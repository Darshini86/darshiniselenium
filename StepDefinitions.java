package com.factory.cucumber.stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {
	private final static Logger logger = Logger.getLogger(StepDefinitions.class.getName());
	public static String apiEndPointUri;
	public static String testName;
	public static String CONTENT_TYPE;
	public static String STATUS_CODE;
	public static String FILE_PATH;
	public static String REQUESTBODY;
	public static String RESPONSEBODY;
	public static Response response;
	public static String name;

	@Given("^I want to set URL as \"([^\"]*)\" for test case \"([^\"]*)\"$")
	public void setAPIEndpointURL(String URL, String testCaseName) {
		String apiHostName = "https://reqres.in";
		apiEndPointUri = String.format("%s%s", apiHostName, URL);
		testName = testCaseName;
	
	}

	@When("^I set header content type as \"([^\"]*)\"$")
	public void setHeader(String contentType) {
		if (contentType != null && !contentType.isEmpty()) {
			CONTENT_TYPE = contentType;
		//	Reporter.addStepLog(Status.PASS + " :: content type is :: " + CONTENT_TYPE);
		logger.info("Content type is :: " + CONTENT_TYPE);
		} else {
		//	Reporter.addStepLog(Status.FAIL + " :: content type cannot be null or empty!");
		logger.info("Content type cannot be null or empty!");
		}
	}

	@And("^I hit the API with requestbody \"([^\"]*)\" and request method is \"([^\"]*)\"$")
	public void submitRequest(String requestBodyPath, String requestType) throws Throwable {
		RestAssured.baseURI = apiEndPointUri;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", CONTENT_TYPE);
		if (requestBodyPath != null && !requestBodyPath.isEmpty() && requestType.equalsIgnoreCase("POST")
				|| requestType.equalsIgnoreCase("PUT")) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "//src//test//java//com//factory//cucumber//"
					+ requestBodyPath;
			//logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
				//logger.info("Request Body is :: " + REQUESTBODY);
				System.out.println("request body-->" + REQUESTBODY);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
			if (REQUESTBODY.length() > 0) {
				request.body(REQUESTBODY);
				response = request.post();
				System.out.println("Response-->" + response);
			} else {
				//Reporter.addStepLog(Status.FAIL + " :: Request Body cannot be null or empty!");
				logger.info(" Request Body cannot be null or empty!");
			}
		} else if (requestType.equalsIgnoreCase("GET")) {
			response = request.get();
		}
	 else if (requestType.equalsIgnoreCase("DELETE")) {
		 System.out.println("RUNNING DELETE");
		response = request.delete("/api/users/1");
	}
		STATUS_CODE = String.valueOf(response.getStatusCode());
		RESPONSEBODY = response.getBody().asString();
		System.out.println("Response -->" + response);
		System.out.println("Response body-->" + RESPONSEBODY);

	}

	@Then("^I try to verify the status code is \"([^\"]*)\"$")
	public void verifyStatusCode(String statusCode) {
		if (statusCode.equals(String.valueOf(STATUS_CODE))) {
			Assert.assertEquals(STATUS_CODE, statusCode);
		} else {
			Assert.assertEquals(STATUS_CODE, statusCode);
		}
	}


	@Then("^I try to verify the response value \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verifyResponseValue(String responseKey, String value) throws Throwable {
		Object obj = responseKey;
		JSONParser parser = new JSONParser();
		JSONObject responseObject = (JSONObject) parser.parse(RESPONSEBODY);
		System.out.println("response object  -->" + responseObject);
		Object key = (Object) responseObject.get(responseKey);
		compareResponseValues(String.valueOf(value), String.valueOf(key), responseKey);
	}
	

	@Then("^I verify the response body contains \"([^\"]*)\"$")	
			public void verifyResponsebody(String value) throws Throwable {
		System.out.println("RB-->"+RESPONSEBODY);
		System.out.println("RB-->"+response.getBody());
		JSONParser parser = new JSONParser();
		JSONObject responseObject = (JSONObject) parser.parse(RESPONSEBODY);
		Object key = responseObject.get("id");
		System.out.println("response Key  -->" + key);
		//System.out.println("response object  -->" +responseObject.get("last_name"));
		Assert.assertTrue(RESPONSEBODY.contains(value));
	}

	private void compareResponseValues(String expected, String actual, String responseKey) {
	//	Reporter.addStepLog("Actual Value is  ::" + actual);
	//	Reporter.addStepLog("Expected Value is  ::" + expected);
		logger.info("Actual Value is  ::" + actual);
		logger.info("Expected Value is  ::" + expected);
		if (expected.equals(actual)) {
			Assert.assertEquals(actual, expected);
		//	Reporter.addStepLog(Status.PASS + " " + responseKey + " : Expected value : " + expected
			//		+ " mathches with Actual Value : " + actual);
		} else {
			//Reporter.addStepLog(Status.FAIL + " " + responseKey + " : Expected value : " + expected
			//		+ " do not matches with Actual Value : " + actual);
			Assert.assertEquals(actual, expected);
		}
	}

}

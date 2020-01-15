package Stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import  static io.restassured.RestAssured.*;

import java.io.FileReader;
import java.util.logging.Logger;

import org.json.simple.parser.JSONParser;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class StepdefinitionGet {

	public static String  baseuri;	
	public static  String endpointurl;
	public static  String CONTENTTYPE;
	public static String FILEPATH;
	public static String REQUESTBODY;
	
	

	@Given("^I want to set the url for the APi as \"([^\"]*)\"$")
	public void setendpointurl(String url) throws Throwable {
	    
		baseuri="https://reqres.in";
		endpointurl=String.format("%s%s", baseuri,url);
		System.out.println("the endpoint url is " + endpointurl);
	}

	@When("^I want to set the  header contenttype as \"([^\"]*)\"$")
	public void  setHeader(String ContentType) throws Throwable {
		
		if (ContentType != null && !ContentType.isEmpty()){	
			CONTENTTYPE=ContentType;	
		}else {
			System.out.println("Content type cannot be Null");
		}			
	}

	@When("^i want to hit the Api with the \"([^\"]*)\" and the \"([^\"]*)\"$")
	public void submitRequestforAPI(String RequestBodypath, String Requestmethod) throws Throwable {
		
		
		RestAssured.baseURI=endpointurl;
		RequestSpecification request= RestAssured.given();
		
	request.header("ContentType",CONTENTTYPE);
	
	JSONParser parser= new JSONParser();
	
	if (RequestBodypath != null && !RequestBodypath.isEmpty() && Requestmethod.equalsIgnoreCase("POST")|| 
			Requestmethod.equalsIgnoreCase("PULL")){
		
		
		FILEPATH= System.getProperty("/arunrestassured/src/test/java/com/factory/cucumber/testdata//"+ RequestBodypath); 
		
		
		FileReader reader=  new FileReader(FILEPATH); 
		
	            Object obj1 =parser.parse(reader);
	            REQUESTBODY=obj1.toString();
	            System.out.println("the request body is " +REQUESTBODY );
	            
	            Response response = request.post();
	            
	            
	            
	            
	        
	            
		
		
		
		
		
	}
		
		
		
		
		
		
		
		
		
	    
	}

	@Then("^I should  verify the status code with \"([^\"]*)\"$")
	public void i_should_verify_the_status_code_with(String arg1) throws Throwable {
	    


}
}

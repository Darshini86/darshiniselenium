package Stepdefinition;

import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import javautil.util;
import junit.framework.Assert;

public class RestApifeature {
	
	  public static String url;
	  public static String endpointurl;
	  public static String path;
	 public static String jsonpath;
	 

	
	@Given("^To initiate Rest service to get user details with id as \"([^\"]*)\"$")
	public void to_initiate_Rest_service_to_get_country_details_with_code_as(String userid) throws Throwable {
		
		util.setbaseurl();
	 	 	    
	}

	@Then("^Response status code should be \"([^\"]*)\"$")
	public void respose_status_code_should_be(int statuscode) throws Throwable {
		
		RequestSpecification httpRequest = RestAssured.given();
		   
		   httpRequest.contentType("/application/JSON");
		   
		   Response response=httpRequest.get();
		 
		 System.out.println("Response is  " + response.prettyPrint());
		 System.out.println("Status code is   " +  response.getStatusCode());        
		     
		Assert.assertEquals("Assertion failed",response.getStatusCode(),statuscode);
				
	    
	}

	@Then("^Response should includes the following$")
	public void response_should_includes_the_following(DataTable arg1) throws Throwable {
	
	   List < List < String >> data = arg1.raw();
        System.out.println("*************** Actua Data Table - data value as-- " + data);
       
        RequestSpecification httpRequest = RestAssured.given();
		   
		   httpRequest.contentType("/application/JSON");
		   
		   Response response=httpRequest.get();
		 
		  JsonPath jsonpath = response.jsonPath();
        
		   System.out.println("The id is   " + jsonpath.get("data.id[1]")); 
		   System.out.println("The emailis   " + jsonpath.get("data.email[1]")); 
		   System.out.println("The first_name is   " + jsonpath.get("data.first_name[1]")); 
		   System.out.println("Thelast_name is   " + jsonpath.get("data.last_name[1]")); 
		   System.out.println("The avatoar is   " + jsonpath.get("data.avatar[1]")); 
		   

  Assert.assertEquals((jsonpath.get("data.email[0]")),(data.get(1).get(1)));
  //Assert.assertEquals((jsonpath.get("data.id[0]")),(data.get(1).get(0)));
  Assert.assertEquals((jsonpath.get("data.first_name[0]")),(data.get(1).get(2)));
  Assert.assertEquals((jsonpath.get("data.last_name[0]")),(data.get(1).get(3)));

}
}

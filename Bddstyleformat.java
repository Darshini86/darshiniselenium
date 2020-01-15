package Stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Bddstyleformat {

@Test
public void GetmethoduserAPI()
{
 RestAssured.baseURI = "https://reqres.in";
 RequestSpecification httpRequest = RestAssured.given();
 Response response = httpRequest.get("/api/users/1");
 
 // First get the JsonPath object instance from the Response interface
 JsonPath jsonPathEvaluator = response.jsonPath();
 
 int statuscode=response.getStatusCode();
 
 String header =response.getHeaders().toString();
 
 // Let us print the city variable to see what we got
 System.out.println("The Response  is" + response.prettyPrint());
 
 //System.out.println("The header is "+ header);
 
 System.out.println(" does the header has the content "+ response.getHeaders().hasHeaderWithName("Content-Type"));
 
 System.out.println("The status code is "+ statuscode);
 
 Assert.assertEquals(200, statuscode);

// // Print the first name:
System.out.println("Firstname for the id 1 " + jsonPathEvaluator.get("data.first_name"));

}




//	public static void getmethod() {
////		RestAssured.baseURI="https://reqres.in/api/unknown/";
////		RestAssured.basePath="1";
//	
//	Response resp=getResponse();
//		
//		given().contentType(ContentType.JSON).
//		when().get(String.format("https://reqres.in/api/unknown/1")).
//		then().body("data.name", is("cerulean")).
//		and().statusCode(200);
//		
//	}
//	public static void validatemethod( String name) {
////		RestAssured.baseURI="https://reqres.in/api/unknown/";
////		RestAssured.basePath="1";
//		
//		given().contentType(ContentType.JSON).
//		//when().get(String.format("https://reqres.in/api/unknown/%s",userid)).
//		then().body("data.name", is(name));
//		
		
//	}
	
//	public static void verifycollections() {
//	
//		
//		given()
//		.contentType(ContentType.JSON).
//		
//		when()
//		.get("https://reqres.in/api/unknown/").
//		
//		then()
//		.body("data.name", containsInAnyOrder(items:"fuchsia rose","" );
//		
//		
//	}

	
	
	
}

package javautil;

import io.restassured.RestAssured;

public class util {
	
	public  static String path;
	public  static String baseuri;
	public static String endurl;
	
	public  static void setbaseurl() {
		
     baseuri=RestAssured.baseURI=("https://reqres.in");
     path=RestAssured.basePath=("/api/users/");
		
	endurl=String.format("%s%s", baseuri,path);
	
	System.out.println("the endpointurl is " + endurl);
	
	
	}
	
	

	

}

package TestRunner;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
 features = "src/main/java/feature/"
+ "RestApi.Feature",glue={"Stepdefinition"},
tags= {"@API_Test"}

 )
 
 
 

public class Runnerclass {
	
	

}

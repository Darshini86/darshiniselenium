#This feature file contains few test scenarios to verify Rest API services
#Author: Muthu
Feature: To verify API automation with Rest Assured

@API_Test
Scenario: To verify Rest service - GET Method
Given To initiate Rest service to get user details with id as "1"
Then Response status code should be "200"
And Response should includes the following
| id | email                          | first_name |last_name |avatar|
| 1 | george.bluth@reqres.in		  | George     |Bluth     |https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg|
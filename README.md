# APIAutomationRestAssured

API Automation Framework with the CRUD of Restful Booker



## Tech Stack 

1. Rest Assured
2. Java
3. Apache POI, TestNG, Maven
4. Jackson and GSON
5. Log42
6. Allure Report
7. Full Folder Structure(Hybrid) Framework.
8. Jenkins File


Run

## Basic Create Test
mvn clean test 

## Integration Test (Create BookinG and Create Token , Update and Delete Booking) 

 mvn clean test -DsuiteXmlFile=testng-integration.xml
 
 
#### Try these Cases also

POSTMAN Assignments
 Assignment  1

Create the Collections for the This Test cases : 

App - Restful Booker with(Auth)

1. Create a Booking, Update the Booking Name, Get the Booking by Id and verify.
2.  Create a Booking, Delete the Booking with Id and Verify using GET request that it should not exist.
3. Get an Existing Booking from Get All Bookings Ids , Update a Booking and Verify using GET by id. 
4. Create a BOOKING, Delete It
5. Invalid Creation - enter a wrong payload or Wrong JSON.
6. Trying to Update on a Delete Id


Test for the Single Req
1. Response
2. Status Code
3. Headers

———

Create Collection 
- [ ] RestfulBooker CRUD operation.
- [ ] Add from Snippets , Test cases
- [ ] Integration Scenarios (Hard Coded)


		
Assert. - Should not happen (9%)
Expect. - Actual Result == Expected ( 90%)
Should - Hard it should happen (1%)



Assignments 2

Full CRUD Test cases for the Github Repo API with Integration Scenarios

1. Create a Test Cases on Template.
2. Submit the Postman Collection with Test cases Added
3. Add the Test cases and integration scenarios also
4. Write Better Advance Postman Test cases.


Assignments 3

1. Get Token ( 2 Step)
2. Upload image on Imgur with OAuth 2.0 ( hash will be generated)
3. Get that image and verify the link hash


Assignments 4

Try the Complex JSON Parsing with the Map and Filters
On Assignment Tab.

Assignments 5

Add JSON Schema Validation for POST, PUT Request for the Restful BOOker.



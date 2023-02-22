package com.thetestingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TC_Integration1 extends BaseTest {

    String token;


    @Test(groups = "integration")
    @Owner("Promode")
    @Description("Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        Response response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload()).post();
        ValidatableResponse validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        System.out.println("Booking Id :" + jsonPath.getString("bookingid"));
        validatableResponse.statusCode(200);
        iTestContext.setAttribute("bookingid", jsonPath.getString("bookingid"));
    }


    @Test(groups = "integration", dependsOnMethods = "testCreateBooking")
    public void testCreateAndUpdateBooking(ITestContext iTestContext) throws JsonProcessingException {
        token = getToken();
        String bookingId = (String) iTestContext.getAttribute("bookingid");
        System.out.println("Pramod - " + bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
        Response response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatedPayload()).put();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.body("firstname", Matchers.is("Lucky"));

    }

    @Test(groups = "integration",dependsOnMethods = "testCreateAndUpdateBooking")
    public void testDeleteCreatedBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + (String) iTestContext.getAttribute("bookingid")).cookie("token",token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }


}

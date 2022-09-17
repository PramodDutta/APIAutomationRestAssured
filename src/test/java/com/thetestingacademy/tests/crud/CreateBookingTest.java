package com.thetestingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.glassfish.gmbal.Description;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.tests.base.BaseTest;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CreateBookingTest extends BaseTest {


    @Test
    @Owner("Promode")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that Create Booking is Working Fine")
    public void testCreateBooking() throws JsonProcessingException {
            requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        ValidatableResponse response = RestAssured.given().spec(requestSpecification).when().body(payloadManager.createPayload())
                .post().then().log().all();

        response.time(Matchers.lessThan(5000L));
        response.statusCode(200);


    }


}

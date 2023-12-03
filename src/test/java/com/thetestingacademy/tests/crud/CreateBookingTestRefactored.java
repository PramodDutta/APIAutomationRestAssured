package com.thetestingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import com.thetestingacademy.modules.PayloadModule;
import com.thetestingacademy.payloads.commons.Commons;
import com.thetestingacademy.payloads.commons.Headers;
import com.thetestingacademy.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class CreateBookingTestRefactored extends BaseTest {


    private static final Logger log = LogManager.getLogger(CreateBookingTestRefactored.class);
    ;

    @Test
    @Owner("Promode")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Booking can be Created")
    public void testCreateBooking() throws JsonProcessingException {
        String reqBody = new PayloadManager().createPayload();
        String response = new PayloadModule().createBooking(reqBody, Headers.getHeaddersForSeesion());
        jsonPath = JsonPath.from(response);
        System.out.println("Booking Id :" + jsonPath.getString("bookingid"));
        log.error("TestDone");

    }


}

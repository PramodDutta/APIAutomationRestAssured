package com.thetestingacademy.modules;

import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.payloads.commons.Commons;
import com.thetestingacademy.payloads.commons.Payload;

import java.util.HashMap;

public class PayloadModule extends Payload {

    Commons c = new Commons();
    public String createBooking(String reqBody, HashMap<String,String> configureHeaders) {
        c.baseUrl = APIConstants.BASE_URL;
        apiName = "Create Booking";
        reqrelateiveURl = APIConstants.CREATE_BOOKING;
        reqbody = reqBody;
        reqmethod = APIConstants.POST;
        reqparameter = new HashMap<String ,String>();
        reqHeaders = configureHeaders ;
        c.CallMethod(this);
        return resStrbody;
    }
}

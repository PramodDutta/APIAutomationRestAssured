package com.thetestingacademy.payloads.commons;

import java.util.HashMap;
import io.restassured.response.Response;
public class Payload {

    public String apiName;
    public String baseUrl;
    public String reqrelateiveURl;
    public HashMap<String,String> reqparameter = new HashMap<String, String>();
    public HashMap<String,String> reqHeaders = new HashMap<String, String>();
    public String reqmethod;
    public int reqStatus;
    public String reqbody;
    public String resHeaders;
    public String resStatus;
    public int resStatusCode;
    public String resStrbody;
    public String access_token;
    public Response response;
    public String resTraceId;
    public HashMap<String,String> multiPart = new HashMap<>();
}

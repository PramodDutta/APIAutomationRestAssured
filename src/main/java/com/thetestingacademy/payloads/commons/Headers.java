package com.thetestingacademy.payloads.commons;

import java.util.HashMap;

public class Headers {

    public static ThreadLocal<HashMap<String, String>> reqHeadersTL = new ThreadLocal<HashMap<String, String>>();


    public static HashMap<String, String> getHeaddersForSeesion() {
        HashMap<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("cookie", "test");
        reqHeaders.put("content-type", "application/json;charset=utf-8");
        reqHeaders.put("accept", "application/json");
        reqHeadersTL.set(reqHeaders);
        return reqHeadersTL.get();
    }
}

package com.thetestingacademy.payloads.commons;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.StringReader;

public class Commons {

    public String baseUrl = null;
    public static ThreadLocal<Payload> payLoadObj = new ThreadLocal<Payload>();


    public static <T> String objectToJSONString(T object) {
        String json = "";
        ObjectMapper mapperObj = new ObjectMapper();
        mapperObj.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            // get object as a json string
            json = mapperObj.writeValueAsString(object);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //		logger.info("Request Body "+ json);
        return json;

    }


    public static <T> T jsonStringToObject(String resStrbody, Class<T> obj) {
        if (resStrbody == null)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, getIsIndApiValidation());
        try {
            return mapper.readValue(new StringReader(resStrbody), obj);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * isIndApiValidation - this is a flag that describes whether this is individual api validation.
     * In case of Individual api validation, the status code validation will not be done.
     * ie. Usually, if the status code came as value other than 200, it will be marked as failure.
     * If  isIndApiValidation flag is turned ON, this status code validation won't be done.
     */
    public static ThreadLocal<Boolean> isIndApiValidation = new ThreadLocal<Boolean>();

    public static Boolean getIsIndApiValidation() {
        Boolean status = false;
        try {
            status = isIndApiValidation.get();
            if (status == null)
                status = false;
        } catch (Throwable t) {
        }
        return status;
    }

    public int CallMethod(Payload payload) {

        int statusCode = 0;
        switch (payload.reqmethod) {

            case "GET":
                payload = GetMethod(payload);
                break;
            case "POST":
                payload = PostMethod(payload);
                break;
            case "PUT":
                payload = PutMethod(payload);
                break;
            case "DELETE":
                payload = DeleteMethod(payload);
                break;
            case "POST_FORM":
                payload = PostMethodWithFormData(payload);
                break;
            default:
                System.out.println("Invalid API method");
                break;
        }
        payload.baseUrl = baseUrl;
        statusCode = payload.resStatusCode;
        String traceId = payload.resTraceId;
        Commons.setPayloadObj(payload);
        //Commons.writeDataIntoReport(payload);

        return statusCode;

    }

    public Payload GetMethod(Payload payload) {

        try {
            RestAssured.baseURI = baseUrl;

            Response response = RestAssured.given().
                    params(payload.reqparameter).
                    headers(payload.reqHeaders).
                    when().get(payload.reqrelateiveURl);

            //	ResponseBody body = response.getBody();
            String strResponse = response.asString();
            payload.resStrbody = Commons.getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payload;
    }

    public Payload PostMethod(Payload payload) {
        try {
            RestAssured.baseURI = baseUrl;
            Response response = null;
            if (payload.reqbody != null) {
                response = (Response) RestAssured.given().
                        headers(payload.reqHeaders).
                        body(payload.reqbody).
                        when().
                        post(payload.reqrelateiveURl);
            } else {
                response = (Response) RestAssured.given().
                        headers(payload.reqHeaders).
                        when().
                        post(payload.reqrelateiveURl);
            }

            String strResponse = response.asString();
            payload.resStrbody = Commons.getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    public Payload PostMethodWithFormData(Payload payload) {
        try {

            RestAssured.baseURI = baseUrl;
            Response response = null;
            RequestSpecification request = RestAssured.given()
                    .headers(payload.reqHeaders)
                    .contentType("multipart/form-data").request();

            payload.multiPart.forEach((key, value) -> {
                if (key.equalsIgnoreCase("file"))
                    request.multiPart("file", new File(value));
                else
                    request.multiPart(key, value);
            });

            response = request
                    .when()
                    .post(payload.reqrelateiveURl);

            String strResponse = response.asString();
            payload.resStrbody = Commons.getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    public Payload PutMethod(Payload payload) {

        try {
            RestAssured.baseURI = baseUrl;
            Response response = (Response) RestAssured.given().
                    params(payload.reqparameter).
                    headers(payload.reqHeaders).
                    body(payload.reqbody).
                    when().
                    put(payload.reqrelateiveURl);

            String strResponse = response.asString();
            payload.resStrbody = Commons.getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    public Payload DeleteMethod(Payload payload) {

        try {
            RestAssured.baseURI = baseUrl;
            Response response = (Response) RestAssured.given().
                    params(payload.reqparameter).
                    headers(payload.reqHeaders).
                    body(payload.reqbody).
                    when().
                    delete(payload.reqrelateiveURl);
            String strResponse = response.asString();
            payload.resStrbody = Commons.getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    public static String getPrettyJsonString(String json) {
        try {
            if (json != null && !json.isEmpty()) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement je = JsonParser.parseString(json);
                return gson.toJson(je);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void setPayloadObj(Payload payload) {
        payLoadObj.set(payload);
    }


}

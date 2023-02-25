package com.credo.ussd.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.UUID;

public class Util {
    public static HttpHeaders getAuthHeader(String auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(auth);
        return headers;
    }

    public static String generateUniqueCode(){
        return UUID.randomUUID().toString();
    }
}

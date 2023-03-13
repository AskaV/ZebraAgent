package com.aska.zebra_tests;

import com.aska.zebra_tests.api.enumtype.HTTPStatusCodeType;
import com.aska.zebra_tests.api.methods.PostAuthenticationMethod;
import io.restassured.path.json.JsonPath;

public class AuthService {

    private static String authToken;

    public static String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String response) {
        authToken = JsonPath.from(response).get(JsonConstant.AUTH_TOKEN).toString();
    }

    public static void refreshAuthToken() {
        ExecutionService executor = new ExecutionService();
        PostAuthenticationMethod authenticationMethod = new PostAuthenticationMethod();
        executor.expectStatus(authenticationMethod, HTTPStatusCodeType.OK);
        AuthService authService = new AuthService();
        authService.setAuthToken(executor.callApiMethod(authenticationMethod));
    }
}
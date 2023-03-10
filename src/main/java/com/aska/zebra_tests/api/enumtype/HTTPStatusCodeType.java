package com.aska.zebra_tests.api.enumtype;

public enum HTTPStatusCodeType {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    OK_NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private int statusCode;

    HTTPStatusCodeType(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

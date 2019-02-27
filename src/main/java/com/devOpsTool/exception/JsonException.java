package com.devOpsTool.exception;

public class JsonException extends RestClientException {
    public JsonException(String message) {
        super(message);
    }
    public JsonException(Exception e) {
        super(e);
    }
}

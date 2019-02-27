package com.devOpsTool.exception;

public class JamaTypeMismatchException extends RestClientException {
    public JamaTypeMismatchException () {}

    public JamaTypeMismatchException (String message) {
        super(message);
    }

}

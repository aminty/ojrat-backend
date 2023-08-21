package com.amin.ojrat.exception;

public class RequestLimitExceededException extends Exception{
    public RequestLimitExceededException(String message) {
        super(message);
    }
}

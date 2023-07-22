package com.amin.ojrat.exception;

import java.util.List;

public class DefaultResponse  {

    private final int code;
    private final String message;
    private final List<String> errors;

    public DefaultResponse(int code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getCode() {
        return code;
    }
}

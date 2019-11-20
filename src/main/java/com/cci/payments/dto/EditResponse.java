package com.cci.payments.dto;

import java.util.Map;

public class EditResponse {
    private boolean success;
    private Map<String, String> errors;
    private String msg;

    public EditResponse() {
    }

    public EditResponse(boolean success) {
        this.success = success;
    }

    public EditResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public EditResponse(String msg) {
        this.msg = msg;
    }

    public EditResponse(Map<String, String> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getMsg() {
        return msg;
    }
}

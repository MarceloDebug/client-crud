package com.marcelodebug.clientcrud.dto;

public class FieldMessage {
    private String fieldName;
    private String message;

    public FieldMessage(String field, String message) {
        this.fieldName = field;
        this.message = message;
    }

    public String getField() {
        return fieldName;
    }

    public void setField(String field) {
        this.fieldName = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

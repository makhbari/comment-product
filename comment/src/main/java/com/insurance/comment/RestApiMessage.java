package com.insurance.comment;

public enum RestApiMessage {
    ERROR_500_OCCURRED("خطایی در سامانه رخ داد.");

    private final String value;

    RestApiMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


package com.javaguru.shoppinglist.service.validationCart;

public enum CartValidationErrors {
    EMPTY_NAME("Name can't be empty"),
    NAME_LENGTH_VIOLATION("Name can be only from 3 to 32 symbols long");

    private final String response;

    CartValidationErrors(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}

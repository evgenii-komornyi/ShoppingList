package com.javaguru.shoppinglist.service.validationCart;

public enum CartValidationErrors {
    EMPTY_NAME("Name can't be empty"),
    NAME_LENGTH_VIOLATION("Name can be only from 3 to 32 symbols long"),
    EMPTY_ID("ID can't be empty"),
    CART_NOT_EMPTY("Can't delete not empty cart"),
    DUPLICATE_ENTRY("Cart has such product already"),
    CART_NOT_EXIST("Cart not exist"),
    PRODUCT_NOT_EXIST("Product not exist");

    private final String response;

    CartValidationErrors(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}

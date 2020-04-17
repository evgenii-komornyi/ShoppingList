package com.javaguru.shoppinglist.repository;

public enum DBErrors {
    DB_CONNECTION_FAILED("Database has failed, please try again later"),
    DB_DUPLICATE_ENTRY("Such entry has exist already"),
    DB_DUPLICATE_ENTRY_CART("Your cart has such product already");

    private String response;

    DBErrors(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}

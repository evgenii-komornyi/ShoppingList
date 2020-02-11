package com.javaguru.shoppinglist.Service.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ValidationErrors {
    EMPTY_NAME("Name can't be empty"),
    EMPTY_PRICE("Price can't be empty"),
    EMPTY_CATEGORY("Category can't be empty"),
    NAME_LENGTH_VIOLATION("Name can be only from 3 to 32 symbols long"),
    DUPLICATE_NAME("Name already exist"),
    NEGATIVE_OR_ZERO_PRICE("Price can't be negative, or 0"),
    DISCOUNT_APPLICATION_LIMIT_VIOLATION("Discount can't be applied to price lower than 20 euro"),
    INVALID_DISCOUNT_RANGE("Discount can be only from 0 to 100, or empty"),
    DESCIPTION_LENGTH_VIOLATION("Description can be only from 8 to 60 symbols long"),
    NO_SEARCH_CRITERIA("ID, or name, or category field for search can't be empty"),
    CONFLICT_SEARCH_PARAMS("Search is available only by ID, or name, or category"),
    NO_UPDATE_CRITERIA("ID, or category field for update can't be empty"),
    CONFLICT_UPDATE_PARAMS("Update is available only by ID, or category");

    private String response;

    ValidationErrors(String response) {
        this.response = response;
    }

    public static List<ValidationErrors> getValidationErrors() {
        List<ValidationErrors> validationErrors = new ArrayList<>(Arrays.asList(ValidationErrors.values()));

        return validationErrors;
    }

    public String getResponse() {
        return response;
    }
}

package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.product.request.CreateRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreateRequestValidation {
    public List<ValidationErrors> validateCreateRequest(CreateRequest createFieldRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateNameField(createFieldRequest.getProductName()));
        allErrors.addAll(validatePriceField(createFieldRequest.getProductPrice()));
        allErrors.addAll(validateCategoryField(createFieldRequest.getProductCategory()));
        allErrors.addAll(validateRangeOfDiscount(createFieldRequest.getProductDiscount()));
        if (createFieldRequest.getProductDescription() != null && !createFieldRequest.getProductDescription().isEmpty()) {
            allErrors.addAll(validateDescriptionField(createFieldRequest.getProductDescription()));
        }

        allErrors.addAll(validateDiscountPossibility(createFieldRequest.getProductPrice(), createFieldRequest.getProductDiscount()));

        return allErrors;
    }

    private List<ValidationErrors> validateNameField(String name) {
        List<ValidationErrors> errorsList = new ArrayList<>();
        if (name == null) {
            errorsList.add(ValidationErrors.EMPTY_NAME);
        } else if (name.length() < 3 || name.length() > 32) {
            errorsList.add(ValidationErrors.NAME_LENGTH_VIOLATION);
        }
        return errorsList;
    }

    private List<ValidationErrors> validatePriceField(BigDecimal price) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (price == null) {
            errorsList.add(ValidationErrors.EMPTY_PRICE);
        } else {
            errorsList.addAll(negativeNumberCheck(price));
        }
        return errorsList;
    }

    private List<ValidationErrors> negativeNumberCheck(BigDecimal price) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            errorsList.add(ValidationErrors.NEGATIVE_OR_ZERO_PRICE);
        }
        return errorsList;
    }

    private List<ValidationErrors> validateDiscountPossibility(BigDecimal price, BigDecimal discount) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (price != null && discount != null) {
            if (price.compareTo(BigDecimal.valueOf(20)) < 0
                    && discount.compareTo(BigDecimal.ZERO) != 0) {
                errorsList.add(ValidationErrors.DISCOUNT_APPLICATION_LIMIT_VIOLATION);
            }
        }
        return errorsList;
    }

    private List<ValidationErrors> validateCategoryField(String category) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (category == null) {
            errorsList.add(ValidationErrors.EMPTY_CATEGORY);
        }
        return errorsList;
    }

    private List<ValidationErrors> validateRangeOfDiscount(BigDecimal discount) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (discount != null) {
            if (discount.compareTo(BigDecimal.valueOf(0)) < 0
                    || discount.compareTo(BigDecimal.valueOf(100)) > 0) {
                errorsList.add(ValidationErrors.INVALID_DISCOUNT_RANGE);
            }
        }
        return errorsList;
    }

    private List<ValidationErrors> validateDescriptionField(String description) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (description != null) {
            if (description.length() < 8 || description.length() > 60) {
                errorsList.add(ValidationErrors.DESCIPTION_LENGTH_VIOLATION);
            }
        }
        return errorsList;
    }
}

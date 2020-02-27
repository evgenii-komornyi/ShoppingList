package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateRequestValidation {
    public List<ValidationErrors> validateUpdateRequest(UpdateRequest updateFieldRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateIDField(updateFieldRequest));
        allErrors.addAll(validateNameField(updateFieldRequest.getProductName()));
        allErrors.addAll(validatePriceField(updateFieldRequest.getProductPrice()));
        allErrors.addAll(validateCategoryField(updateFieldRequest.getProductCategory()));
        allErrors.addAll(validateRangeOfDiscount(updateFieldRequest.getProductDiscount()));
        if (!updateFieldRequest.getProductDescription().isEmpty()) {
            allErrors.addAll(validateDescriptionField(updateFieldRequest.getProductDescription()));
        }

        allErrors.addAll(validateDiscountPossibility(updateFieldRequest.getProductPrice(), updateFieldRequest.getProductDiscount()));
        allErrors.addAll(validateSearchCriteria(updateFieldRequest));

        return allErrors;
    }

    private List<ValidationErrors> validateIDField(UpdateRequest updateRequest) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (updateRequest.getProductID() == null) {
            errorsList.add(ValidationErrors.NO_UPDATE_CRITERIA);
        }
        return errorsList;
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

    private List<ValidationErrors> validateSearchCriteria(UpdateRequest updateRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        if (updateRequest.getProductID() == null) {
            allErrors.add(ValidationErrors.NO_UPDATE_CRITERIA);
        }

        return allErrors;
    }
}

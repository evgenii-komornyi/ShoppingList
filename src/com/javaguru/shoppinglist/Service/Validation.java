package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Validation {
    public List<ValidationErrors> validateCreateRequest(CreateRequest createFieldRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateNameField(createFieldRequest.getProductName()));
        allErrors.addAll(validatePriceField(createFieldRequest.getProductPrice()));
        allErrors.addAll(validateCategoryField(createFieldRequest.getProductCategory()));
        allErrors.addAll(validateDiscountField(createFieldRequest.getProductDiscount()));
        allErrors.addAll(validateDescriptionField(createFieldRequest.getProductDescription()));

        allErrors.addAll(validateToMoreThanTwentyPrice(createFieldRequest.getProductPrice(), createFieldRequest.getProductDiscount()));

        return allErrors;
    }

    public List<ValidationErrors> validateUpdateRequest(UpdateRequest updateFieldRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateSearchCriteria(updateFieldRequest));

        allErrors.addAll(validateNewPriceField(updateFieldRequest.getNewProductPrice()));
        allErrors.addAll(validateDiscountField(updateFieldRequest.getNewProductDiscount()));
        allErrors.addAll(validateDescriptionField(updateFieldRequest.getNewDescription()));

        return allErrors;
    }

    public List<ValidationErrors> validateFindRequest(FindRequest findFieldRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateSearchCriteria(findFieldRequest));

        return allErrors;
    }

    private List<ValidationErrors> validateNameField(String name) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (name.isEmpty()) {
            errorsList.add(ValidationErrors.EMPTY_NAME);
            return errorsList;
        }
        errorsList.addAll(lengthCheck(name));
        return errorsList;
    }

    private List<ValidationErrors> lengthCheck(String name) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (name != null
                && name.length() < 3
                || name.length() > 32) {
            errorsList.add(ValidationErrors.NAME_LENGTH_VIOLATION);
        }
        return errorsList;
    }

    private List<ValidationErrors> validatePriceField(BigDecimal price) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (price == null) {
            errorsList.add(ValidationErrors.EMPTY_PRICE);
        }
        errorsList.addAll(negativeNumberCheck(price));
        return errorsList;
    }

    private List<ValidationErrors> negativeNumberCheck(BigDecimal price) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (price != null && price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            errorsList.add(ValidationErrors.NEGATIVE_OR_ZERO_PRICE);
        }
        return errorsList;
    }

    private List<ValidationErrors> validateToMoreThanTwentyPrice(BigDecimal price, BigDecimal discount) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (price != null
            && price.compareTo(BigDecimal.valueOf(20)) < 0
            && discount != null
            && discount.compareTo(BigDecimal.ZERO) != 0) {
            errorsList.add(ValidationErrors.DISCOUNT_APPLICATION_LIMIT_VIOLATION);
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

    private List<ValidationErrors> validateDiscountField(BigDecimal discount) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (discount != null && discount.compareTo(BigDecimal.valueOf(0)) < 0) {
            errorsList.add(ValidationErrors.NEGATIVE_DISCOUNT);
        }
        errorsList.addAll(validateRangeOfDiscount(discount));
        return errorsList;
    }

    private List<ValidationErrors> validateRangeOfDiscount(BigDecimal discount) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (discount != null
                && (discount.compareTo(BigDecimal.valueOf(0)) < 0
                || discount.compareTo(BigDecimal.valueOf(100)) > 0)) {
            errorsList.add(ValidationErrors.INVALID_RANGE);
        }
        return errorsList;
    }

    private List<ValidationErrors> validateDescriptionField(String description) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        if (description != null && !description.isEmpty()
                && (description.length() < 8 || description.length() > 60)) {
            errorsList.add(ValidationErrors.DESCIPTION_LENGTH_VIOLATION);
        }
        return errorsList;
    }

    private List<ValidationErrors> validateSearchCriteria(UpdateRequest searchCriteria) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        if (searchCriteria.getProductID() == null && searchCriteria.getProductCategory() == null)
        {
            allErrors.add(ValidationErrors.NO_UPDATE_CRITERIA);
        }

        if (searchCriteria.getProductID() != null && searchCriteria.getProductCategory() != null)
        {
            allErrors.add(ValidationErrors.CONFLICT_UPDATE_PARAMS);
        }
        return allErrors;
    }

    private List<ValidationErrors> validateNewPriceField(BigDecimal newPrice) {
        List<ValidationErrors> errorsList = new ArrayList<>();

        errorsList.addAll(negativeNumberCheck(newPrice));
        return errorsList;
    }

    private List<ValidationErrors> validateSearchCriteria(FindRequest searchCriteria) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        if (searchCriteria.getProductID() == null
                && searchCriteria.getProductName() == null
                && searchCriteria.getProductCategory() == null)
        {
            allErrors.add(ValidationErrors.NO_SEARCH_CRITERIA);
        }

        if (searchCriteria.getProductID() != null && searchCriteria.getProductName() != null && searchCriteria.getProductCategory() != null)
        {
            allErrors.add(ValidationErrors.CONFLICT_SEARCH_PARAMS);
        }
        return allErrors;
    }
}

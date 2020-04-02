package com.javaguru.shoppinglist.service.validationProduct;

import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCreateRequestValidation {
    public List<ProductValidationErrors> validateCreateRequest(ProductCreateRequest createFieldRequest) {
        List<ProductValidationErrors> allErrors = new ArrayList<>();

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

    private List<ProductValidationErrors> validateNameField(String name) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();
        if (name == null) {
            errorsList.add(ProductValidationErrors.EMPTY_NAME);
        } else if (name.length() < 3 || name.length() > 32) {
            errorsList.add(ProductValidationErrors.NAME_LENGTH_VIOLATION);
        }
        return errorsList;
    }

    private List<ProductValidationErrors> validatePriceField(BigDecimal price) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();

        if (price == null) {
            errorsList.add(ProductValidationErrors.EMPTY_PRICE);
        } else {
            errorsList.addAll(negativeNumberCheck(price));
        }
        return errorsList;
    }

    private List<ProductValidationErrors> negativeNumberCheck(BigDecimal price) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();

        if (price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            errorsList.add(ProductValidationErrors.NEGATIVE_OR_ZERO_PRICE);
        }
        return errorsList;
    }

    private List<ProductValidationErrors> validateDiscountPossibility(BigDecimal price, BigDecimal discount) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();

        if (price != null && discount != null) {
            if (price.compareTo(BigDecimal.valueOf(20)) < 0
                    && discount.compareTo(BigDecimal.ZERO) != 0) {
                errorsList.add(ProductValidationErrors.DISCOUNT_APPLICATION_LIMIT_VIOLATION);
            }
        }
        return errorsList;
    }

    private List<ProductValidationErrors> validateCategoryField(String category) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();

        if (category == null) {
            errorsList.add(ProductValidationErrors.EMPTY_CATEGORY);
        }
        return errorsList;
    }

    private List<ProductValidationErrors> validateRangeOfDiscount(BigDecimal discount) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();

        if (discount != null) {
            if (discount.compareTo(BigDecimal.valueOf(0)) < 0
                    || discount.compareTo(BigDecimal.valueOf(100)) > 0) {
                errorsList.add(ProductValidationErrors.INVALID_DISCOUNT_RANGE);
            }
        }
        return errorsList;
    }

    private List<ProductValidationErrors> validateDescriptionField(String description) {
        List<ProductValidationErrors> errorsList = new ArrayList<>();

        if (description != null) {
            if (description.length() < 8 || description.length() > 60) {
                errorsList.add(ProductValidationErrors.DESCIPTION_LENGTH_VIOLATION);
            }
        }
        return errorsList;
    }
}

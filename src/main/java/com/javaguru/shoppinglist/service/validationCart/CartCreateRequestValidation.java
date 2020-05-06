package com.javaguru.shoppinglist.service.validationCart;

import com.javaguru.shoppinglist.domain.cart.request.CartCreateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartCreateRequestValidation {
    public List<CartValidationErrors> validateCreateRequest(CartCreateRequest createFieldRequest) {
        List<CartValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateNameField(createFieldRequest.getCartName()));

        return allErrors;
    }

    private List<CartValidationErrors> validateNameField(String name) {
        List<CartValidationErrors> errorsList = new ArrayList<>();
        if (name == null) {
            errorsList.add(CartValidationErrors.EMPTY_NAME);
        } else if (name.length() < 3 || name.length() > 32) {
            errorsList.add(CartValidationErrors.NAME_LENGTH_VIOLATION);
        }
        return errorsList;
    }
}

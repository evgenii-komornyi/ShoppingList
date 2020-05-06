package com.javaguru.shoppinglist.service.validationCart;

import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartFindRequestValidation {
    public List<CartValidationErrors> validateFindRequest(CartFindRequest findFieldRequest) {
        List<CartValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateIDField(findFieldRequest.getCartId()));

        return allErrors;
    }

    private List<CartValidationErrors> validateIDField(Integer id) {
        List<CartValidationErrors> errorsList = new ArrayList<>();

        if (id == null) {
            errorsList.add(CartValidationErrors.EMPTY_ID);
        }

        return errorsList;
    }
}

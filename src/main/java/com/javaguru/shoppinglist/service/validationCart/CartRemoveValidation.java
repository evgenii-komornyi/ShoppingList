package com.javaguru.shoppinglist.service.validationCart;

import com.javaguru.shoppinglist.domain.cart.Cart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartRemoveValidation {
    public List<CartValidationErrors> validateCartRemoveRequest(Cart cart) {
        List<CartValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateNotEmptyCart(cart));

        return allErrors;
    }

    private List<CartValidationErrors> validateNotEmptyCart(Cart cart) {
        List<CartValidationErrors> errorsList = new ArrayList<>();

        if (!cart.getProductsInCart().isEmpty()) {
            errorsList.add(CartValidationErrors.CART_NOT_EMPTY);
        }

        return errorsList;
    }
}

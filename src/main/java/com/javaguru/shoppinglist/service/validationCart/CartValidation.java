package com.javaguru.shoppinglist.service.validationCart;

import org.springframework.stereotype.Component;

@Component
public class CartValidation {
    private final CartCreateRequestValidation cartCreateRequestValidation;

    public CartValidation(CartCreateRequestValidation cartCreateRequestValidation) {
        this.cartCreateRequestValidation = cartCreateRequestValidation;
    }

    public CartCreateRequestValidation getCartCreateRequestValidation() {
        return cartCreateRequestValidation;
    }
}

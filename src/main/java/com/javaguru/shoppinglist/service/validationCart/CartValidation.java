package com.javaguru.shoppinglist.service.validationCart;

import org.springframework.stereotype.Component;

@Component
public class CartValidation {
    private final CartCreateRequestValidation cartCreateRequestValidation;
    private final CartFindRequestValidation cartFindRequestValidation;
    private final EmptyCartValidation emptyCartValidation;

    public CartValidation(CartCreateRequestValidation cartCreateRequestValidation, CartFindRequestValidation cartFindRequestValidation, EmptyCartValidation emptyCartValidation) {
        this.cartCreateRequestValidation = cartCreateRequestValidation;
        this.cartFindRequestValidation = cartFindRequestValidation;
        this.emptyCartValidation = emptyCartValidation;
    }

    public CartCreateRequestValidation getCartCreateRequestValidation() {
        return cartCreateRequestValidation;
    }

    public CartFindRequestValidation getCartFindRequestValidation() {
        return cartFindRequestValidation;
    }

    public EmptyCartValidation getEmptyCartValidation() {
        return emptyCartValidation;
    }
}

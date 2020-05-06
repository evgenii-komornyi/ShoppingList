package com.javaguru.shoppinglist.service.validationCart;

import org.springframework.stereotype.Component;

@Component
public class CartValidation {
    private final CartCreateRequestValidation cartCreateRequestValidation;
    private final CartFindRequestValidation cartFindRequestValidation;
    private final CartRemoveValidation cartRemoveValidation;
    private final AddProductToCartValidation addProductToCartValidation;


    public CartValidation(CartCreateRequestValidation cartCreateRequestValidation, CartFindRequestValidation cartFindRequestValidation, CartRemoveValidation cartRemoveValidation, AddProductToCartValidation addProductToCartValidation) {
        this.cartCreateRequestValidation = cartCreateRequestValidation;
        this.cartFindRequestValidation = cartFindRequestValidation;
        this.cartRemoveValidation = cartRemoveValidation;
        this.addProductToCartValidation = addProductToCartValidation;
    }

    public CartCreateRequestValidation getCartCreateRequestValidation() {
        return cartCreateRequestValidation;
    }

    public CartFindRequestValidation getCartFindRequestValidation() {
        return cartFindRequestValidation;
    }

    public CartRemoveValidation getCartRemoveValidation() {
        return cartRemoveValidation;
    }

    public AddProductToCartValidation getAddProductToCartValidation() {
        return addProductToCartValidation;
    }
}

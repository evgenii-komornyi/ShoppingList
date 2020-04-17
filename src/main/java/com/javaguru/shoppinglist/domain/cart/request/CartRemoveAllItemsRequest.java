package com.javaguru.shoppinglist.domain.cart.request;

import com.javaguru.shoppinglist.domain.cart.Cart;

public class CartRemoveAllItemsRequest {
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

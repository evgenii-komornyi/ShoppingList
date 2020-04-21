package com.javaguru.shoppinglist.domain.cart.response;

import com.javaguru.shoppinglist.domain.cart.Cart;

import java.math.BigDecimal;

public class CartFindResponse extends CartBasicResponse {
    private Cart cart;
    private BigDecimal amount;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

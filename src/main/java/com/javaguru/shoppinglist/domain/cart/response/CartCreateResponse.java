package com.javaguru.shoppinglist.domain.cart.response;

import com.javaguru.shoppinglist.domain.cart.Cart;

import java.util.Objects;

public class CartCreateResponse extends CartBasicResponse {
    private Cart cart;

    @Override
    public String toString() {
        return "CartCreateResponse{" +
                "cart=" + cart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartCreateResponse that = (CartCreateResponse) o;
        return Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

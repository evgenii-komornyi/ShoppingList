package com.javaguru.shoppinglist.domain.cart.request;

import java.util.Objects;

public class CartCreateRequest {
    private Integer cartId;
    private String cartName;

    @Override
    public String toString() {
        return "CartCreateRequest{" +
                "cartId=" + cartId +
                ", cartName='" + cartName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartCreateRequest request = (CartCreateRequest) o;
        return Objects.equals(cartId, request.cartId) &&
                Objects.equals(cartName, request.cartName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, cartName);
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }
}

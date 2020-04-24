package com.javaguru.shoppinglist.domain.cart.request;

import java.util.Objects;

public class CartFindRequest {
    private Integer cartId;

    @Override
    public String toString() {
        return "CartFindRequest{" +
                "cartId=" + cartId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartFindRequest that = (CartFindRequest) o;
        return Objects.equals(cartId, that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId);
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}

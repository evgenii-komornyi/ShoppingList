package com.javaguru.shoppinglist.domain.cart.response;

import com.javaguru.shoppinglist.domain.cart.Cart;

import java.math.BigDecimal;
import java.util.Objects;

public class CartFindResponse extends CartBasicResponse {
    private Cart cart;
    private BigDecimal amount = BigDecimal.ZERO;

    @Override
    public String toString() {
        return "CartFindResponse{" +
                "cart=" + cart +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartFindResponse that = (CartFindResponse) o;
        return Objects.equals(cart, that.cart) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cart, amount);
    }

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

package com.javaguru.shoppinglist.domain.cart.response;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationCart.CartValidationErrors;

import java.util.List;
import java.util.Objects;

public class CartCreateResponse {
    private Cart cart;
    private List<DBErrors> dbErrorsList;
    private List<CartValidationErrors> validationErrorsList;

    public boolean hasDBErrors() {
        return (dbErrorsList != null && !dbErrorsList.isEmpty());
    }

    public boolean hasValidationErrors() {
        return (validationErrorsList != null && !validationErrorsList.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartCreateResponse that = (CartCreateResponse) o;
        return Objects.equals(cart, that.cart) &&
                Objects.equals(dbErrorsList, that.dbErrorsList) &&
                Objects.equals(validationErrorsList, that.validationErrorsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart, dbErrorsList, validationErrorsList);
    }

    @Override
    public String toString() {
        return "CartCreateResponse{" +
                "cart=" + cart +
                ", dbErrorsList=" + dbErrorsList +
                ", validationErrors=" + validationErrorsList +
                '}';
    }

    public List<DBErrors> getDbErrorsList() {
        return dbErrorsList;
    }

    public void setDbErrorsList(List<DBErrors> dbErrorsList) {
        this.dbErrorsList = dbErrorsList;
    }

    public List<CartValidationErrors> getValidationErrorsList() {
        return validationErrorsList;
    }

    public void setValidationErrorsList(List<CartValidationErrors> validationErrorsList) {
        this.validationErrorsList = validationErrorsList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

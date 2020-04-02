package com.javaguru.shoppinglist.domain.cart.response;

public class CartResponseAnswer {
    private CartResponseStatus responseStatus;

    public CartResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(CartResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}

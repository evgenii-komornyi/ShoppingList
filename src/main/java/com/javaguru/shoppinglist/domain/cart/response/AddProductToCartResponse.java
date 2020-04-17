package com.javaguru.shoppinglist.domain.cart.response;

public class AddProductToCartResponse extends CartBasicResponse {
    private Status stat;

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }
}

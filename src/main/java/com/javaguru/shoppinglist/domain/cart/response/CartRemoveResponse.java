package com.javaguru.shoppinglist.domain.cart.response;

public class CartRemoveResponse extends CartBasicResponse {
    private RemoveCartStatus stat;

    public RemoveCartStatus getStat() {
        return stat;
    }

    public void setStat(RemoveCartStatus stat) {
        this.stat = stat;
    }
}


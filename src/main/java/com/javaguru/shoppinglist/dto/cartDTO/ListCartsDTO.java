package com.javaguru.shoppinglist.dto.cartDTO;

import java.util.List;

public class ListCartsDTO {
    private List<CartDTO> carts;

    public List<CartDTO> getCarts() {
        return carts;
    }

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }
}

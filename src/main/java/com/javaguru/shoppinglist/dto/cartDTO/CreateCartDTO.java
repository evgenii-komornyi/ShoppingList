package com.javaguru.shoppinglist.dto.cartDTO;

import com.javaguru.shoppinglist.domain.cart.response.CartCreateResponse;

public class CreateCartDTO extends CartBasicDTO {
    private CartCreateResponse cartResponse;

    public CartCreateResponse getCartResponse() {
        return cartResponse;
    }

    public void setCartResponse(CartCreateResponse cartResponse) {
        this.cartResponse = cartResponse;
    }
}

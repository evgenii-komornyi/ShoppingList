package com.javaguru.shoppinglist.dto.cartDTO;

public class AddProductToCartDTO extends CartBasicDTO {
    private Long productID;
    private Integer cartID;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }
}

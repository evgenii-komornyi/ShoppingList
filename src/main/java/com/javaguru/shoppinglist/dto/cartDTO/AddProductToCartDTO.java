package com.javaguru.shoppinglist.dto.cartDTO;

public class AddProductToCartDTO extends CartBasicDTO {
    private Long productId;
    private Integer cartID;

    public Long getProductId() {
        return productId;
    }

    public void setProductID(Long productID) {
        this.productId = productID;
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }
}

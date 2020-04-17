package com.javaguru.shoppinglist.dto.cartDTO;

public class ProductToCartRequestDTO {
    private Integer cartID;
    private Long productID;

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}

package com.javaguru.shoppinglist.Catalog.Product.Request;

public class UpdateRequest extends CreateRequest {
    private Long productID;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}

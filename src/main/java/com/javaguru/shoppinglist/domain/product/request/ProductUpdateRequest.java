package com.javaguru.shoppinglist.domain.product.request;

public class ProductUpdateRequest extends ProductCreateRequest {
    private Long productID;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}

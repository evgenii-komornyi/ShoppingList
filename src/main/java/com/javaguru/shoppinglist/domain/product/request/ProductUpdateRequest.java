package com.javaguru.shoppinglist.domain.product.request;

import java.util.Objects;

public class ProductUpdateRequest extends ProductCreateRequest {
    private Long productID;

    @Override
    public String toString() {
        return "ProductUpdateRequest{" +
                "productID=" + productID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUpdateRequest request = (ProductUpdateRequest) o;
        return Objects.equals(productID, request.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}

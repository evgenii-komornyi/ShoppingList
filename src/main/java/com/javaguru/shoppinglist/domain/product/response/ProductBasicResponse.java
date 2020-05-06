package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidationErrors;

import java.util.List;
import java.util.Objects;

public abstract class ProductBasicResponse {
    private List<ProductValidationErrors> productValidationErrors;
    private List<DBErrors> DBErrors;

    public boolean hasValidationErrors() {
        return (productValidationErrors != null && !productValidationErrors.isEmpty());
    }
    public boolean hasDBErrors() {
        return (DBErrors != null && !DBErrors.isEmpty());
    }

    @Override
    public String toString() {
        return "BasicResponse{" +
                "productValidationErrors=" + productValidationErrors +
                ", DBErrors=" + DBErrors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBasicResponse that = (ProductBasicResponse) o;
        return Objects.equals(productValidationErrors, that.productValidationErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productValidationErrors, DBErrors);
    }

    public List<ProductValidationErrors> getValidationErrors() {
        return productValidationErrors;
    }

    public void setValidationErrors(List<ProductValidationErrors> productValidationErrors) {
        this.productValidationErrors = productValidationErrors;
    }

    public List<DBErrors> getDBErrors() {
        return DBErrors;
    }

    public void setDBErrors(List<DBErrors> DBErrors) {
        this.DBErrors = DBErrors;
    }
}

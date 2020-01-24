package com.javaguru.shoppinglist.Catalog.Product.Response;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Service.ValidationErrors;

import java.util.List;
import java.util.Objects;

public class CreateResponse {
    private List<ValidationErrors> validationErrors;
    private Product product;

    public boolean hasErrors() {
        return (validationErrors != null && validationErrors.size() != 0);
    }

    @Override
    public String toString() {
        return "CreateResponse{" +
                "validationErrors=" + validationErrors +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateResponse that = (CreateResponse) o;
        return Objects.equals(validationErrors, that.validationErrors) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationErrors, product);
    }

    public List<ValidationErrors> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationErrors> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.domain.product.Product;

import java.util.Objects;

public class CreateResponse extends BasicResponse {
    private Product product;

    @Override
    public String toString() {
        return "CreateResponse{" +
                "product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreateResponse that = (CreateResponse) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

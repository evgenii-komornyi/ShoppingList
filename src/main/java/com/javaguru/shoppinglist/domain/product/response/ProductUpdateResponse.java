package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.domain.product.Product;

import java.util.List;
import java.util.Objects;

public class ProductUpdateResponse extends ProductBasicResponse {
    private Product updatedProduct;
    private List<Product> listOfUpdatedProducts;

    @Override
    public String toString() {
        return "UpdateResponse{" +
                "updatedProduct=" + updatedProduct +
                ", listOfUpdatedProducts=" + listOfUpdatedProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductUpdateResponse that = (ProductUpdateResponse) o;
        return Objects.equals(updatedProduct, that.updatedProduct) &&
                Objects.equals(listOfUpdatedProducts, that.listOfUpdatedProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), updatedProduct, listOfUpdatedProducts);
    }

    public Product getUpdatedProduct() {
        return updatedProduct;
    }

    public void setUpdatedProduct(Product updatedProduct) {
        this.updatedProduct = updatedProduct;
    }

    public List<Product> getListOfUpdatedProducts() {
        return listOfUpdatedProducts;
    }

    public void setListOfUpdatedProducts(List<Product> listOfUpdatedProducts) {
        this.listOfUpdatedProducts = listOfUpdatedProducts;
    }
}

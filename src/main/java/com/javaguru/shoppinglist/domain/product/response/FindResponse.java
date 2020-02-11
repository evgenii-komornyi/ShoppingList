package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.domain.product.Product;

import java.util.List;
import java.util.Objects;

public class FindResponse extends BasicResponse {
    private Product foundProduct;
    private List<Product> listOfFoundProducts;

    @Override
    public String toString() {
        return "FindResponse{" +
                "product=" + foundProduct +
                ", listOfProducts=" + listOfFoundProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FindResponse response = (FindResponse) o;
        return Objects.equals(foundProduct, response.foundProduct) &&
                Objects.equals(listOfFoundProducts, response.listOfFoundProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foundProduct, listOfFoundProducts);
    }

    public Product getFoundProduct() {
        return foundProduct;
    }

    public void setFoundProduct(Product foundProduct) {
        this.foundProduct = foundProduct;
    }

    public List<Product> getListOfFoundProducts() {
        return listOfFoundProducts;
    }

    public void setListOfFoundProducts(List<Product> listOfFoundProducts) {
        this.listOfFoundProducts = listOfFoundProducts;
    }
}

package com.javaguru.shoppinglist.Catalog.Product.Request;

import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;

import java.math.BigDecimal;
import java.util.Objects;

public class FindRequest {
    private Long productID;
    private String productName;
    private ProductCategory productCategory;
    private BigDecimal productPrice;

    public FindRequest() {
        this.productID = null;
        this.productName = null;
        this.productPrice = null;
        this.productCategory = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindRequest that = (FindRequest) o;
        return Objects.equals(productID, that.productID) &&
                Objects.equals(productName, that.productName) &&
                productCategory == that.productCategory &&
                Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productCategory, productPrice);
    }

    public Long getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
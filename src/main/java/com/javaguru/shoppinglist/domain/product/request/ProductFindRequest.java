package com.javaguru.shoppinglist.domain.product.request;

import com.javaguru.shoppinglist.domain.product.ProductCategory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
@Component
public class ProductFindRequest {
    private Long productID;
    private String productName;
    private ProductCategory productCategory;
    private BigDecimal productPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFindRequest that = (ProductFindRequest) o;
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
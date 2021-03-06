package com.javaguru.shoppinglist.domain.product.request;

import java.math.BigDecimal;

public class ProductCreateRequest {
    private Long productID;

    private String productName;

    private BigDecimal productPrice;

    private String productCategory;

    private BigDecimal productDiscount = BigDecimal.ZERO;
    private String productDescription = "";

    @Override
    public String toString() {
        return "ProductCreateRequest{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                ", productDiscount=" + productDiscount +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}

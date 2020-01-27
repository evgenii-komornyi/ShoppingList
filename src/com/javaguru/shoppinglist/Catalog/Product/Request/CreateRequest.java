package com.javaguru.shoppinglist.Catalog.Product.Request;

import java.math.BigDecimal;

public class CreateRequest {
    private String productName;
    private BigDecimal productPrice;
    private String productCategory;

    private BigDecimal productDiscount;
    private String productDescription;

    public CreateRequest() {
        this.productName = null;
        this.productPrice = null;
        this.productCategory = null;
        this.productDiscount = new BigDecimal("0.0");
        this.productDescription = "";
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

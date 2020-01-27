package com.javaguru.shoppinglist.Catalog.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {
    private static Long countID = 0L;

    private Long productID;
    private String productName;
    private BigDecimal productPrice;
    private ProductCategory productCategory;
    private BigDecimal productDiscount = new BigDecimal("0.00");
    private String productDescription = "";

    public Product(String productName, BigDecimal productPrice, ProductCategory productCategory) {
        this.productID = getCountID();
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    private Long getCountID() {
        return countID++;
    }

    public BigDecimal calculateActualPrice() {
        BigDecimal productActualPrice = productPrice.subtract((productPrice.multiply(productDiscount)).divide(BigDecimal.valueOf(100)));
        return productActualPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productActualPrice=" + calculateActualPrice() +
                ", productCategory=" + productCategory +
                ", productDiscount=" + productDiscount.setScale(2, RoundingMode.HALF_EVEN) +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productPrice, product.productPrice) &&
                productCategory == product.productCategory &&
                Objects.equals(productDiscount, product.productDiscount) &&
                Objects.equals(productDescription, product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productPrice, productCategory, productDiscount, productDescription);
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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
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
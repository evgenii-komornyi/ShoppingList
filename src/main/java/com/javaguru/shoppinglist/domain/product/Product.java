package com.javaguru.shoppinglist.domain.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private BigDecimal productRegularPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;

    @Column(name = "discount")
    private BigDecimal productDiscount;

    @Column(name = "description")
    private String productDescription;

    public Product(){}

    public Product(String productName, BigDecimal productRegularPrice, ProductCategory category) {
        this.productName = productName;
        this.productRegularPrice = productRegularPrice;
        this.category = category;
    }

    public BigDecimal calculateActualPrice() {
        BigDecimal productActualPrice = productRegularPrice.subtract((productRegularPrice.multiply(productDiscount)).divide(BigDecimal.valueOf(100)));
        return productActualPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productRegularPrice=" + productRegularPrice +
                ", productActualPrice=" + calculateActualPrice() +
                ", productCategory=" + category +
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
                Objects.equals(productRegularPrice, product.productRegularPrice) &&
                category == product.category &&
                Objects.equals(productDiscount, product.productDiscount) &&
                Objects.equals(productDescription, product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productRegularPrice, category, productDiscount, productDescription);
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

    public BigDecimal getProductRegularPrice() {
        return productRegularPrice;
    }

    public void setProductRegularPrice(BigDecimal productRegularPrice) {
        this.productRegularPrice = productRegularPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory productCategory) {
        this.category = productCategory;
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
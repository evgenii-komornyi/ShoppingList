package com.javaguru.shoppinglist.dto.productDTO;

import com.javaguru.shoppinglist.domain.product.ProductCategory;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDTO {
    private Long productID;
    private String productName;
    private BigDecimal productRegularPrice;
    private ProductCategory productCategory;

    private BigDecimal productDiscount;
    private String productDescription;

    public ProductDTO() {
    }

    public ProductDTO(Long productID, String productName, BigDecimal productRegularPrice, ProductCategory productCategory) {
        this.productID = productID;
        this.productName = productName;
        this.productRegularPrice = productRegularPrice;
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productRegularPrice=" + productRegularPrice +
                ", productCategory=" + productCategory +
                ", productDiscount=" + productDiscount +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(productID, that.productID) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productRegularPrice, that.productRegularPrice) &&
                productCategory == that.productCategory &&
                Objects.equals(productDiscount, that.productDiscount) &&
                Objects.equals(productDescription, that.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productRegularPrice, productCategory, productDiscount, productDescription);
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

    public interface Update {
    }

    public interface Create {
    }
}

package com.javaguru.shoppinglist.dto.productDTO;

import com.javaguru.shoppinglist.domain.product.ProductCategory;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal regPrice;
    private ProductCategory category;

    private BigDecimal discount;
    private String description;
    private BigDecimal actPrice;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regPrice=" + regPrice +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", actPrice=" + actPrice +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(BigDecimal regPrice) {
        this.regPrice = regPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }
}

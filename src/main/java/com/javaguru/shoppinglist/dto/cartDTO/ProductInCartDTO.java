package com.javaguru.shoppinglist.dto.cartDTO;

import com.javaguru.shoppinglist.domain.product.ProductCategory;

import java.math.BigDecimal;

public class ProductInCartDTO {
    private Long id;
    private String name;
    private ProductCategory category;
    private BigDecimal regPrice;
    private BigDecimal discount;
    private BigDecimal actPrice;
    private String description;

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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public BigDecimal getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(BigDecimal regPrice) {
        this.regPrice = regPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

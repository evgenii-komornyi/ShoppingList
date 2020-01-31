package com.javaguru.shoppinglist.Catalog.Product.Request;

import java.math.BigDecimal;

public class UpdateRequest extends FindRequest {
    private BigDecimal newProductPrice;
    private BigDecimal newProductDiscount;
    private String newDescription;

    public BigDecimal getNewProductPrice() {
        return newProductPrice;
    }

    public void setNewProductPrice(BigDecimal newProductPrice) {
        this.newProductPrice = newProductPrice;
    }

    public BigDecimal getNewProductDiscount() {
        return newProductDiscount;
    }

    public void setNewProductDiscount(BigDecimal newProductDiscount) {
        this.newProductDiscount = newProductDiscount;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}

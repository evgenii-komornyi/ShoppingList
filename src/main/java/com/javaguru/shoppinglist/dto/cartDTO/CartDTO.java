package com.javaguru.shoppinglist.dto.cartDTO;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO extends CartBasicDTO {
    private Integer id;
    private String name;
    private List<ProductInCartDTO> products;
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductInCartDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCartDTO> products) {
        this.products = products;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

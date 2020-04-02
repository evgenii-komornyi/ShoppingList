package com.javaguru.shoppinglist.domain.cart.request;

import com.javaguru.shoppinglist.domain.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class CartFindRequest {
    private Integer cartId;
    private String cartName;
    private List<Product> productsInCart;
    private BigDecimal amount;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

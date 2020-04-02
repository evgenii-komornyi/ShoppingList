package com.javaguru.shoppinglist.domain.cart.response;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.product.Product;

public class ProductToCartRequest {
    private Cart cart;

    @Override
    public String toString() {
        return "ProductToCartRequest{" +
                "cart=" + cart +
                ", product=" + product +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

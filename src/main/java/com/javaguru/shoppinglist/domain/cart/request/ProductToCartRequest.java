package com.javaguru.shoppinglist.domain.cart.request;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.product.Product;

public class ProductToCartRequest {
    private Cart cart;
    private Product product;

    @Override
    public String toString() {
        return "ProductToCartRequest{" +
                "cart=" + cart +
                ", product=" + product +
                '}';
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

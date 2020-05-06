package com.javaguru.shoppinglist.dto.productDTO;

import com.javaguru.shoppinglist.dto.cartDTO.CartDTO;

import java.util.List;

public class ListProductsDTO {
    private List<ProductDTO> products;
    private List<CartDTO> carts;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public List<CartDTO> getCarts() {
        return carts;
    }

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }
}

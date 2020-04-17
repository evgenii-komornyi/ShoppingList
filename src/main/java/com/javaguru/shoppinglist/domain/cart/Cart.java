package com.javaguru.shoppinglist.domain.cart;

import com.javaguru.shoppinglist.domain.product.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @Column(name = "name")
    private String cartName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_in_cart",
            joinColumns = @JoinColumn(name = "cartId", unique = true),
            inverseJoinColumns = @JoinColumn(name = "productId", unique = true)
            )
    private List<Product> productsInCart;

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", cartName='" + cartName + '\'' +
                ", productsInCart=" + productsInCart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) &&
                Objects.equals(cartName, cart.cartName) &&
                Objects.equals(productsInCart, cart.productsInCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, cartName, productsInCart);
    }

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
}

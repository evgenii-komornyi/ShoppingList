package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartRemoveAllItemsRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartRemoveItemRequest;
import com.javaguru.shoppinglist.domain.cart.request.ProductToCartRequest;
import com.javaguru.shoppinglist.domain.product.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("hibernate")
@Transactional
public class CartRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Cart create(Cart cart) {
        sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    public Cart readById(CartFindRequest findRequest) {
        Cart cart = (Cart) sessionFactory.getCurrentSession().createCriteria(Cart.class)
            .add(Restrictions.eq("id", findRequest.getCartId()))
                .uniqueResult();
        return cart;
    }

    public List<Cart> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Cart.class).list();
    }

    public boolean delete(Cart cart) {
        if (cart != null) {
            sessionFactory.getCurrentSession().delete(cart);
            return true;
        }
        return false;
    }

    public List<Product> addItemToCart(ProductToCartRequest request) {
        sessionFactory.getCurrentSession().saveOrUpdate(request.getProduct());
        List<Product> productInCart = request.getCart().getProductsInCart();
        productInCart.add(request.getProduct());

        return productInCart;
    }

    public boolean removeItemFromCart(CartRemoveItemRequest request) {
        Cart cart = request.getCart();
        Product product = request.getProduct();

        List<Product> productsInCart = cart.getProductsInCart();

        productsInCart.remove(product);
        return true;
    }

    public boolean removeAllItemsFromCart(CartRemoveAllItemsRequest request) {
        if (request.getCart() != null) {
            Cart cart = request.getCart();
            List<Product> productsInCart = cart.getProductsInCart();

            productsInCart.clear();
            return true;
        }
        return false;
    }
}

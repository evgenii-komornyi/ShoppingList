package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.product.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("hibernate")
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

    public List<Product> addItemToCart(Product product, Cart cart) throws DataIntegrityViolationException {
        Session session = sessionFactory.getCurrentSession();
        List<Product> productInCart = cart.getProductsInCart();

        session.saveOrUpdate(product);
        productInCart.add(product);
        return productInCart;
    }

    public boolean removeItemFromCart(Cart cart, Product product) {
        List<Product> productsInCart = cart.getProductsInCart();

        productsInCart.remove(product);
        return true;
    }

    public boolean removeAllItemsFromCart(Cart cart) {
        if (cart != null) {
            List<Product> productsInCart = cart.getProductsInCart();

            productsInCart.clear();
            return true;
        }
        return false;
    }
}

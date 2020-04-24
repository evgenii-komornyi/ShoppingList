package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartCreateRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validationCart.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductService productService;

    private CartCreateRequestValidation createRequestValidation = new CartCreateRequestValidation();
    private CartFindRequestValidation findRequestValidation = new CartFindRequestValidation();
    private CartRemoveValidation removeValidation = new CartRemoveValidation();
    private AddProductToCartValidation addProductToCartValidation = new AddProductToCartValidation();

    private CartValidation cartValidation = new CartValidation(createRequestValidation, findRequestValidation, removeValidation, addProductToCartValidation);

    private CartService victim;

    @Before
    public void setUp() {
        victim = new CartService(cartRepository, productService, cartValidation);
    }

    @Test
    public void shouldCreateCart() {
        Cart cart = cartWithoutID();

        when(cartRepository.create(cart)).thenReturn(cart);

        Cart result = victim.createCart(createCartRequest()).getCart();

        assertThat(cart).isEqualTo(result);
    }

    @Test
    public void shouldFindCartByID() {
        Cart cart = cartWithID();

        when(cartRepository.readById(findRequest())).thenReturn(cart);

        Cart result = victim.findCartByID(findRequest()).getCart();

        assertThat(cart).isEqualTo(result);
    }

    @Test
    public void shouldDeleteCartByID() {
        Cart cart = cartWithID();

        when(cartRepository.readById(findRequest())).thenReturn(cart);
        when(cartRepository.delete(cart)).thenReturn(true);

        boolean result = victim.deleteCartByID(findRequest()).isDeleted();

        assertThat(result).isTrue();
    }

    private Cart cartWithoutID() {
        Cart cart = new Cart();
        cart.setCartName("Cart");

        return cart;
    }

    private CartCreateRequest createCartRequest() {
        CartCreateRequest request = new CartCreateRequest();
        request.setCartName("Cart");

        return request;
    }

    private Cart cartWithID() {
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setCartName("Cart");
        cart.setProductsInCart(new ArrayList<>());
        return cart;
    }

    private CartFindRequest findRequest() {
        CartFindRequest request = new CartFindRequest();
        request.setCartId(1);

        return request;
    }
}
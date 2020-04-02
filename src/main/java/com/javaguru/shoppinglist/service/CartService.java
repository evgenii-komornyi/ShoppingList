package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartCreateRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.cart.response.CartCreateResponse;
import com.javaguru.shoppinglist.domain.cart.response.CartResponseStatus;
import com.javaguru.shoppinglist.domain.cart.response.ProductToCartRequest;
import com.javaguru.shoppinglist.domain.cart.response.CartResponseAnswer;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationCart.CartValidation;
import com.javaguru.shoppinglist.service.validationCart.CartValidationErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartValidation cartValidation;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, CartValidation cartValidation) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartValidation = cartValidation;
    }

    public CartCreateResponse createCart(CartCreateRequest createRequest) {
        CartCreateResponse response = new CartCreateResponse();
        List<CartValidationErrors> validationErrors = cartValidation.getCartCreateRequestValidation().validateCreateRequest(createRequest);
        List<DBErrors> dbErrors = new ArrayList<>();

        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                Cart cart = new Cart();
                cart.setCartName(createRequest.getCartName());

                response.setCart(cartRepository.create(cart));
            } catch (CannotGetJdbcConnectionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            } catch (DuplicateKeyException | ConstraintViolationException e) {
                dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
            }
            response.setDbErrorsList(dbErrors);
        }
        return response;
    }

    public ProductToCartRequest findCartByID(CartFindRequest findRequest) {
        ProductToCartRequest response = new ProductToCartRequest();

        response.setCart(cartRepository.readById(findRequest));
        return response;
    }

    public List<Cart> getAllCarts() {
        List<Cart> allCarts = new ArrayList<>();
        try {
            allCarts = cartRepository.findAll();
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database has failed, please try again later");
        }

        return allCarts;
    }

    public CartResponseAnswer addToCart(ProductFindRequest productFindRequest, CartFindRequest cartFindRequest) {
        CartResponseAnswer cartResponseAnswer = new CartResponseAnswer();
        ProductToCartRequest productToCartRequest = new ProductToCartRequest();

        Product product = productService.findByID(productFindRequest).getFoundProduct();
        Cart cart = findCartByID(cartFindRequest).getCart();

        if (product != null && cart != null) {
            cartResponseAnswer.setResponseStatus(CartResponseStatus.OK);
            productToCartRequest.setCart(cart);
            productToCartRequest.setProduct(product);
            cartRepository.addItemToCart(productToCartRequest);
        } else {
            cartResponseAnswer.setResponseStatus(CartResponseStatus.FAILED);
        }

        return cartResponseAnswer;
    }
}

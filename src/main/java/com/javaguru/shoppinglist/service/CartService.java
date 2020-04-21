package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartCreateRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.cart.response.*;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationCart.CartValidation;
import com.javaguru.shoppinglist.service.validationCart.CartValidationErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
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

    @Transactional
    public CartCreateResponse createCart(CartCreateRequest createRequest) {
        List<CartValidationErrors> validationErrors = cartValidation.getCartCreateRequestValidation().validateCreateRequest(createRequest);

        CartCreateResponse response = new CartCreateResponse();
        List<DBErrors> dbErrors = new ArrayList<>();
        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                Cart cart = new Cart();
                cart.setCartName(createRequest.getCartName());

                response.setCart(cartRepository.create(cart));
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            } catch (DuplicateKeyException | ConstraintViolationException e) {
                dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
            }
            response.setDbErrorsList(dbErrors);
        }
        return response;
    }

    @Transactional
    public CartFindResponse findCartByID(CartFindRequest findRequest) {
        List<CartValidationErrors> validationErrors = cartValidation.getCartFindRequestValidation().validateFindRequest(findRequest);

        CartFindResponse response = new CartFindResponse();
        List<DBErrors> dbErrors = new ArrayList<>();
        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                response.setCart(cartRepository.readById(findRequest));
                response.setAmount(calculateTotalAmount(response.getCart().getProductsInCart()));
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            }
            response.setDbErrorsList(dbErrors);
        }
        return response;
    }

    private BigDecimal calculateTotalAmount(List<Product> products) {
        BigDecimal amount = new BigDecimal("0.00");
        for (Product product : products) {
            amount = amount.add(product.calculateActualPrice());
        }
        return amount;
    }

    @Transactional
    public CartRemoveResponse deleteCartByID(CartFindRequest findRequest) {
        Cart cart = findCartByID(findRequest).getCart();
        List<CartValidationErrors> validationErrors = cartValidation.getCartRemoveValidation().validateCartRemoveRequest(cart);

        CartRemoveResponse response = new CartRemoveResponse();
        List<DBErrors> dbErrors = new ArrayList<>();
        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                cartRepository.delete(cart);
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            }
            response.setDbErrorsList(dbErrors);
        }
        return response;
    }

    @Transactional
    public List<Cart> getAllCarts() {
        List<Cart> allCarts = new ArrayList<>();
        try {
            allCarts = cartRepository.findAll();
        } catch (CannotCreateTransactionException e) {
            System.out.println("Database has failed, please try again later");
        }

        return allCarts;
    }

    @Transactional
    public AddProductToCartResponse addToCart(Long productID, Integer cartID) {
        ProductFindRequest productFindRequest = new ProductFindRequest();
        CartFindRequest cartFindRequest = new CartFindRequest();
        productFindRequest.setProductID(productID);
        cartFindRequest.setCartId(cartID);

        Product product = productService.findByID(productFindRequest).getFoundProduct();
        Cart cart = findCartByID(cartFindRequest).getCart();

        List<CartValidationErrors> cartValidationErrors = cartValidation.getAddProductToCartValidation().validateAddProductToCart(cart, product);

        AddProductToCartResponse response = new AddProductToCartResponse();
        List<DBErrors> dbErrors = new ArrayList<>();

        if (!cartValidationErrors.isEmpty()) {
            response.setValidationErrorsList(cartValidationErrors);
        } else {
            try {
                cartRepository.addItemToCart(product, cart);
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            }
            response.setDbErrorsList(dbErrors);
        }

        return response;
    }

    @Transactional
    public CartRemoveItemResponse removeItemFromCart(Integer cartId, Long productId) {
        CartFindRequest cartFindRequest = new CartFindRequest();
        cartFindRequest.setCartId(cartId);
        Cart cart = findCartByID(cartFindRequest).getCart();

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(productId);
        Product product = productService.findByID(productFindRequest).getFoundProduct();

        CartRemoveItemResponse response = new CartRemoveItemResponse();
        List<DBErrors> dbErrors = new ArrayList<>();

        if (cart != null && product != null) {
            try {
                cartRepository.removeItemFromCart(cart, product);
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            }
        } else {
            response.setDbErrorsList(dbErrors);
        }
        return response;
    }

    @Transactional
    public CartClearResponse clearCart(CartFindRequest cartFindRequest) {
        Cart cart = findCartByID(cartFindRequest).getCart();
        List<CartValidationErrors> validationErrors = cartValidation.getCartFindRequestValidation().validateFindRequest(cartFindRequest);

        CartClearResponse response = new CartClearResponse();
        List<DBErrors> dbErrors = new ArrayList<>();
        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                cartRepository.removeAllItemsFromCart(cart);
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            }
            response.setDbErrorsList(dbErrors);
        }
        return response;
    }
}

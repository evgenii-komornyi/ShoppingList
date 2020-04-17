package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.*;
import com.javaguru.shoppinglist.domain.cart.response.*;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationCart.CartValidation;
import com.javaguru.shoppinglist.service.validationCart.CartValidationErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        List<CartValidationErrors> validationErrors = cartValidation.getCartCreateRequestValidation().validateCreateRequest(createRequest);
        List<DBErrors> dbErrors = new ArrayList<>();

        CartCreateResponse response = new CartCreateResponse();

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

    public CartFindResponse findCartByID(CartFindRequest findRequest) {
        List<CartValidationErrors> validationErrors = cartValidation.getCartFindRequestValidation().validateFindRequest(findRequest);
        List<DBErrors> dbErrors = new ArrayList<>();
        CartFindResponse response = new CartFindResponse();

        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                response.setCart(cartRepository.readById(findRequest));
            } catch (CannotGetJdbcConnectionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
                response.setDbErrorsList(dbErrors);
            }
        }
        return response;
    }

    public CartRemoveResponse deleteCartByID(CartFindRequest findRequest) {
        List<CartValidationErrors> validationErrors = cartValidation.getCartFindRequestValidation().validateFindRequest(findRequest);
        List<DBErrors> dbErrorsList = new ArrayList<>();
        CartRemoveResponse response = new CartRemoveResponse();

        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
            response.setStat(RemoveCartStatus.FAILED);
        } else {
            Cart cart = findCartByID(findRequest).getCart();
            try {
                cartRepository.delete(cart);
                response.setStat(RemoveCartStatus.SUCCESS);
            } catch (CannotGetJdbcConnectionException e) {
                dbErrorsList.add(DBErrors.DB_CONNECTION_FAILED);
            }
        }
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

    public AddProductToCartResponse addToCart(AddProductToCartByIDRequest requestProductCartIDs) {
        ProductFindRequest productFindRequest = new ProductFindRequest();
        CartFindRequest cartFindRequest = new CartFindRequest();

        productFindRequest.setProductID(requestProductCartIDs.getProductID());
        cartFindRequest.setCartId(requestProductCartIDs.getCartID());

        Product product = productService.findByID(productFindRequest).getFoundProduct();
        Cart cart = findCartByID(cartFindRequest).getCart();

        AddProductToCartResponse response = new AddProductToCartResponse();
        List<DBErrors> dbErrorsList = new ArrayList<>();

        if (dbErrorsList.isEmpty()) {
            if (product != null && cart != null) {
                ProductToCartRequest productToCartRequest = new ProductToCartRequest();

                productToCartRequest.setCart(cart);
                productToCartRequest.setProduct(product);
                try {
                    cartRepository.addItemToCart(productToCartRequest);
                    response.setStat(Status.SUCCESS);
                } catch (DuplicateKeyException e) {
                    dbErrorsList.add(DBErrors.DB_DUPLICATE_ENTRY_CART);
                } catch (ConstraintViolationException e) {
                    dbErrorsList.add(DBErrors.DB_DUPLICATE_ENTRY_CART);
                } catch (DataIntegrityViolationException e) {
                    dbErrorsList.add(DBErrors.DB_DUPLICATE_ENTRY_CART);
                } catch (CannotGetJdbcConnectionException e) {
                    dbErrorsList.add(DBErrors.DB_CONNECTION_FAILED);
                }
            } else {
                response.setStat(Status.FAILED);
            }
        } else {
            response.setDbErrorsList(dbErrorsList);
        }

        return response;
    }

    public CartRemoveItemResponse removeItemFromCart(RemoveProductFromCartRequest requestCartAndProductIDs) {
        CartFindRequest cartFindRequest = new CartFindRequest();
        cartFindRequest.setCartId(requestCartAndProductIDs.getCartID());

        Cart cart = findCartByID(cartFindRequest).getCart();

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(requestCartAndProductIDs.getProductID());

        Product product = productService.findByID(productFindRequest).getFoundProduct();

        CartRemoveItemResponse response = new CartRemoveItemResponse();

        if (requestCartAndProductIDs.getCartID() != null && requestCartAndProductIDs.getProductID() != null) {
            CartRemoveItemRequest cartRemoveItemRequest = new CartRemoveItemRequest();

            cartRemoveItemRequest.setCart(cart);
            cartRemoveItemRequest.setProduct(product);
            cartRepository.removeItemFromCart(cartRemoveItemRequest);
            response.setStat(RemoveCartStatus.SUCCESS);
        } else {
            response.setStat(RemoveCartStatus.FAILED);
        }
        return response;
    }

    public CartClearResponse clearCart(CartFindRequest cartFindRequest) {
        CartRemoveAllItemsRequest request = new CartRemoveAllItemsRequest();
        Cart cart = findCartByID(cartFindRequest).getCart();

        CartClearResponse response = new CartClearResponse();
        if (cart != null) {
            request.setCart(cart);
            cartRepository.removeAllItemsFromCart(request);
            response.setStat(RemoveCartStatus.SUCCESS);
        }
        return response;
    }
}

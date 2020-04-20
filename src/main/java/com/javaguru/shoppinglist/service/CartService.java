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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(propagation = Propagation.NEVER)
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
            } catch (CannotCreateTransactionException e) {
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
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
                response.setDbErrorsList(dbErrors);
            }
        }
        return response;
    }

    public CartRemoveResponse deleteCartByID(CartFindRequest findRequest) {
        List<DBErrors> dbErrorsList = new ArrayList<>();
        CartRemoveResponse response = new CartRemoveResponse();

        Cart cart = findCartByID(findRequest).getCart();
        List<CartValidationErrors> validationErrors = cartValidation.getCartRemoveValidation().validateCartRemoveRequest(cart);

        if (!validationErrors.isEmpty()) {
            response.setValidationErrorsList(validationErrors);
        } else {
            try {
                cartRepository.delete(cart);
            } catch (CannotCreateTransactionException e) {
                dbErrorsList.add(DBErrors.DB_CONNECTION_FAILED);
            }
            response.setDbErrorsList(dbErrorsList);
        }
        return response;
    }

    public List<Cart> getAllCarts() {
        List<Cart> allCarts = new ArrayList<>();
        try {
            allCarts = cartRepository.findAll();
        } catch (CannotCreateTransactionException e) {
            System.out.println("Database has failed, please try again later");
        }

        return allCarts;
    }

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

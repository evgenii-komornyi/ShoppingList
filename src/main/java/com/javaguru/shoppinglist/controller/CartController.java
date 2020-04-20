package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartCreateRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.cart.request.RemoveProductFromCartRequest;
import com.javaguru.shoppinglist.domain.cart.response.AddProductToCartResponse;
import com.javaguru.shoppinglist.domain.cart.response.CartCreateResponse;
import com.javaguru.shoppinglist.domain.cart.response.CartRemoveResponse;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.dto.cartDTO.*;
import com.javaguru.shoppinglist.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableWebMvc
public class CartController {
    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/allCarts")
    public ListCartsDTO getAllCarts() {
        ListCartsDTO responseJSON = new ListCartsDTO();
        List<CartDTO> cartDTOList = new ArrayList<>();
        List<Cart> cartList = cartService.getAllCarts();

        for (Cart cart : cartList) {
            cartDTOList.add(convertCartToDTO(cart));
        }
        responseJSON.setCarts(cartDTOList);

        return responseJSON;
    }

    private CartDTO convertCartToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();

        cartDTO.setId(cart.getCartId());
        cartDTO.setName(cart.getCartName());

        return cartDTO;
    }

    @PostMapping(value = "/addCart")
    public CreateCartDTO addCart(@RequestBody CartCreateRequest createRequest) {
        CreateCartDTO responseJSON = new CreateCartDTO();
        CartCreateResponse cart = cartService.createCart(createRequest);

        if (cart.hasDBErrors() || cart.hasValidationErrors()) {
            responseJSON.setStat(Status.FAILED);
            responseJSON.setValidationErrors(cart.getValidationErrorsList());
            responseJSON.setDbErrors(cart.getDbErrorsList());
        } else {
            responseJSON.setStat(Status.SUCCESS);
            responseJSON.setCartResponse(cart);
        }

        return responseJSON;
    }

    @DeleteMapping(value = "/carts/deleteCart")
    public DeleteCartDTO deleteCartByID(@RequestParam Integer cartId) {
        DeleteCartDTO responseJSON = new DeleteCartDTO();
        CartFindRequest cartFindRequest = new CartFindRequest();
        cartFindRequest.setCartId(cartId);
        cartService.findCartByID(cartFindRequest);
        CartRemoveResponse cart = cartService.deleteCartByID(cartFindRequest);

        if (cart.hasValidationErrors() || cart.hasDBErrors()) {
            responseJSON.setStat(Status.FAILED);
        } else {
            responseJSON.setStat(Status.SUCCESS);
        }
        return responseJSON;
    }

    @GetMapping(value = "/cart/{cartId}")
    public CartDTO singleCart(@PathVariable("cartId") Integer id) {
        CartDTO responseJSON = new CartDTO();
        CartFindRequest findRequest = new CartFindRequest();
        findRequest.setCartId(id);

        Cart cart = cartService.findCartByID(findRequest).getCart();
        List<Product> productsInCart = cart.getProductsInCart();
        List<ProductInCartDTO> productsDTOList = new ArrayList<>();

        BigDecimal amount = new BigDecimal("0.0");
        for (Product product : productsInCart) {
            productsDTOList.add(convertProductInCartToDTO(product));
            amount = amount.add(product.calculateActualPrice());
        }

        responseJSON.setId(cart.getCartId());
        responseJSON.setName(cart.getCartName());
        responseJSON.setProducts(productsDTOList);
        responseJSON.setAmount(amount);

        return responseJSON;
    }

    private ProductInCartDTO convertProductInCartToDTO(Product product) {
        ProductInCartDTO productInCartDTO = new ProductInCartDTO();

        productInCartDTO.setId(product.getProductID());
        productInCartDTO.setName(product.getProductName());
        productInCartDTO.setRegPrice(product.getProductRegularPrice());
        productInCartDTO.setCategory(product.getCategory());
        productInCartDTO.setDiscount(product.getProductDiscount());
        productInCartDTO.setActPrice(product.calculateActualPrice());
        productInCartDTO.setDescription(product.getProductDescription());

        return productInCartDTO;
    }

    @PutMapping(value = "/addToCart")
    public AddProductToCartDTO addCartItem(@RequestBody ProductToCartRequestDTO requestFromUI) {
        AddProductToCartDTO responseJSON = new AddProductToCartDTO();
        AddProductToCartResponse serviceAnswer = cartService.addToCart(requestFromUI.getProductID(), requestFromUI.getCartID());

        if (serviceAnswer.hasDBErrors() || serviceAnswer.hasValidationErrors()) {
            responseJSON.setValidationErrors(serviceAnswer.getValidationErrorsList());
            responseJSON.setDbErrors(serviceAnswer.getDbErrorsList());
            responseJSON.setStat(Status.FAILED);
        } else {
            responseJSON.setStat(Status.SUCCESS);
            responseJSON.setCartID(requestFromUI.getCartID());
            responseJSON.setProductID(requestFromUI.getProductID());
        }

        return responseJSON;
    }

    @DeleteMapping(value = "/cart/removeProduct")
    public DeleteProductFromCartDTO removeItem(@RequestParam Long productId, @RequestParam Integer cartId) {
        DeleteProductFromCartDTO responseJSON = new DeleteProductFromCartDTO();

        RemoveProductFromCartRequest removeProductFromCartRequest = new RemoveProductFromCartRequest();
        removeProductFromCartRequest.setCartID(cartId);
        removeProductFromCartRequest.setProductID(productId);

        if (cartId != null && productId != null) {
            cartService.removeItemFromCart(removeProductFromCartRequest);
            responseJSON.setProductID(productId);
            responseJSON.setCartID(cartId);
            responseJSON.setStat(Status.SUCCESS);
        } else {
            responseJSON.setStat(Status.FAILED);
        }
        return responseJSON;
    }

    @DeleteMapping(value = "/cart/clearCart")
    public DeleteAllItemsDTO clearCart(@RequestParam Integer cartId) {
        DeleteAllItemsDTO responseJSON = new DeleteAllItemsDTO();
        CartFindRequest cartFindRequest = new CartFindRequest();
        cartFindRequest.setCartId(cartId);

        if (cartFindRequest != null) {
            responseJSON.setStat(Status.SUCCESS);
            cartService.clearCart(cartFindRequest);
        } else {
            responseJSON.setStat(Status.FAILED);
        }
        return responseJSON;
    }
}

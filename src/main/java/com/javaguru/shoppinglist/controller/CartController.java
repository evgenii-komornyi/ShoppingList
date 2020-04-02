package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.cart.request.CartCreateRequest;
import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.cart.response.CartCreateResponse;
import com.javaguru.shoppinglist.domain.cart.response.CartResponseAnswer;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.dto.cartDTO.AddProductToCartDTO;
import com.javaguru.shoppinglist.dto.cartDTO.CreateCartDTOCart;
import com.javaguru.shoppinglist.dto.productDTO.Status;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@Transactional
@EnableWebMvc
public class CartController {
    @Autowired
    private final CartService cartService;

    @Autowired
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/carts")
    public ModelAndView cartList() {
        ModelAndView modelAndView = new ModelAndView("cartList");
        List<Cart> carts = cartService.getAllCarts();

        modelAndView.addObject("title", "CartList");
        modelAndView.addObject("carts", carts);
        return modelAndView;
    }

    @GetMapping("/cart/addCart")
    public ModelAndView getCreateCartForm(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("createCart");
        modelAndView.addObject("title", "Add Cart");
        model.addAttribute("cart", new CartCreateRequest());
        return modelAndView;
    }

    @PostMapping("/cart/addCart")
    @Transactional(propagation = Propagation.NEVER)
    public CreateCartDTOCart addCart(@ModelAttribute("cart") CartCreateRequest createRequest) {
        CreateCartDTOCart responseJSON = new CreateCartDTOCart();
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

    @GetMapping("/cart/{cartId}")
    public ModelAndView singleProduct(@PathVariable("cartId") Integer id) {
        ModelAndView modelAndView = new ModelAndView("cart");

        CartFindRequest findRequest = new CartFindRequest();
        findRequest.setCartId(id);

        Cart cart = cartService.findCartByID(findRequest).getCart();

        List<Product> productsInCart = cart.getProductsInCart();

        modelAndView.addObject("title", "Products in " + cart.getCartName());
        modelAndView.addObject("cartName", cart.getCartName());
        modelAndView.addObject("products", productsInCart);
        return modelAndView;
    }

    @PutMapping("/addToCart-{cartId}/{productID}")
    public AddProductToCartDTO addCartItem(@PathVariable("productID") Long productID, @PathVariable("cartId") Integer cartId) {
        AddProductToCartDTO responseJSON = new AddProductToCartDTO();

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(productID);

        CartFindRequest findRequestCart = new CartFindRequest();
        findRequestCart.setCartId(cartId);

        CartResponseAnswer serviceAnswer = cartService.addToCart(productFindRequest, findRequestCart);

        if (serviceAnswer.getResponseStatus().equals("FAILED")) {
            responseJSON.setStat(Status.FAILED);
        } else {
            responseJSON.setStat(Status.SUCCESS);
            responseJSON.setCartID(cartId);
            responseJSON.setProductID(productID);
        }

        return responseJSON;
    }

}

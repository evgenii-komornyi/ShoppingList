package com.javaguru.shoppinglist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ViewController {
    @RequestMapping(value = "/products")
    public String getProductsPage() {
        return "templates/productsList";
    }

    @RequestMapping(value = "/product")
    public String getProductInfoPage() {
        return "templates/productInfo";
    }

    @RequestMapping(value = "/addNewProduct")
    public String getFormNewProductPage() { return "templates/addProduct"; }

    @RequestMapping(value = "/carts")
    public String cartList() {
        return "templates/cartList";
    }

    @RequestMapping(value = "/cart")
    public String getCartInfoPage() {
        return "templates/cartInfo";
    }

}

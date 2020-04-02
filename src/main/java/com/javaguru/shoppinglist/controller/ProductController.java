package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import com.javaguru.shoppinglist.domain.product.response.ProductCreateResponse;
import com.javaguru.shoppinglist.domain.product.response.ProductFindResponse;
import com.javaguru.shoppinglist.dto.productDTO.CreateProductDTO;
import com.javaguru.shoppinglist.dto.productDTO.Status;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Transactional
@EnableWebMvc
public class ProductController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }


    @GetMapping("/products")
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView("productsList");
        List<Product> products = productService.getAllDatabase();
        List<Cart> carts = cartService.getAllCarts();

        modelAndView.addObject("title", "Products List");
        modelAndView.addObject("products", products);
        modelAndView.addObject("carts", carts);
        return modelAndView;
    }

    @GetMapping("/product/{productID}")
    public ModelAndView singleProduct(@PathVariable("productID") Long id) {
        ModelAndView modelAndView = new ModelAndView("productInfo");

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(id);

        Product product = productService.findByID(productFindRequest).getFoundProduct();

        modelAndView.addObject("title", product.getProductName() + " - Product Information");
        modelAndView.addObject("productName", product.getProductName());
        modelAndView.addObject("productDescription", product.getProductDescription());
        return modelAndView;
    }

    @PostMapping("/product/addProduct")
    @Transactional(propagation = Propagation.NEVER)
    public CreateProductDTO addProduct(@ModelAttribute("product") ProductCreateRequest productCreateRequest) {
        CreateProductDTO responseJSON = new CreateProductDTO();
        ProductCreateResponse product = productService.addProduct(productCreateRequest);

        if (product.hasDBErrors() || product.hasValidationErrors()) {
            responseJSON.setStat(Status.FAILED);
            responseJSON.setValidationErrors(product.getValidationErrors());
            responseJSON.setDbErrors(product.getDBErrors());
        } else {
            responseJSON.setStat(Status.SUCCESS);
            responseJSON.setCreateResponse(product);
        }

        return responseJSON;
    }

    @GetMapping("/product/editProduct/{productID}")
    public ModelAndView getUpdateProductForm(ModelMap model, @PathVariable("productID") Long id) {
        ModelAndView modelAndView = new ModelAndView("editProduct");
        modelAndView.addObject("title", "Edit Product");

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(id);

        Product product = productService.findByID(productFindRequest).getFoundProduct();

        model.addAttribute("productName", product.getProductName());
        model.addAttribute("productPrice", product.getProductRegularPrice());
        model.addAttribute("productCategory", product.getCategory());
        model.addAttribute("productDiscount", product.getProductDiscount());
        model.addAttribute("productDescription", product.getProductDescription());
        model.addAttribute("ed_product", new ProductUpdateRequest());
        return modelAndView;
    }

    @PutMapping("/product/editProduct/{productID}")
    @Transactional(propagation = Propagation.NEVER)
    public UpdateControllerResponse editProduct(@ModelAttribute("ed_product") @Valid ProductUpdateRequest updateRequest, BindingResult result) {
        UpdateControllerResponse response = new UpdateControllerResponse();
        ModelAndView modelAndView;
        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(updateRequest.getProductID());

        ProductFindResponse findResponse = productService.findByID(productFindRequest);

        if (result.hasErrors()) {
            modelAndView = new ModelAndView("403");
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            response.setModelAndView(modelAndView);
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            if (findResponse.getFoundProduct() != null) {
                response.setValidated(true);
                response.setUpdateResponse(productService.updateByID(updateRequest));
            }
        }
        modelAndView = new ModelAndView("editProduct");
        response.setModelAndView(modelAndView);
        return response;
    }

    @DeleteMapping("/product/{productID}")
    public void deleteProduct(@PathVariable("productID") Long id) {
        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(id);

        if (productService.deleteByID(productFindRequest)) {
            System.out.println("Product was successfuly deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }
}

package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import com.javaguru.shoppinglist.domain.product.response.ProductCreateResponse;
import com.javaguru.shoppinglist.domain.product.response.ProductUpdateResponse;
import com.javaguru.shoppinglist.dto.cartDTO.CartDTO;
import com.javaguru.shoppinglist.dto.productDTO.*;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
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

    @GetMapping(value = "/allProducts")
    public ListProductsDTO getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = productService.getAllDatabase();

        for (Product product : productList) {
            productDTOList.add(convertProductToDTO(product));
        }

        ListProductsDTO responseJSON = new ListProductsDTO();
        responseJSON.setProducts(productDTOList);

        List<CartDTO> cartDTOList = new ArrayList<>();
        List<Cart> cartList = cartService.getAllCarts();

        for (Cart cart : cartList) {
            cartDTOList.add(convertCartToDTO(cart));
        }
        responseJSON.setCarts(cartDTOList);

        return responseJSON;
    }

    private ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getProductID());
        productDTO.setName(product.getProductName());
        productDTO.setRegPrice(product.getProductRegularPrice());
        productDTO.setCategory(product.getCategory());
        productDTO.setDiscount(product.getProductDiscount());
        productDTO.setActPrice(product.calculateActualPrice());
        productDTO.setDescription(product.getProductDescription());

        return productDTO;
    }

    private CartDTO convertCartToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();

        cartDTO.setId(cart.getCartId());
        cartDTO.setName(cart.getCartName());

        return cartDTO;
    }

    @GetMapping(value = "/product/{productID}")
    public FindProductDTO findById(@PathVariable("productID") Long id) {
        FindProductDTO responseJSON = new FindProductDTO();

        ProductFindRequest findRequest = new ProductFindRequest();
        findRequest.setProductID(id);

        responseJSON.setProduct(convertProductToDTO(productService.findByID(findRequest).getFoundProduct()));

        return responseJSON;
    }

    @PostMapping(value = "/create")
    @Transactional(propagation = Propagation.NEVER)
    public CreateProductDTO addProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        CreateProductDTO responseJSON = new CreateProductDTO();
        ProductCreateResponse product = productService.addProduct(productCreateRequest);

        if (product.hasDBErrors() || product.hasValidationErrors()) {
            responseJSON.setValidationErrors(product.getValidationErrors());
            responseJSON.setDbErrors(product.getDBErrors());
        } else {
            responseJSON.setCreateResponse(product);
        }

        return responseJSON;
    }

    @PutMapping(value = "/product/{productID}")
    @Transactional(propagation = Propagation.NEVER)
    public UpdateProductDTO updateProduct(@PathVariable("productID") Long id, @RequestBody ProductUpdateRequest updateRequest) {
        UpdateProductDTO responseJSON = new UpdateProductDTO();
        ProductFindRequest findRequest = new ProductFindRequest();
        findRequest.setProductID(id);

        if (productService.findByID(findRequest).getFoundProduct() != null) {
            updateRequest.setProductID(id);

            ProductUpdateResponse updateResponse = productService.updateByID(updateRequest);

            if (updateResponse.hasValidationErrors() || updateResponse.hasDBErrors()) {
                responseJSON.setValidationErrors(updateResponse.getValidationErrors());
                responseJSON.setDbErrors(updateResponse.getDBErrors());
            } else {
                responseJSON.setProduct(convertProductToDTO(updateResponse.getUpdatedProduct()));
            }
        }

        return responseJSON;
    }

    @DeleteMapping(value = "/product/{productID}")
    public DeleteProductDTO deleteProduct(@PathVariable("productID") Long id) {
        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(id);

        DeleteProductDTO responseJSON = new DeleteProductDTO();

        if (productService.deleteByID(productFindRequest)) {
            responseJSON.setStatus(Status.SUCCESS);
        } else {
            responseJSON.setStatus(Status.FAILED);
        }
        return responseJSON;
    }
}

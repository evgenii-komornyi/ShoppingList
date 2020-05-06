package com.javaguru.shoppinglist.service.validationCart;

import com.javaguru.shoppinglist.domain.cart.Cart;
import com.javaguru.shoppinglist.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductToCartValidation {
    public List<CartValidationErrors> validateAddProductToCart(Cart cart, Product product) {
        List<CartValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateProductExist(product));
        allErrors.addAll(validateCartExist(cart));
        allErrors.addAll(validateProductInCartExist(cart.getProductsInCart(), product));

        return allErrors;
    }

    private List<CartValidationErrors> validateProductExist(Product product) {
        List<CartValidationErrors> errorsList = new ArrayList<>();

        if (product == null) {
            errorsList.add(CartValidationErrors.PRODUCT_NOT_EXIST);
        }
        return errorsList;
    }

    private List<CartValidationErrors> validateCartExist(Cart cart) {
        List<CartValidationErrors> errorsList = new ArrayList<>();

        if (cart == null) {
            errorsList.add(CartValidationErrors.CART_NOT_EXIST);
        }
        return errorsList;
    }

    private List<CartValidationErrors> validateProductInCartExist(List<Product> productsList, Product product) {
        List<CartValidationErrors> errorsList = new ArrayList<>();

        if (productsList.contains(product)) {
            errorsList.add(CartValidationErrors.DUPLICATE_ENTRY);
        }
        return errorsList;
    }

}

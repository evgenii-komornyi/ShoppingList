package com.javaguru.shoppinglist.service.validationCart;

import com.javaguru.shoppinglist.domain.cart.request.CartFindRequest;
import com.javaguru.shoppinglist.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmptyCartValidation {
    public List<CartValidationErrors> validateEmptyCart(CartFindRequest findItemsInCartRequest) {
        List<CartValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateCartForEmpty(findItemsInCartRequest.getProductsInCart()));

        return allErrors;
    }

    private List<CartValidationErrors> validateCartForEmpty(List<Product> products) {
        List<CartValidationErrors> errorsList = new ArrayList<>();

        if (!products.isEmpty()) {
            errorsList.add(CartValidationErrors.CART_NOT_EMPTY);
        }

        return errorsList;
    }
}

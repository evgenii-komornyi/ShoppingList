package com.javaguru.shoppinglist.service.validationProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidation {
    private ProductCreateRequestValidation productCreateRequestValidation;
    private ProductFindRequestValidation productFindRequestValidation;
    private ProductUpdateRequestValidation productUpdateRequestValidation;

    @Autowired
    public ProductValidation(ProductCreateRequestValidation productCreateRequestValidation, ProductFindRequestValidation productFindRequestValidation, ProductUpdateRequestValidation productUpdateRequestValidation) {
        this.productCreateRequestValidation = productCreateRequestValidation;
        this.productFindRequestValidation = productFindRequestValidation;
        this.productUpdateRequestValidation = productUpdateRequestValidation;
    }

    public ProductCreateRequestValidation getCreateRequestValidation() {
        return productCreateRequestValidation;
    }

    public ProductFindRequestValidation getFindRequestValidation() {
        return productFindRequestValidation;
    }

    public ProductUpdateRequestValidation getUpdateRequestValidation() {
        return productUpdateRequestValidation;
    }
}


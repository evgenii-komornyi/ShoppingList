package com.javaguru.shoppinglist.dto.productDTO;

import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidationErrors;

import java.util.List;

public abstract class ProductBasicDTO {
    private List<ProductValidationErrors> productValidationErrors;
    private List<DBErrors> dbErrors;

    public List<ProductValidationErrors> getValidationErrors() {
        return productValidationErrors;
    }

    public void setValidationErrors(List<ProductValidationErrors> productValidationErrors) {
        this.productValidationErrors = productValidationErrors;
    }

    public List<DBErrors> getDbErrors() {
        return dbErrors;
    }

    public void setDbErrors(List<DBErrors> dbErrors) {
        this.dbErrors = dbErrors;
    }
}

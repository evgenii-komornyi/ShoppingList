package com.javaguru.shoppinglist.service.validationProduct;

import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductFindRequestValidation {
    public List<ProductValidationErrors> validateFindRequest(ProductFindRequest findFieldRequest) {
        return new ArrayList<>(validateSearchCriteria(findFieldRequest));
    }

    private List<ProductValidationErrors> validateSearchCriteria(ProductFindRequest searchCriteria) {
        List<ProductValidationErrors> allErrors = new ArrayList<>();

        if (searchCriteria.getProductID() == null
                && searchCriteria.getProductName() == null
                && searchCriteria.getProductCategory() == null) {
            allErrors.add(ProductValidationErrors.NO_SEARCH_CRITERIA);
        }

        if (searchCriteria.getProductID() != null
                && searchCriteria.getProductName() != null
                && searchCriteria.getProductCategory() != null) {
            allErrors.add(ProductValidationErrors.CONFLICT_SEARCH_PARAMS);
        }
        return allErrors;
    }
}

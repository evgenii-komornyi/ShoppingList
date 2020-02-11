package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.product.request.FindRequest;

import java.util.ArrayList;
import java.util.List;

public class FindRequestValidation {
    public List<ValidationErrors> validateFindRequest(FindRequest findFieldRequest) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        allErrors.addAll(validateSearchCriteria(findFieldRequest));

        return allErrors;
    }

    private List<ValidationErrors> validateSearchCriteria(FindRequest searchCriteria) {
        List<ValidationErrors> allErrors = new ArrayList<>();

        if (searchCriteria.getProductID() == null
                && searchCriteria.getProductName() == null
                && searchCriteria.getProductCategory() == null) {
            allErrors.add(ValidationErrors.NO_SEARCH_CRITERIA);
        }

        if (searchCriteria.getProductID() != null
                && searchCriteria.getProductName() != null
                && searchCriteria.getProductCategory() != null) {
            allErrors.add(ValidationErrors.CONFLICT_SEARCH_PARAMS);
        }
        return allErrors;
    }
}

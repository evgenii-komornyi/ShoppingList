package com.javaguru.shoppinglist.service.validation;

public class Validation {
    private CreateRequestValidation createRequestValidation;
    private FindRequestValidation findRequestValidation;
    private UpdateRequestValidation updateRequestValidation;

    public Validation(CreateRequestValidation createRequestValidation, FindRequestValidation findRequestValidation, UpdateRequestValidation updateRequestValidation) {
        this.createRequestValidation = createRequestValidation;
        this.findRequestValidation = findRequestValidation;
        this.updateRequestValidation = updateRequestValidation;
    }

    public CreateRequestValidation getCreateRequestValidation() {
        return createRequestValidation;
    }

    public FindRequestValidation getFindRequestValidation() {
        return findRequestValidation;
    }

    public UpdateRequestValidation getUpdateRequestValidation() {
        return updateRequestValidation;
    }
}


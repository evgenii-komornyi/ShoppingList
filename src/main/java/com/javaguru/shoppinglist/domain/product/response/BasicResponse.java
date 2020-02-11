package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.service.validation.ValidationErrors;

import java.util.List;
import java.util.Objects;

public abstract class BasicResponse {
    private List<ValidationErrors> validationErrors;

    public boolean hasErrors() {
        return (validationErrors != null && !validationErrors.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicResponse that = (BasicResponse) o;
        return Objects.equals(validationErrors, that.validationErrors);
    }

    @Override
    public String toString() {
        return "BasicResponse{" +
                "validationErrors=" + validationErrors +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationErrors);
    }

    public List<ValidationErrors> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationErrors> validationErrors) {
        this.validationErrors = validationErrors;
    }
}

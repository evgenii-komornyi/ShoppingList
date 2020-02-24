package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validation.ValidationErrors;

import java.util.List;
import java.util.Objects;

public abstract class BasicResponse {
    private List<ValidationErrors> validationErrors;
    private List<DBErrors> DBErrors;

    public boolean hasValidationErrors() {
        return (validationErrors != null && !validationErrors.isEmpty());
    }
    public boolean hasDBRrrors() {
        return (DBErrors != null && !DBErrors.isEmpty());
    }

    @Override
    public String toString() {
        return "BasicResponse{" +
                "validationErrors=" + validationErrors +
                ", DBErrors=" + DBErrors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicResponse that = (BasicResponse) o;
        return Objects.equals(validationErrors, that.validationErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationErrors, DBErrors);
    }

    public List<ValidationErrors> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationErrors> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<DBErrors> getDBErrors() {
        return DBErrors;
    }

    public void setDBErrors(List<DBErrors> DBErrors) {
        this.DBErrors = DBErrors;
    }
}

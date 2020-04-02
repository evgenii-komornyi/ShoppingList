package com.javaguru.shoppinglist.dto.cartDTO;

import com.javaguru.shoppinglist.dto.productDTO.Status;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.service.validationCart.CartValidationErrors;

import java.util.List;

public abstract class CartBasicDTO {
    private Status stat;
    private List<CartValidationErrors> validationErrors;
    private List<DBErrors> dbErrors;

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }

    public List<CartValidationErrors> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<CartValidationErrors> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<DBErrors> getDbErrors() {
        return dbErrors;
    }

    public void setDbErrors(List<DBErrors> dbErrors) {
        this.dbErrors = dbErrors;
    }
}

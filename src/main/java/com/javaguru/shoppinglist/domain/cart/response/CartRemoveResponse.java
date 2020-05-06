package com.javaguru.shoppinglist.domain.cart.response;

import java.util.Objects;

public class CartRemoveResponse extends CartBasicResponse {
    private boolean deleted;

    @Override
    public String toString() {
        return "CartRemoveResponse{" +
                "deleted=" + deleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartRemoveResponse that = (CartRemoveResponse) o;
        return deleted == that.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deleted);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}


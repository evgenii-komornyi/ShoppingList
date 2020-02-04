package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product.Request.FindRequest;
import com.javaguru.shoppinglist.domain.Product.Request.UpdateRequest;

import java.util.List;

public interface Repository<T> {
    T create(T item);
    List<T> read(FindRequest findRequest);
    T readByID(FindRequest findRequest);
    T updateByID(UpdateRequest updateRequest);
    boolean delete(FindRequest itemID);
}

package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;

import java.util.List;

public interface Repository<T> {
    T create(T item);
    List<T> read(FindRequest findRequest);
    Product readByID(FindRequest findRequest);
    T updateByID(UpdateRequest updateRequest);
    boolean delete(FindRequest itemID);
    List<Product> findAll();
}

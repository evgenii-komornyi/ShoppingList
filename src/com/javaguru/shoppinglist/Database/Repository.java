package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;

import java.util.Map;

public interface Repository<T> {
    T create(T item);
    Map<Long, T> read(FindRequest item);
    Map<Long, T> update(UpdateRequest item);
    boolean delete(FindRequest item);
}

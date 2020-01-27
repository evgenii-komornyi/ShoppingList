package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;

import java.util.List;
import java.util.Map;

public interface Repository<T> {
    T create(T item) throws Exception;
    List<T> read(FindRequest itemCriteria);
    List<T> update(UpdateRequest itemCriteria);
    boolean delete(FindRequest itemID);
}

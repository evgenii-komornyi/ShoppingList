package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;

import java.util.List;

public interface ProductRepository<T> {
    T create(T item);
    List<T> read(ProductFindRequest productFindRequest);
    Product readByID(ProductFindRequest productFindRequest);
    T updateByID(ProductUpdateRequest updateRequest);
    boolean delete(ProductFindRequest itemID);
    List<Product> findAll();
}

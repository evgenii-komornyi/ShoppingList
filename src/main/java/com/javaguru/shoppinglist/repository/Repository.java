package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;

import java.util.List;

public interface Repository {
    Product create(Product product);
    List<Product> read(ProductFindRequest productFindRequest);
    Product readByID(ProductFindRequest productFindRequest);
    Product updateByID(ProductUpdateRequest updateRequest);
    boolean delete(ProductFindRequest itemID);
    List<Product> findAll();
}

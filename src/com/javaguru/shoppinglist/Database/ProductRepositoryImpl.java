package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;

import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryImpl implements Repository<Product> {
    private static Map<Long, Product> database = new HashMap<>();

    @Override
    public Product create(Product item) {
        if (!database.containsValue(item)) {
            database.put(item.getProductID(), item);
            return item;
        } else {
            return null;
        }
    }

    @Override
    public Map<Long, Product> read(FindRequest item) {
        return null;
    }

    @Override
    public Map<Long, Product> update(UpdateRequest item) {
        return null;
    }

    @Override
    public boolean delete(FindRequest item) {
        return false;
    }

    public Map<Long, Product> getAllDatabase() {
        return database;
    }
}

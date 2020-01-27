package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Response.CreateResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryImpl implements Repository<Product> {
    private static Map<Long, Product> database = new HashMap<>();

    @Override
    public Product create(Product item) throws Exception {
        if (!database.containsValue(item)) {
            for (Product product : database.values()) {
                if (product.getProductName().toLowerCase().equals(item.getProductName().toLowerCase())) {
                    throw new Exception("Duplicate name");
                }
            }
            database.put(item.getProductID(), item);
            return item;
        } else {
            throw new Exception("Duplicate key");
        }
    }

    @Override
    public List<Product> read(FindRequest itemCriteria) {
        List<Product> databaseForFind = new ArrayList<>(database.values());
        List<Product> foundItems = new ArrayList<>();

        Long sProductID;
        String sProductName;
        BigDecimal sProductPrice;
        String sProductCategory;

        for (Product product : databaseForFind) {
            sProductID = ((itemCriteria.getProductID() != null) ? itemCriteria.getProductID() : product.getProductID());
            sProductName = ((itemCriteria.getProductName() != null) ? itemCriteria.getProductName() : product.getProductName());
            sProductPrice = ((itemCriteria.getProductPrice() != null) ? itemCriteria.getProductPrice() : product.getProductPrice());
            sProductCategory = ((itemCriteria.getProductCategory() != null) ? String.valueOf(itemCriteria.getProductCategory()) : String.valueOf(product.getProductCategory()));

            if (sProductID.compareTo(product.getProductID()) == 0
                    && sProductName.equals(product.getProductName())
                    && sProductPrice.compareTo(product.getProductPrice()) == 0
                    && sProductCategory.equals(String.valueOf(product.getProductCategory()))) {
                foundItems.add(product);
            }
        }

        return foundItems;
    }

    @Override
    public List<Product> update(UpdateRequest itemCriteria) {
        return null;
    }

    @Override
    public boolean delete(FindRequest itemID) {
        return false;
    }

    public Map<Long, Product> getAllDatabase() {
        return database;
    }
}

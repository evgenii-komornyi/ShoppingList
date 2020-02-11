package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;

import java.math.BigDecimal;
import java.util.*;

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
    public List<Product> read(FindRequest findRequest) {
        List<Product> databaseForFind = new ArrayList<>(database.values());
        List<Product> foundItems = new ArrayList<>();

        Long sProductID;
        String sProductName;
        BigDecimal sProductPrice;
        String sProductCategory;

        for (Product product : databaseForFind) {
            sProductID = ((findRequest.getProductID() != null) ? findRequest.getProductID() : product.getProductID());
            sProductName = ((findRequest.getProductName() != null) ? findRequest.getProductName() : product.getProductName());
            sProductPrice = ((findRequest.getProductPrice() != null) ? findRequest.getProductPrice() : product.getProductPrice());
            sProductCategory = ((findRequest.getProductCategory() != null) ? String.valueOf(findRequest.getProductCategory()) : String.valueOf(product.getProductCategory()));

            if (sProductID.compareTo(product.getProductID()) == 0
                    && sProductName.equalsIgnoreCase(product.getProductName())
                    && sProductPrice.compareTo(product.getProductPrice()) == 0
                    && sProductCategory.equalsIgnoreCase(String.valueOf(product.getProductCategory()))) {
                foundItems.add(product);
            }
        }

        return foundItems;
    }

    @Override
    public Product readByID(FindRequest findRequest) {
        Product product = null;
        if (database.containsKey(findRequest.getProductID())) {
            product = database.get(findRequest.getProductID());
        }
        return product;
    }

    @Override
    public Product updateByID(UpdateRequest updateRequest) {
        Product product = null;
        if (database.containsKey(updateRequest.getProductID())) {
            product = database.get(updateRequest.getProductID());

            product.setProductName(updateRequest.getProductName());
            product.setProductPrice(updateRequest.getProductPrice());
            product.setProductCategory(ProductCategory.valueOf(updateRequest.getProductCategory()));
            product.setProductDiscount(updateRequest.getProductDiscount());
            product.setProductDescription(updateRequest.getProductDescription());
        }
        return product;
    }

    @Override
    public boolean delete(FindRequest findRequest) {
        if (database.containsKey(findRequest.getProductID())) {
            database.remove(findRequest.getProductID());
            return true;
        } else {
            return false;
        }
    }

    public Map<Long, Product> getAllDatabase() {
        return database;
    }

    public void drop() {
        database.clear();
    }
}

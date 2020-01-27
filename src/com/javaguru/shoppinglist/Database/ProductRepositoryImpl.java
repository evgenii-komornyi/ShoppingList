package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;

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
                    && sProductName.equals(product.getProductName())
                    && sProductPrice.compareTo(product.getProductPrice()) == 0
                    && sProductCategory.equals(String.valueOf(product.getProductCategory()))) {
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
    public List<Product> update(UpdateRequest updateRequest) {
        List<Product> listOfUpdatedProducts = read(updateRequest);

        for (Product product : listOfUpdatedProducts) {
            if (updateRequest.getNewProductPrice() != null)
            {
                product.setProductPrice(updateRequest.getNewProductPrice());
            }

            if (updateRequest.getNewProductDiscount() != null)
            {
                product.setProductDiscount(updateRequest.getNewProductDiscount());
            }

            if (updateRequest.getNewDescription() != null)
            {
                product.setProductDescription(updateRequest.getNewDescription());
            }
        }
        return listOfUpdatedProducts;
    }

    @Override
    public Product updateByID(UpdateRequest updateRequest) {
        Product product = null;
        if (database.containsKey(updateRequest.getProductID())) {
            product = database.get(updateRequest.getProductID());
            if (updateRequest.getNewProductPrice() != null) {
                product.setProductPrice(updateRequest.getNewProductPrice());
            }
            if (updateRequest.getNewProductDiscount() != null) {
                product.setProductDiscount(updateRequest.getNewProductDiscount());
            }
            if (updateRequest.getNewDescription() != null) {
                product.setProductDescription(updateRequest.getNewDescription());
            }
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
}

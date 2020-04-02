package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@Profile("memory")
public class ProductInMemoryRepositoryImpl implements ProductRepository<Product> {
    private static Long countID = 0L;
    private static Map<Long, Product> database = new HashMap<>();

    @Override
    public Product create(Product item) {
        if (!database.containsValue(item)) {
            Long id = incrementCountID();
            item.setProductID(id);
            database.put(id, item);
            return item;
        } else {
            return null;
        }
    }

    private Long incrementCountID() {
        return countID++;
    }

    @Override
    public List<Product> read(ProductFindRequest productFindRequest) {
        List<Product> databaseForFind = new ArrayList<>(database.values());
        List<Product> foundItems = new ArrayList<>();

        Long sProductID;
        String sProductName;
        BigDecimal sProductPrice;
        String sProductCategory;

        for (Product product : databaseForFind) {
            sProductID = ((productFindRequest.getProductID() != null) ? productFindRequest.getProductID() : product.getProductID());
            sProductName = ((productFindRequest.getProductName() != null) ? productFindRequest.getProductName() : product.getProductName());
            sProductPrice = ((productFindRequest.getProductPrice() != null) ? productFindRequest.getProductPrice() : product.getProductRegularPrice());
            sProductCategory = ((productFindRequest.getProductCategory() != null) ? String.valueOf(productFindRequest.getProductCategory()) : String.valueOf(product.getCategory()));

            if (sProductID.compareTo(product.getProductID()) == 0
                    && sProductName.equalsIgnoreCase(product.getProductName())
                    && sProductPrice.compareTo(product.getProductRegularPrice()) == 0
                    && sProductCategory.equalsIgnoreCase(String.valueOf(product.getCategory()))) {
                foundItems.add(product);
            }
        }

        return foundItems;
    }

    @Override
    public Product readByID(ProductFindRequest productFindRequest) {
        Product product = null;
        if (database.containsKey(productFindRequest.getProductID())) {
            product = database.get(productFindRequest.getProductID());
        }
        return product;
    }

    @Override
    public Product updateByID(ProductUpdateRequest updateRequest) {
        Product product = null;
        if (database.containsKey(updateRequest.getProductID())) {
            product = database.get(updateRequest.getProductID());

            product.setProductName(updateRequest.getProductName());
            product.setProductRegularPrice(updateRequest.getProductPrice());
            product.setCategory(ProductCategory.valueOf(updateRequest.getProductCategory()));
            product.setProductDiscount(updateRequest.getProductDiscount());
            product.setProductDescription(updateRequest.getProductDescription());
        }
        return product;
    }

    @Override
    public boolean delete(ProductFindRequest productFindRequest) {
        if (database.containsKey(productFindRequest.getProductID())) {
            database.remove(productFindRequest.getProductID());
            return true;
        } else {
            return false;
        }
    }

    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    public void drop() {
        database.clear();
    }
}

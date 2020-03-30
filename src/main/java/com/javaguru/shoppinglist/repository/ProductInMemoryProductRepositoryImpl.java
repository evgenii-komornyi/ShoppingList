package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@Profile("memory")
public class ProductInMemoryProductRepositoryImpl implements ProductRepository<Product> {
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
            sProductPrice = ((findRequest.getProductPrice() != null) ? findRequest.getProductPrice() : product.getProductRegularPrice());
            sProductCategory = ((findRequest.getProductCategory() != null) ? String.valueOf(findRequest.getProductCategory()) : String.valueOf(product.getProductCategory()));

            if (sProductID.compareTo(product.getProductID()) == 0
                    && sProductName.equalsIgnoreCase(product.getProductName())
                    && sProductPrice.compareTo(product.getProductRegularPrice()) == 0
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
            product.setProductRegularPrice(updateRequest.getProductPrice());
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

    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    public void drop() {
        database.clear();
    }
}

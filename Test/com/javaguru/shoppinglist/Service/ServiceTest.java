package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceTest {
    private CreateRequest milk;
    private CreateRequest meat;
    private CreateRequest bread;
    private CreateRequest cheese;

    private Service service;

    private FindRequest findRequest;
    private UpdateRequest updateRequest;

    @Before
    public void setUp() {
        service = new Service();
        findRequest = new FindRequest();
        updateRequest = new UpdateRequest();

        milk = new CreateRequest();
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("43.2"));
        milk.setProductCategory("MILK");
        milk.setProductDiscount(new BigDecimal("45.3"));
        milk.setProductDescription("Good cow milk from Latvia");

        meat = new CreateRequest();
        meat.setProductName("Beef");
        meat.setProductPrice(new BigDecimal("25.8"));
        meat.setProductCategory("MEAT");
        meat.setProductDiscount(new BigDecimal("10"));
        meat.setProductDescription("Fresh beef form Russia");

        bread = new CreateRequest();
        bread.setProductName("White bread");
        bread.setProductPrice(new BigDecimal("1.0"));
        bread.setProductCategory("BREAD");
        bread.setProductDescription("Fresh bread from Riga");

        cheese = new CreateRequest();
        cheese.setProductName("Cheese");
        cheese.setProductPrice(new BigDecimal("15"));
        cheese.setProductCategory("MILK");
        cheese.setProductDescription("Cheese from the Russia");
    }

    @Test
    public void testToAddProductToDatabase() {
        service.addProduct(milk);
        service.addProduct(meat);
        service.addProduct(bread);

        Map<Long, Product> productList = service.getAllDatabase();

        assertEquals(3, productList.size());
    }

    @Test
    public void getAllDatabase() {
        service.addProduct(milk);
        service.addProduct(meat);
        service.addProduct(bread);

        assertEquals(3, service.getAllDatabase().size());
    }

    @Test
    public void findByID() {
        service.addProduct(milk);
        service.addProduct(meat);

        findRequest.setProductID(0L);

        assertEquals(milk.getProductName(), service.findByID(findRequest).getFoundProduct().getProductName());
    }

    @Test
    public void findByCategory() {
        service.addProduct(milk);
        service.addProduct(meat);
        service.addProduct(cheese);

        updateRequest.setProductCategory(ProductCategory.MILK);

        assertEquals(2, service.findByCategory(updateRequest).getListOfFoundProducts().size());
    }

    @Test
    public void updateByID() {
        Long id = service.addProduct(milk).getProduct().getProductID();
        service.addProduct(meat);
        service.addProduct(bread);

        updateRequest.setProductID(id);
        BigDecimal expectedDiscount = new BigDecimal("10.5");
        updateRequest.setNewProductDiscount(expectedDiscount);

        service.updateByID(updateRequest);

        assertEquals(expectedDiscount, service.updateByID(updateRequest).getUpdatedProduct().getProductDiscount());
    }

    @Test
    public void updateByCategory() {
        service.addProduct(milk);
        service.addProduct(meat);

        updateRequest.setProductCategory(ProductCategory.MILK);
        updateRequest.setNewDescription("Its a good milk");

        assertEquals(1, service.updateByCategory(updateRequest).getListOfUpdatedProducts().size());
    }

    @Test
    public void deleteByID() {
        Long id = service.addProduct(milk).getProduct().getProductID();

        findRequest.setProductID(id);

        assertTrue(service.deleteByID(findRequest));
    }
}
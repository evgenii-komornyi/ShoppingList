package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Response.CreateResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ServiceTest {
    private CreateRequest milk;
    private CreateRequest meat;
    private CreateRequest bread;

    @Before
    public void setUp() {
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
    }

    @Test
    public void testToAddProductToDatabase() {
        Service service = new Service();
        service.addProduct(milk);
        service.addProduct(meat);
        service.addProduct(bread);

        Map<Long, Product> productList = service.getAllDatabase();
        int expectedSize = 3;

        assertEquals(expectedSize, productList.size());
    }
}
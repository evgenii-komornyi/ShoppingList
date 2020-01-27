package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
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
    private CreateRequest cheese;

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

        cheese = new CreateRequest();
        cheese.setProductName("Cheese");
        cheese.setProductPrice(new BigDecimal("15"));
        cheese.setProductCategory("MILK");
        cheese.setProductDescription("Cheese from the Russia");
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

    @Test
    public void getAllDatabase() {
        Service service = new Service();
        service.addProduct(milk);
        service.addProduct(meat);
        service.addProduct(bread);

        int expectedSize = 3;

        assertEquals(expectedSize, service.getAllDatabase().size());
    }

    @Test
    public void findByID() {
        Service service = new Service();
        service.addProduct(milk);
        service.addProduct(meat);

        FindRequest request = new FindRequest();
        request.setProductID(Long.valueOf(0));

        int expected = 1;

        assertEquals(expected, service.findByID(request).getListOfFoundProducts().size());
    }

    @Test
    public void findByCategory() {
        Service service = new Service();
        service.addProduct(milk);
        service.addProduct(meat);
        service.addProduct(cheese);

        FindRequest request = new FindRequest();
        request.setProductCategory(ProductCategory.MILK);

        int expected = 2;

        assertEquals(expected, service.findByCategory(request).getListOfFoundProducts().size());
    }

    @Test
    public void updateByID() {
        Service service = new Service();
        Long id = service.addProduct(milk).getProduct().getProductID();
        service.addProduct(meat);
        service.addProduct(bread);

        UpdateRequest request = new UpdateRequest();
        request.setProductID(id);
        BigDecimal expectedDiscount = new BigDecimal("10.5");
        request.setNewProductDiscount(expectedDiscount);

        System.out.println(service.updateByID(request));

        List<Product> updatedItem = service.updateByID(request).getListOfUpdatedProducts();
        BigDecimal actualDiscount = null;
        for (Product product : updatedItem) {
            actualDiscount = product.getProductDiscount();
        }

        assertEquals(expectedDiscount, actualDiscount);
    }

    @Test
    public void updateByCategory() {
        Service service = new Service();
        service.addProduct(milk);
        service.addProduct(meat);

        UpdateRequest request = new UpdateRequest();
        request.setProductCategory(ProductCategory.MILK);
        request.setNewDescription("Its a good milk");

        int expectedListLengthOfUpdatedItems = 2;

        int actualListLengthOfUpdatedItems = service.updateByCategory(request).getListOfUpdatedProducts().size();

        assertEquals(expectedListLengthOfUpdatedItems, actualListLengthOfUpdatedItems);
    }

    @Test
    public void deleteByID() {
        Service service = new Service();
        Long id = service.addProduct(milk).getProduct().getProductID();

        FindRequest request = new FindRequest();
        request.setProductID(id);

        assertTrue(service.deleteByID(request));
    }
}
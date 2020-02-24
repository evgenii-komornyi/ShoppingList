package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductInMemoryRepositoryImplTest {
    private Product milk;
    private Product meat;
    private Product bread;
    private Product cheese;

    private ProductInMemoryRepositoryImpl db;
    private FindRequest findRequest;
    private UpdateRequest updateRequest;
    @Before
    public void setUp() {
        db = new ProductInMemoryRepositoryImpl();
        findRequest = new FindRequest();
        updateRequest = new UpdateRequest();

        milk = new Product("Milk", new BigDecimal("25"), ProductCategory.MILK);
        milk.setProductDiscount(new BigDecimal("45.3"));
        milk.setProductDescription("Good cow milk from Latvia");
        meat = new Product("Beef", new BigDecimal("45.8"), ProductCategory.MEAT);
        bread = new Product("Bread", new BigDecimal("61.5"), ProductCategory.BREAD);
        cheese = new Product("Cheese", new BigDecimal("55"), ProductCategory.MILK);
    }

    @After
    public void drop() {
        db.drop();
    }

    @Test
    public void testForCreateProductInDatabase() {
        db.create(milk);

        int expectedSizeList = 1;
        List<Product> list = db.getAllDatabase();

        assertEquals(expectedSizeList, list.size());
    }

    @Test
    public void getAllDatabase() {
        db.create(milk);
        db.create(meat);
        db.create(cheese);

        int expectedSize = 3;

        assertEquals(expectedSize, db.getAllDatabase().size());
    }

    @Test
    public void readByID() {
        db.create(milk);
        db.create(meat);

        Long id = milk.getProductID();

        findRequest.setProductID(id);

        assertEquals(Collections.singletonList(milk), db.read(findRequest));
    }

    @Test
    public void readByName() {
        db.create(milk);
        db.create(meat);

        findRequest.setProductName("Milk");

        assertEquals(Collections.singletonList(milk), db.read(findRequest));
    }

    @Test
    public void readByCategory() {
        db.create(milk);
        db.create(meat);
        db.create(cheese);

        findRequest.setProductCategory(ProductCategory.MILK);

        List<Product> expected = new ArrayList<>();
        expected.add(milk);
        expected.add(cheese);

        assertEquals(expected, db.read(findRequest));
    }

    @Test
    public void updateByID() {
        db.create(milk);
        db.create(meat);
        db.create(cheese);

        Long id = meat.getProductID();
        String name = meat.getProductName();
        ProductCategory category = meat.getProductCategory();
        BigDecimal discount = meat.getProductDiscount();
        String description = meat.getProductDescription();

        updateRequest.setProductID(id);

        BigDecimal expected = new BigDecimal("60.0");

        updateRequest.setProductName(name);
        updateRequest.setProductPrice(expected);
        updateRequest.setProductCategory(String.valueOf(category));
        updateRequest.setProductDiscount(discount);
        updateRequest.setProductDescription(description);

        db.updateByID(updateRequest);

        assertEquals(expected, meat.getProductRegularPrice());
    }

    @Test
    public void deleteByID() {
        db.create(milk);
        db.create(meat);
        db.create(bread);
        db.create(cheese);

        Long id = meat.getProductID();

        findRequest.setProductID(id);

        assertTrue(db.delete(findRequest));
    }
}
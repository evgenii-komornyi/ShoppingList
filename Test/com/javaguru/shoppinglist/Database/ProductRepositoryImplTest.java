package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductRepositoryImplTest {
    private Product milk;
    private Product meat;
    private Product bread;
    private Product cheese;

    @Before
    public void setUp() {
        milk = new Product("Milk", new BigDecimal("25"), ProductCategory.MILK);
        milk.setProductDiscount(new BigDecimal("45.3"));
        milk.setProductDescription("Good cow milk from Latvia");
        meat = new Product("Beef", new BigDecimal("45.8"), ProductCategory.MEAT);
        bread = new Product("Bread", new BigDecimal("61.5"), ProductCategory.BREAD);
        cheese = new Product("Cheese", new BigDecimal("55"), ProductCategory.MILK);
    }

    @Test
    public void testForCreateProductInDatabase() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int expectedSizeList = 1;
        Map<Long, Product> list = db.getAllDatabase();

        assertEquals(expectedSizeList, list.size());
    }

    @Test
    public void getAllDatabase() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
            db.create(meat);
            db.create(cheese);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int expectedSize = 3;

        assertEquals(expectedSize, db.getAllDatabase().size());
    }

    @Test
    public void readByID() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
            db.create(meat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Long id = milk.getProductID();

        FindRequest request = new FindRequest();
        request.setProductID(id);

        List<Product> expected = Collections.singletonList(milk);

        assertEquals(expected, db.read(request));
    }

    @Test
    public void readByName() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
            db.create(meat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Product> expected = Collections.singletonList(milk);

        FindRequest request = new FindRequest();
        request.setProductName("Milk");

        List<Product> actual = db.read(request);

        if (expected.equals(actual))
        {
            System.out.println("Test for find by name has passed");
        } else
        {
            System.out.println("Test for find by name has failed");
            System.out.println(expected);
            System.out.println(actual);
        }
    }

    @Test
    public void readByCategory() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
            db.create(meat);
            db.create(cheese);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        FindRequest request = new FindRequest();
        request.setProductCategory(ProductCategory.MILK);

        List<Product> expected = new ArrayList<>();
        expected.add(milk);
        expected.add(cheese);

        assertEquals(expected, db.read(request));
    }

    @Test
    public void updateByID() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
            db.create(meat);
            db.create(cheese);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Long id = meat.getProductID();

        UpdateRequest request = new UpdateRequest();
        request.setProductID(id);

        BigDecimal expected = new BigDecimal("60.0");

        request.setNewProductPrice(expected);

        db.update(request);

        BigDecimal actual = meat.getProductPrice();

        assertEquals(expected, actual);
    }

    @Test
    public void deleteByID() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        try {
            db.create(milk);
            db.create(meat);
            db.create(bread);
            db.create(cheese);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Long id = meat.getProductID();

        FindRequest request = new FindRequest();
        request.setProductID(id);

        assertTrue(db.delete(request));
    }
}
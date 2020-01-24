package com.javaguru.shoppinglist.Database;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProductRepositoryImplTest {
    private Product milk;
    private Product meat;
    private Product bread;

    @Before
    public void setUp() {
        milk = new Product("Milk", new BigDecimal("2.8"), ProductCategory.MILK);
        milk.setProductDiscount(new BigDecimal("45.3"));
        milk.setProductDescription("Good cow milk from Latvia");
        meat = new Product("Meat", new BigDecimal("5.8"), ProductCategory.MEAT);
        bread = new Product("Bread", new BigDecimal("1.5"), ProductCategory.BREAD);
    }

    @Test
    public void testForCreateProductInDatabase() {
        ProductRepositoryImpl db = new ProductRepositoryImpl();
        db.create(milk);

        int expectedSizeList = 1;
        Map<Long, Product> list = db.getAllDatabase();

        System.out.println(db.getAllDatabase());
        assertEquals(expectedSizeList, list.size());
    }
}
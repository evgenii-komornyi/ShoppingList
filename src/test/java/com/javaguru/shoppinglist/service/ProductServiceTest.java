package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepositoryImpl;
import com.javaguru.shoppinglist.service.validationProduct.ProductCreateRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductFindRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductUpdateRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidation;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {
    private ProductInMemoryRepositoryImpl db = new ProductInMemoryRepositoryImpl();

    private ProductCreateRequestValidation productCreateRequestValidation = new ProductCreateRequestValidation();
    private ProductFindRequestValidation productFindRequestValidation = new ProductFindRequestValidation();
    private ProductUpdateRequestValidation productUpdateRequestValidation = new ProductUpdateRequestValidation();

    private ProductValidation productValidation = new ProductValidation(productCreateRequestValidation, productFindRequestValidation, productUpdateRequestValidation);

    private ProductService victim = new ProductService(db, productValidation);

    @Test
    public void shouldAddProductSuccessfuly() {
        Long id = victim.addProduct(createRequest()).getProduct().getProductID();

        String  expectedProductName = "Milk";
        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(id);

        assertEquals(expectedProductName, victim.findByID(productFindRequest).getFoundProduct().getProductName());
    }

    private ProductCreateRequest createRequest() {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setProductName("Milk");
        productCreateRequest.setProductPrice(new BigDecimal("25"));
        productCreateRequest.setProductCategory("MILK");

        return productCreateRequest;
    }

    @Test
    public void shouldFindByID() {
        Long expectedID = victim.addProduct(createRequestForFindByID()).getProduct().getProductID();

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(expectedID);

        assertEquals(expectedID, victim.findByID(productFindRequest).getFoundProduct().getProductID());
    }

    private ProductCreateRequest createRequestForFindByID() {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setProductName("Milk");
        productCreateRequest.setProductPrice(new BigDecimal("25"));
        productCreateRequest.setProductCategory("MILK");

        return productCreateRequest;
    }

    @Test
    public void shouldFindByCategory() {
        victim.addProduct(milkForFindByCategory());
        victim.addProduct(pienaForFindByCategory());

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductCategory(ProductCategory.MILK);

        int expectedSize = 4;
        assertEquals(expectedSize, victim.findByCategory(productFindRequest).getListOfFoundProducts().size());
    }

    private ProductCreateRequest milkForFindByCategory() {
        ProductCreateRequest milk = new ProductCreateRequest();
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("25"));
        milk.setProductCategory("MILK");

        return milk;
    }

    private ProductCreateRequest pienaForFindByCategory() {
        ProductCreateRequest piena = new ProductCreateRequest();
        piena.setProductName("Baltis milk");
        piena.setProductPrice(new BigDecimal("20"));
        piena.setProductCategory("MILK");

        return piena;
    }

    @Test
    public void shouldUpdateByID() {
        Long id = victim.addProduct(milkForUpdateByCategory()).getProduct().getProductID();

        ProductUpdateRequest updateRequest = new ProductUpdateRequest();
        updateRequest.setProductID(id);
        updateRequest.setProductName("Milk");
        updateRequest.setProductPrice(new BigDecimal("25"));
        updateRequest.setProductCategory("MILK");
        updateRequest.setProductDiscount(new BigDecimal("50"));

        BigDecimal expectedDiscount = new BigDecimal("50");

        assertEquals(expectedDiscount, victim.updateByID(updateRequest).getUpdatedProduct().getProductDiscount());
    }

    private ProductCreateRequest milkForUpdateByCategory() {
        ProductCreateRequest milk = new ProductCreateRequest();
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("25"));
        milk.setProductCategory("MILK");

        return milk;
    }

    @Test
    public void deleteByID() {
        Long id = victim.addProduct(createRequestForFindByID()).getProduct().getProductID();

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(id);

        assertTrue(victim.deleteByID(productFindRequest));
    }

    @Test
    public void getAllDatabase() {
        victim.addProduct(milkForUpdateByCategory());
        victim.addProduct(pienaForFindByCategory());

        int expectedSizeOfDB = 7;

        assertEquals(expectedSizeOfDB, victim.getAllDatabase().size());
    }

    @Test
    public void addProductWithDuplicateKey() {

    }
}
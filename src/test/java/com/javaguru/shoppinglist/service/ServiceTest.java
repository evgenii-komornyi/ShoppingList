package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.CreateRequest;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepositoryImpl;
import com.javaguru.shoppinglist.service.validation.CreateRequestValidation;
import com.javaguru.shoppinglist.service.validation.FindRequestValidation;
import com.javaguru.shoppinglist.service.validation.UpdateRequestValidation;
import com.javaguru.shoppinglist.service.validation.Validation;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceTest {
    private ProductInMemoryRepositoryImpl db = new ProductInMemoryRepositoryImpl();

    private CreateRequestValidation createRequestValidation = new CreateRequestValidation();
    private FindRequestValidation findRequestValidation = new FindRequestValidation();
    private UpdateRequestValidation updateRequestValidation = new UpdateRequestValidation();

    private Validation validation = new Validation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private Service victim = new Service(db, validation);

    @Test
    public void shouldAddProductSuccessfuly() {
        Long id = victim.addProduct(createRequest()).getProduct().getProductID();

        String  expectedProductName = "Milk";
        FindRequest findRequest = new FindRequest();
        findRequest.setProductID(id);

        assertEquals(expectedProductName, victim.findByID(findRequest).getFoundProduct().getProductName());

        victim.drop();
    }

    private CreateRequest createRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setProductName("Milk");
        createRequest.setProductPrice(new BigDecimal("25"));
        createRequest.setProductCategory("MILK");

        return createRequest;
    }

    @Test
    public void shouldFindByID() {
        Long expectedID = victim.addProduct(createRequestForFindByID()).getProduct().getProductID();

        FindRequest findRequest = new FindRequest();
        findRequest.setProductID(expectedID);

        assertEquals(expectedID, victim.findByID(findRequest).getFoundProduct().getProductID());

        victim.drop();
    }

    private CreateRequest createRequestForFindByID() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setProductName("Milk");
        createRequest.setProductPrice(new BigDecimal("25"));
        createRequest.setProductCategory("MILK");

        return createRequest;
    }

    @Test
    public void shouldFindByCategory() {
        victim.addProduct(milkForFindByCategory());
        victim.addProduct(pienaForFindByCategory());

        FindRequest findRequest = new FindRequest();
        findRequest.setProductCategory(ProductCategory.MILK);

        int expectedSize = 2;
        assertEquals(expectedSize, victim.findByCategory(findRequest).getListOfFoundProducts().size());

        victim.drop();
    }

    private CreateRequest milkForFindByCategory() {
        CreateRequest milk = new CreateRequest();
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("25"));
        milk.setProductCategory("MILK");

        return milk;
    }

    private CreateRequest pienaForFindByCategory() {
        CreateRequest piena = new CreateRequest();
        piena.setProductName("Baltis milk");
        piena.setProductPrice(new BigDecimal("20"));
        piena.setProductCategory("MILK");

        return piena;
    }

    @Test
    public void shouldUpdateByID() {
        Long id = victim.addProduct(milkForUpdateByCategory()).getProduct().getProductID();

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setProductID(id);
        updateRequest.setProductName("Milk");
        updateRequest.setProductPrice(new BigDecimal("25"));
        updateRequest.setProductCategory("MILK");
        updateRequest.setProductDiscount(new BigDecimal("50"));

        BigDecimal expectedDiscount = new BigDecimal("50");

        assertEquals(expectedDiscount, victim.updateByID(updateRequest).getUpdatedProduct().getProductDiscount());

        victim.drop();

    }

    private CreateRequest milkForUpdateByCategory() {
        CreateRequest milk = new CreateRequest();
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("25"));
        milk.setProductCategory("MILK");

        return milk;
    }

    @Test
    public void deleteByID() {
        Long id = victim.addProduct(createRequestForFindByID()).getProduct().getProductID();

        FindRequest findRequest = new FindRequest();
        findRequest.setProductID(id);

        assertTrue(victim.deleteByID(findRequest));

        victim.drop();
    }

    @Test
    public void getAllDatabase() {
        victim.addProduct(milkForUpdateByCategory());
        victim.addProduct(pienaForFindByCategory());

        int expectedSizeOfDB = 2;

        assertEquals(expectedSizeOfDB, victim.getAllDatabase().size());

        victim.drop();
    }
}
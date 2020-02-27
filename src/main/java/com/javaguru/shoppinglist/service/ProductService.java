package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.CreateRequest;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import com.javaguru.shoppinglist.domain.product.response.CreateResponse;
import com.javaguru.shoppinglist.domain.product.response.FindResponse;
import com.javaguru.shoppinglist.domain.product.response.UpdateResponse;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validation.Validation;
import com.javaguru.shoppinglist.service.validation.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private final Repository<Product> repository;
    private final Validation validation;

    @Autowired
    public ProductService(Repository<Product> repository, Validation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    public CreateResponse addProduct(CreateRequest createRequest) {
        CreateResponse response = new CreateResponse();
        List<ValidationErrors> validationErrors = validation.getCreateRequestValidation().validateCreateRequest(createRequest);
        List<DBErrors> dbErrors = new ArrayList<>();

        if (!validationErrors.isEmpty()) {
            response.setValidationErrors(validationErrors);
        } else {
            try {
                Product product = new Product(createRequest.getProductName(),
                        createRequest.getProductPrice(),
                        ProductCategory.valueOf(createRequest.getProductCategory()));

                if (createRequest.getProductDiscount() == null) {
                    product.setProductDiscount(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
                } else {
                    product.setProductDiscount(createRequest.getProductDiscount().setScale(2, RoundingMode.HALF_EVEN));
                }

                product.setProductDescription(createRequest.getProductDescription());

                response.setProduct(repository.create(product));
            } catch (CannotGetJdbcConnectionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            } catch (DuplicateKeyException e) {
                dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
            }
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public FindResponse findByID(FindRequest findRequest) {
        FindResponse response = new FindResponse();
        List<ValidationErrors> validationErrors = validation.getFindRequestValidation().validateFindRequest(findRequest);
        List<DBErrors> dbErrors = new ArrayList<>();
        try {
            if (!validationErrors.isEmpty()) {
                response.setValidationErrors(validationErrors);
            } else {
                response.setFoundProduct(repository.readByID(findRequest));
            }
        } catch(CannotGetJdbcConnectionException e) {
            dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public FindResponse findByCategory(FindRequest findRequest) {
        FindResponse response = new FindResponse();
        List<ValidationErrors> validationErrors = validation.getFindRequestValidation().validateFindRequest(findRequest);
        List<DBErrors> dbErrors = new ArrayList<>();
        try {
            if (!validationErrors.isEmpty()) {
                response.setValidationErrors(validationErrors);
            } else {

                response.setListOfFoundProducts(repository.read(findRequest));
            }
        } catch(CannotGetJdbcConnectionException | NullPointerException e){
            dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public UpdateResponse updateByID(UpdateRequest updateRequest) {
        UpdateResponse response = new UpdateResponse();
        List<ValidationErrors> validationErrors = validation.getUpdateRequestValidation().validateUpdateRequest(updateRequest);
        List<DBErrors> dbErrors = new ArrayList<>();

        if (!validationErrors.isEmpty()) {
            response.setValidationErrors(validationErrors);
        } else {
            try {
                response.setUpdatedProduct(repository.updateByID(updateRequest));
            } catch (CannotGetJdbcConnectionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            } catch (DuplicateKeyException e) {
                dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
            }
        }
        response.setDBErrors(dbErrors);
        return response;
    }

    public boolean deleteByID(FindRequest findRequest) {
        boolean hasDeleted = false;

        try {
            hasDeleted = repository.delete(findRequest);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database has failed, please try again later");
        }
        return hasDeleted;
    }

    public List<Product> getAllDatabase() {
        List<Product> allDB = null;
        try {
            allDB = repository.getAllDatabase();
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database has failed, please try again later");
        }

        return allDB;
    }

    public void drop() {
        repository.drop();
    }
}

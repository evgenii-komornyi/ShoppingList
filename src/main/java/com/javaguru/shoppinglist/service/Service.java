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
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Service {
    private final Repository<Product> DB;
    private final Validation validation;

    @Autowired
    public Service(Repository<Product> DB, Validation validation) {
        this.DB = DB;
        this.validation = validation;
    }

    public CreateResponse addProduct(CreateRequest createRequest) {
        CreateResponse response = new CreateResponse();
        List<ValidationErrors> validationErrors = validation.getCreateRequestValidation().validateCreateRequest(createRequest);
        List<DBErrors> dbErrors = new ArrayList<>();
        if (!validationErrors.isEmpty()) {
            response.setValidationErrors(validationErrors);
        } else {
            FindRequest findRequest = new FindRequest();
            findRequest.setProductName(createRequest.getProductName());

            try {
                if (!DB.read(findRequest).isEmpty()) {
//                    validationErrors.add(ValidationErrors.DUPLICATE_NAME);
//                    response.setValidationErrors(validationErrors);
                } else {
                    Product product = new Product(createRequest.getProductName(),
                            createRequest.getProductPrice(),
                            ProductCategory.valueOf(createRequest.getProductCategory()));

                    if (createRequest.getProductDiscount() == null) {
                        product.setProductDiscount(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
                    } else {
                        product.setProductDiscount(createRequest.getProductDiscount().setScale(2, RoundingMode.HALF_EVEN));
                    }

                    product.setProductDescription(createRequest.getProductDescription());
                    response.setProduct(DB.create(product));

                }
            } catch (CannotGetJdbcConnectionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
                response.setDBErrors(dbErrors);
            } catch (Exception e) {
                if(e instanceof SQLIntegrityConstraintViolationException) {
                    if(e.getMessage().contains("Duplicate")) {
                        dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
                        response.setDBErrors(dbErrors);
                    }
                }
            }
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
                response.setFoundProduct(DB.readByID(findRequest));
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

                response.setListOfFoundProducts(DB.read(findRequest));
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
        try {
            if (!validationErrors.isEmpty()) {
                response.setValidationErrors(validationErrors);
            } else {
                FindRequest findRequest = new FindRequest();
                findRequest.setProductName(updateRequest.getProductName());

                List<Product> list = DB.read(findRequest);

                if (!list.isEmpty() && !list.get(0).getProductID().equals(updateRequest.getProductID())) {
                    validationErrors.add(ValidationErrors.DUPLICATE_NAME);
                    response.setValidationErrors(validationErrors);
                } else {
                    response.setUpdatedProduct(DB.updateByID(updateRequest));
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public boolean deleteByID(FindRequest findRequest) {
        boolean hasDeleted = false;

        try {
            hasDeleted = DB.delete(findRequest);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database has failed, please try again later");
        }
        return hasDeleted;
    }

    public List<Product> getAllDatabase() {
        List<Product> allDB = null;
        try {
            allDB = DB.getAllDatabase();
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Database has failed, please try again later");
        }

        return allDB;
    }

    public void drop() {
        DB.drop();
    }
}

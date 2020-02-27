package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.CreateRequest;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import com.javaguru.shoppinglist.domain.product.response.CreateResponse;
import com.javaguru.shoppinglist.domain.product.response.FindResponse;
import com.javaguru.shoppinglist.domain.product.response.UpdateResponse;
import com.javaguru.shoppinglist.repository.ProductRepositoryImpl;
import com.javaguru.shoppinglist.service.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Component
public class Service {
    private final ProductRepositoryImpl DB;
    private final Validation validation;

    @Autowired
    public Service(ProductRepositoryImpl DB, Validation validation) {
        this.DB = DB;
        this.validation = validation;
    }

    public CreateResponse addProduct(CreateRequest createRequest) {
        CreateResponse response = new CreateResponse();
        List<ValidationErrors> validationErrors = validation.getCreateRequestValidation().validateCreateRequest(createRequest);
        if (!validationErrors.isEmpty()) {
            response.setValidationErrors(validationErrors);
        } else {
            FindRequest findRequest = new FindRequest();
            findRequest.setProductName(createRequest.getProductName());

            if (!DB.read(findRequest).isEmpty()) {
                validationErrors.add(ValidationErrors.DUPLICATE_NAME);
                response.setValidationErrors(validationErrors);
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
        }
        return response;
    }

    public FindResponse findByID(FindRequest findRequest) {
        FindResponse response = new FindResponse();
        List<ValidationErrors> validationErrors = validation.getFindRequestValidation().validateFindRequest(findRequest);
        if (!validationErrors.isEmpty()) {
            response.setValidationErrors(validationErrors);
        } else {
            response.setFoundProduct(DB.readByID(findRequest));
        }
        return response;
    }

    public FindResponse findByCategory(FindRequest findRequest) {
        FindResponse response = new FindResponse();
        List<ValidationErrors> validationErrors = validation.getFindRequestValidation().validateFindRequest(findRequest);
        if (!validationErrors.isEmpty()) {
            response.setValidationErrors(validationErrors);
        } else {
            response.setListOfFoundProducts(DB.read(findRequest));
        }
        return response;
    }

    public UpdateResponse updateByID(UpdateRequest updateRequest) {
        UpdateResponse response = new UpdateResponse();
        List<ValidationErrors> validationErrors = validation.getUpdateRequestValidation().validateUpdateRequest(updateRequest);
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
        return response;
    }

    public boolean deleteByID(FindRequest findRequest) {
        return DB.delete(findRequest);
    }

    public Map<Long, Product> getAllDatabase() {
        return DB.getAllDatabase();
    }

    public void drop() {
        DB.drop();
    }
}

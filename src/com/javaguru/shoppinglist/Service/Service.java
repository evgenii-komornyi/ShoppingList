package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Response.CreateResponse;
import com.javaguru.shoppinglist.Catalog.Product.Response.FindResponse;
import com.javaguru.shoppinglist.Catalog.Product.Response.UpdateResponse;
import com.javaguru.shoppinglist.Database.ProductRepositoryImpl;
import com.javaguru.shoppinglist.Service.Validation.Validation;
import com.javaguru.shoppinglist.Service.Validation.ValidationErrors;

import java.util.List;
import java.util.Map;

public class Service {
    private ProductRepositoryImpl DB = new ProductRepositoryImpl();
    private Validation validator = new Validation();

    public CreateResponse addProduct(CreateRequest createRequest) {
        CreateResponse response = new CreateResponse();
        List<ValidationErrors> validationErrors = validator.validateCreateRequest(createRequest);
        if (validationErrors.size() != 0) {
            response.setValidationErrors(validationErrors);
        } else {
            FindRequest findRequest = new FindRequest();
            findRequest.setProductName(createRequest.getProductName());

            if (DB.read(findRequest).size() != 0) {
                validationErrors.add(ValidationErrors.DUPLICATE_NAME);
                response.setValidationErrors(validationErrors);
            } else {
                Product product = new Product(createRequest.getProductName(),
                        createRequest.getProductPrice(),
                        ProductCategory.valueOf(createRequest.getProductCategory()));
                if (createRequest.getProductDiscount() != null) {
                    product.setProductDiscount(createRequest.getProductDiscount());
                }
                product.setProductDescription(createRequest.getProductDescription());

                response.setProduct(DB.create(product));
            }
        }
        return response;
    }

    public FindResponse findByID(FindRequest findRequest) {
        FindResponse response = new FindResponse();
        List<ValidationErrors> validationErrors = validator.validateFindRequest(findRequest);
        if (validationErrors.size() != 0) {
            response.setValidationErrors(validationErrors);
        } else {
            response.setFoundProduct(DB.readByID(findRequest));
        }
        return response;
    }

    public FindResponse findByCategory(FindRequest findRequest) {
        FindResponse response = new FindResponse();
        List<ValidationErrors> validationErrors = validator.validateFindRequest(findRequest);
        if (validationErrors.size() != 0) {
            response.setValidationErrors(validationErrors);
        } else {
            response.setListOfFoundProducts(DB.read(findRequest));
        }
        return response;
    }

    public UpdateResponse updateByID(UpdateRequest updateRequest) {
        UpdateResponse response = new UpdateResponse();
        List<ValidationErrors> validationErrors = validator.validateUpdateRequest(updateRequest);
        if (validationErrors.size() != 0) {
            response.setValidationErrors(validationErrors);
        } else {
            FindRequest findRequest = new FindRequest();
            findRequest.setProductName(updateRequest.getProductName());

            List<Product> list = DB.read(findRequest);

            if (list.size() != 0 && !list.get(0).getProductID().equals(updateRequest.getProductID())) {
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

package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Response.CreateResponse;
import com.javaguru.shoppinglist.Catalog.Product.Response.FindResponse;
import com.javaguru.shoppinglist.Database.ProductRepositoryImpl;

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
            Product product = new Product(createRequest.getProductName(),
                                          createRequest.getProductPrice(),
                                          ProductCategory.valueOf(createRequest.getProductCategory()));
            product.setProductDiscount(createRequest.getProductDiscount());
            product.setProductDescription(createRequest.getProductDescription());

            try {
                response.setProduct(DB.create(product));
            } catch (Exception e) {
                validationErrors.add(ValidationErrors.DUPLICATE_NAME);
                response.setValidationErrors(validationErrors);
            }
        }
        return response;
    }

    public List<Product> findByID(FindRequest requestProductID) {
        return DB.read(requestProductID);
    }

    public Map<Long, Product> getAllDatabase() {
        return DB.getAllDatabase();
    }
}

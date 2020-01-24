package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Response.CreateResponse;
import com.javaguru.shoppinglist.Database.ProductRepositoryImpl;

import java.util.List;
import java.util.Map;

public class Service {
    private ProductRepositoryImpl DB = new ProductRepositoryImpl();
    private Validation validator = new Validation();
    private CreateResponse response = new CreateResponse();

    public CreateResponse addProduct(CreateRequest createRequest) {
        List<ValidationErrors> validationErrors = validator.validateCreateRequest(createRequest);
        if (validationErrors.size() != 0) {
            response.setValidationErrors(validationErrors);
        } else {
            Product product = new Product(createRequest.getProductName(),
                                          createRequest.getProductPrice(),
                                          ProductCategory.valueOf(createRequest.getProductCategory()));
            product.setProductDiscount(createRequest.getProductDiscount());
            product.setProductDescription(createRequest.getProductDescription());

            response.setProduct(addProductToDB(product));
        }
        return response;
    }

    private Product addProductToDB(Product requestProduct) {
        return DB.create(requestProduct);
    }

    public Map<Long, Product> getAllDatabase() {
        return DB.getAllDatabase();
    }
}

package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import com.javaguru.shoppinglist.domain.product.response.ProductCreateResponse;
import com.javaguru.shoppinglist.domain.product.response.ProductFindResponse;
import com.javaguru.shoppinglist.domain.product.response.ProductUpdateResponse;
import com.javaguru.shoppinglist.repository.DBErrors;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidationErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final Repository repository;
    private final ProductValidation productValidation;

    @Autowired
    public ProductService(Repository repository, ProductValidation productValidation) {
        this.repository = repository;
        this.productValidation = productValidation;
    }

    public ProductCreateResponse addProduct(ProductCreateRequest productCreateRequest) {
        ProductCreateResponse response = new ProductCreateResponse();
        List<ProductValidationErrors> productValidationErrors = productValidation.getCreateRequestValidation().validateCreateRequest(productCreateRequest);
        List<DBErrors> dbErrors = new ArrayList<>();

        if (!productValidationErrors.isEmpty()) {
            response.setValidationErrors(productValidationErrors);
        } else {
            try {
                Product product = new Product(productCreateRequest.getProductName(),
                        productCreateRequest.getProductPrice(),
                        ProductCategory.valueOf(productCreateRequest.getProductCategory()));

                if (productCreateRequest.getProductDiscount() == null) {
                    product.setProductDiscount(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
                } else {
                    product.setProductDiscount(productCreateRequest.getProductDiscount().setScale(2, RoundingMode.HALF_EVEN));
                }

                product.setProductDescription(productCreateRequest.getProductDescription());
                response.setProduct(repository.create(product));
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            } catch (DuplicateKeyException | ConstraintViolationException e) {
                dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
            }
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public ProductFindResponse findByID(ProductFindRequest productFindRequest) {
        ProductFindResponse response = new ProductFindResponse();
        List<ProductValidationErrors> productValidationErrors = productValidation.getFindRequestValidation().validateFindRequest(productFindRequest);
        List<DBErrors> dbErrors = new ArrayList<>();
        try {
            if (!productValidationErrors.isEmpty()) {
                response.setValidationErrors(productValidationErrors);
            } else {
                response.setFoundProduct(repository.readByID(productFindRequest));
            }
        } catch(CannotCreateTransactionException e) {
            dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public ProductFindResponse findByCategory(ProductFindRequest productFindRequest) {
        ProductFindResponse response = new ProductFindResponse();
        List<ProductValidationErrors> productValidationErrors = productValidation.getFindRequestValidation().validateFindRequest(productFindRequest);
        List<DBErrors> dbErrors = new ArrayList<>();
        try {
            if (!productValidationErrors.isEmpty()) {
                response.setValidationErrors(productValidationErrors);
            } else {

                response.setListOfFoundProducts(repository.read(productFindRequest));
            }
        } catch(CannotCreateTransactionException e){
            dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            response.setDBErrors(dbErrors);
        }
        return response;
    }

    public ProductUpdateResponse updateByID(ProductUpdateRequest updateRequest) {
        ProductUpdateResponse response = new ProductUpdateResponse();
        List<ProductValidationErrors> productValidationErrors = productValidation.getUpdateRequestValidation().validateUpdateRequest(updateRequest);
        List<DBErrors> dbErrors = new ArrayList<>();

        if (!productValidationErrors.isEmpty()) {
            response.setValidationErrors(productValidationErrors);
        } else {
            try {
                response.setUpdatedProduct(repository.updateByID(updateRequest));
            } catch (CannotCreateTransactionException e) {
                dbErrors.add(DBErrors.DB_CONNECTION_FAILED);
            } catch (DuplicateKeyException e) {
                dbErrors.add(DBErrors.DB_DUPLICATE_ENTRY);
            }
        }
        response.setDBErrors(dbErrors);
        return response;
    }

    public boolean deleteByID(ProductFindRequest productFindRequest) {
        boolean hasDeleted = false;

        try {
            hasDeleted = repository.delete(productFindRequest);
        } catch (CannotCreateTransactionException e) {
            System.out.println("Database has failed, please try again later");
        }
        return hasDeleted;
    }

    public List<Product> getAllDatabase() {
        List<Product> allDB = null;
        try {
            allDB = repository.findAll();
        } catch (CannotCreateTransactionException e) {
            System.out.println("Database has failed, please try again later");
        }

        return allDB;
    }
}

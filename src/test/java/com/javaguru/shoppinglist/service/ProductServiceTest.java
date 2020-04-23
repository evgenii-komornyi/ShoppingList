package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validationProduct.ProductCreateRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductFindRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductUpdateRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private Repository repository;

    private ProductCreateRequestValidation createRequestValidation = new ProductCreateRequestValidation();

    private ProductFindRequestValidation findRequestValidation = new ProductFindRequestValidation();

    private ProductUpdateRequestValidation updateRequestValidation = new ProductUpdateRequestValidation();

    private ProductValidation validation = new ProductValidation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private ProductService victim;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        victim = new ProductService(repository, validation);
    }

    @Test
    public void shouldCreateProduct() {
        Product newProduct = product();
        System.out.println(newProduct);
        System.out.println(productCreateRequest());
        when(repository.create(newProduct)).thenReturn(product());
        Product result = victim.addProduct(productCreateRequest()).getProduct();

        assertThat(newProduct).isEqualTo(result);
    }

    private ProductCreateRequest productCreateRequest() {
        ProductCreateRequest milk = new ProductCreateRequest();
        milk.setProductID(10L);
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("20.00"));
        milk.setProductCategory("MILK");
        milk.setProductDiscount(new BigDecimal("0.00"));
        milk.setProductDescription("Fresh milk from Latvia");

        return milk;
    }

    private Product product() {
        Product milk = new Product("Milk", new BigDecimal("20.00"), ProductCategory.MILK);
        milk.setProductID(10L);
        milk.setProductDiscount(new BigDecimal("0.00"));
        milk.setProductDescription("Fresh milk from Latvia");

        return milk;
    }
}
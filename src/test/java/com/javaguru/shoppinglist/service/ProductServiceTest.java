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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private Repository<Product> repository;
    @Mock
    private ProductCreateRequestValidation createRequestValidation;
    @Mock
    private ProductFindRequestValidation findRequestValidation;
    @Mock
    private ProductUpdateRequestValidation updateRequestValidation;
    @InjectMocks
    private ProductValidation validation = new ProductValidation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private ProductService victim;

    @Before
    public void setUp() {
        victim = new ProductService(repository, validation);
    }

    @Test
    public void shouldCreateProduct() {
        ProductCreateRequest request = productCreateRequest();
        Product newProduct = product();

        when(repository.create(newProduct)).thenReturn(newProduct);
        Product result = victim.addProduct(request).getProduct();

        assertThat(newProduct).isEqualTo(result);
    }

    private ProductCreateRequest productCreateRequest() {
        ProductCreateRequest milk = new ProductCreateRequest();
        milk.setProductName("Milk");
        milk.setProductPrice(new BigDecimal("20.00"));
        milk.setProductCategory("MILK");
        milk.setProductDiscount(new BigDecimal("0.00"));
        milk.setProductDescription("Fresh milk from Latvia");

        return milk;
    }

    private Product product() {
        Product milk = new Product("Milk", new BigDecimal("20.00"), ProductCategory.MILK);
        milk.setProductDiscount(new BigDecimal("0.00"));
        milk.setProductDescription("Fresh milk from Latvia");

        return milk;
    }
}
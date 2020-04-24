package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import com.javaguru.shoppinglist.repository.Repository;
import com.javaguru.shoppinglist.service.validationProduct.ProductCreateRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductFindRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductUpdateRequestValidation;
import com.javaguru.shoppinglist.service.validationProduct.ProductValidation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private Repository productRepository;

    private ProductCreateRequestValidation createRequestValidation = new ProductCreateRequestValidation();

    private ProductFindRequestValidation findRequestValidation = new ProductFindRequestValidation();

    private ProductUpdateRequestValidation updateRequestValidation = new ProductUpdateRequestValidation();

    private ProductValidation validation = new ProductValidation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private ProductService victim;

    @Before
    public void setUp() {
        victim = new ProductService(productRepository, validation);
    }

    @Test
    public void shouldCreateProduct() {
        Product newProduct = productWithoutID();

        when(productRepository.create(newProduct)).thenReturn(productWithoutID());
        Product result = victim.addProduct(productCreateRequest()).getProduct();

        assertThat(newProduct).isEqualTo(result);
    }

    @Test
    public void shouldFindProductByID() {
        Product product = productWithID();

        when(productRepository.readByID(findRequest())).thenReturn(product);

        Product result = victim.findByID(findRequest()).getFoundProduct();

        assertThat(product).isEqualTo(result);
    }

    @Test
    public void shouldUpdateProductByID() {
        Product product = updatedProduct();

        when(productRepository.updateByID(updateRequest())).thenReturn(product);

        Product result = victim.updateByID(updateRequest()).getUpdatedProduct();

        assertThat(result).isEqualTo(product);
    }

    @Test
    public void shouldDeleteProductByID() {
        when(productRepository.delete(findRequest())).thenReturn(true);

        boolean result = victim.deleteByID(findRequest());

        assertThat(result).isTrue();
    }

    private ProductCreateRequest productCreateRequest() {
        ProductCreateRequest request = new ProductCreateRequest();
        request.setProductName("Milk");
        request.setProductPrice(new BigDecimal("20.00"));
        request.setProductCategory("MILK");
        request.setProductDiscount(new BigDecimal("0.00"));
        request.setProductDescription("Fresh milk from Latvia");

        return request;
    }

    private Product productWithoutID() {
        Product product = new Product("Milk", new BigDecimal("20.00"), ProductCategory.MILK);
        product.setProductDiscount(new BigDecimal("0.00"));
        product.setProductDescription("Fresh milk from Latvia");

        return product;
    }

    private ProductFindRequest findRequest() {
        ProductFindRequest request = new ProductFindRequest();
        request.setProductID(10L);

        return request;
    }

    private ProductUpdateRequest updateRequest() {
        ProductUpdateRequest request = new ProductUpdateRequest();
        request.setProductID(10L);
        request.setProductName("Milk Funny Milk");
        request.setProductCategory("MILK");
        request.setProductPrice(new BigDecimal("25.00"));
        request.setProductDiscount(new BigDecimal("20.00"));
        request.setProductDescription("Fresh milk from Latvia");

        return request;
    }

    private Product updatedProduct() {
        Product product = new Product();
        product.setProductID(10L);
        product.setProductName("Milk Funny Milk");
        product.setCategory(ProductCategory.MILK);
        product.setProductRegularPrice(new BigDecimal("25.00"));
        product.setProductDiscount(new BigDecimal("20.00"));
        product.setProductDescription("Fresh milk from Latvia");

        return product;
    }

    private Product productWithID() {
        Product milk = new Product("Milk", new BigDecimal("20.00"), ProductCategory.MILK);
        milk.setProductID(10L);
        milk.setProductDiscount(new BigDecimal("0.00"));
        milk.setProductDescription("Fresh milk from Latvia");

        return milk;
    }
}
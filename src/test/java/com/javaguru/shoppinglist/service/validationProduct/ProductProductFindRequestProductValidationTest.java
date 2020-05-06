package com.javaguru.shoppinglist.service.validationProduct;

import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductProductFindRequestProductValidationTest {
    private ProductCreateRequestValidation productCreateRequestValidation = new ProductCreateRequestValidation();
    private ProductFindRequestValidation productFindRequestValidation = new ProductFindRequestValidation();
    private ProductUpdateRequestValidation productUpdateRequestValidation = new ProductUpdateRequestValidation();

    private ProductValidation victim = new ProductValidation(productCreateRequestValidation, productFindRequestValidation, productUpdateRequestValidation);

    private ProductFindRequest productFindRequest = new ProductFindRequest();
    @Test
    public void validateFindRequestWithEmptyFields() {
        assertThat(victim.getFindRequestValidation().validateFindRequest(productFindRequest)).contains(ProductValidationErrors.NO_SEARCH_CRITERIA);
    }

    @Test
    public void validateFindRequestWithAllFieldsFilled() {
        productFindRequest.setProductID(Long.valueOf(0));
        productFindRequest.setProductName("Milk");
        productFindRequest.setProductCategory(ProductCategory.MILK);

        assertThat(victim.getFindRequestValidation().validateFindRequest(productFindRequest)).contains(ProductValidationErrors.CONFLICT_SEARCH_PARAMS);
    }
}
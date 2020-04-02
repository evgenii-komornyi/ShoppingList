package com.javaguru.shoppinglist.service.validationProduct;

import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductUpdateRequestProductValidationTest {
    private ProductCreateRequestValidation productCreateRequestValidation = new ProductCreateRequestValidation();
    private ProductFindRequestValidation productFindRequestValidation = new ProductFindRequestValidation();
    private ProductUpdateRequestValidation productUpdateRequestValidation = new ProductUpdateRequestValidation();

    private ProductValidation victim = new ProductValidation(productCreateRequestValidation, productFindRequestValidation, productUpdateRequestValidation);

    private ProductUpdateRequest updateRequest = new ProductUpdateRequest();

    @Test
    public void validateUpdateRequestWithEmptyFields() {
        assertThat(victim.getUpdateRequestValidation().validateUpdateRequest(updateRequest)).contains(ProductValidationErrors.NO_UPDATE_CRITERIA);
    }

}
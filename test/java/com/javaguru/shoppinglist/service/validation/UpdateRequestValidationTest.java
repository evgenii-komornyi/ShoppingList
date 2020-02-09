package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.domain.Product.Request.UpdateRequest;
import com.javaguru.shoppinglist.repository.ProductRepositoryImpl;
import com.javaguru.shoppinglist.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UpdateRequestValidationTest {
    private CreateRequestValidation createRequestValidation = new CreateRequestValidation();
    private FindRequestValidation findRequestValidation = new FindRequestValidation();
    private UpdateRequestValidation updateRequestValidation = new UpdateRequestValidation();

    @Mock
    private ProductRepositoryImpl db = new ProductRepositoryImpl();

    @InjectMocks
    private Validation victim = new Validation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private Service service = new Service(db, victim);

    private UpdateRequest updateRequest = new UpdateRequest();

    @Test
    public void validateUpdateRequestWithEmptyFields() {
        service.addProduct(product());

        updateRequest.setProductID(null);

        assertThat(victim.getUpdateRequestValidation().validateUpdateRequest(updateRequest)).contains(ValidationErrors.NO_UPDATE_CRITERIA);
    }

    @Test
    public void validateUpdateRequestWithoutDiscount() {
        service.addProduct(product());

        updateRequest.setProductID(0L);
        updateRequest.setProductDiscount(new BigDecimal("500"));

        assertThat(victim.getUpdateRequestValidation().validateUpdateRequest(updateRequest)).contains(ValidationErrors.INVALID_DISCOUNT_RANGE);

    }

    private CreateRequest product() {
        CreateRequest product = new CreateRequest();
        product.setProductName("Bread");
        product.setProductPrice(new BigDecimal("25"));
        product.setProductCategory("BREAD");

        return product;
    }
}
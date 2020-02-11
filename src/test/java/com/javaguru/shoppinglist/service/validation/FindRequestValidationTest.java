package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.product.ProductCategory;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FindRequestValidationTest {
    private CreateRequestValidation createRequestValidation = new CreateRequestValidation();
    private FindRequestValidation findRequestValidation = new FindRequestValidation();
    private UpdateRequestValidation updateRequestValidation = new UpdateRequestValidation();

    private Validation victim = new Validation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private FindRequest findRequest = new FindRequest();
    @Test
    public void validateFindRequestWithEmptyFields() {
        assertThat(victim.getFindRequestValidation().validateFindRequest(findRequest)).contains(ValidationErrors.NO_SEARCH_CRITERIA);
    }

    @Test
    public void validateFindRequestWithAllFieldsFilled() {
        findRequest.setProductID(Long.valueOf(0));
        findRequest.setProductName("Milk");
        findRequest.setProductCategory(ProductCategory.MILK);

        assertThat(victim.getFindRequestValidation().validateFindRequest(findRequest)).contains(ValidationErrors.CONFLICT_SEARCH_PARAMS);
    }
}
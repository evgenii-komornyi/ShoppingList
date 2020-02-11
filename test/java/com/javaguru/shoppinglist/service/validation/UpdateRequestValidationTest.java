package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UpdateRequestValidationTest {
    private CreateRequestValidation createRequestValidation = new CreateRequestValidation();
    private FindRequestValidation findRequestValidation = new FindRequestValidation();
    private UpdateRequestValidation updateRequestValidation = new UpdateRequestValidation();

    private Validation victim = new Validation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private UpdateRequest updateRequest = new UpdateRequest();

    @Test
    public void validateUpdateRequestWithEmptyFields() {
        assertThat(victim.getUpdateRequestValidation().validateUpdateRequest(updateRequest)).contains(ValidationErrors.NO_UPDATE_CRITERIA);
    }

}
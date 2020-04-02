package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.product.response.ProductUpdateResponse;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class UpdateControllerResponse {
    private ProductUpdateResponse updateResponse;
    private boolean validated;
    private ModelAndView modelAndView;
    private Map<String, String> errorMessages;

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ProductUpdateResponse getUpdateResponse() {
        return updateResponse;
    }

    public void setUpdateResponse(ProductUpdateResponse updateResponse) {
        this.updateResponse = updateResponse;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

    public void setModelAndView(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }
}

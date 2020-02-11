package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.consoleUI.UIController;
import com.javaguru.shoppinglist.repository.ProductRepositoryImpl;
import com.javaguru.shoppinglist.service.Service;
import com.javaguru.shoppinglist.service.validation.CreateRequestValidation;
import com.javaguru.shoppinglist.service.validation.FindRequestValidation;
import com.javaguru.shoppinglist.service.validation.UpdateRequestValidation;
import com.javaguru.shoppinglist.service.validation.Validation;

class ShoppingListApplication {
    public static void main(String[] args) {
        ProductRepositoryImpl DB = new ProductRepositoryImpl();
        CreateRequestValidation createRequestValidation = new CreateRequestValidation();
        FindRequestValidation findRequestValidation = new FindRequestValidation();
        UpdateRequestValidation updateRequestValidation = new UpdateRequestValidation();

        Validation validator = new Validation(createRequestValidation, findRequestValidation, updateRequestValidation);

        Service service = new Service(DB, validator);

        UIController UIController = new UIController(service);
        UIController.startUI();
    }
}
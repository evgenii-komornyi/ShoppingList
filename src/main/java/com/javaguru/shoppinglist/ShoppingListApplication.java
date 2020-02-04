package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.consoleUI.UIController;

class ShoppingListApplication {
    public static void main(String[] args) {
        UIController UIController = new UIController();
        UIController.startUI();
    }
}
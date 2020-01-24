package com.javaguru.shoppinglist.UI;

import com.javaguru.shoppinglist.Catalog.Product.Product;
import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Response.CreateResponse;
import com.javaguru.shoppinglist.Service.Service;
import com.javaguru.shoppinglist.Service.ValidationErrors;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ShoppingListApplication {
    public static void main(String[] args) {
        UIController UIController = new UIController();
        UIController.startUI();
    }
}

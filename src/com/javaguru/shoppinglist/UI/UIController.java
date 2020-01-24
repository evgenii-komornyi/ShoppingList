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
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UIController {
    private Service productService = new Service();

    public void startUI() {
        BufferedReader keyPress = reader();
        boolean exitMainMenu = false;

        while (exitMainMenu == false) {
            readMainMenuCommands();
            System.out.println("Type command: ");
            try {
                String commandForMainMenu = keyPress.readLine().toLowerCase();

                switch (commandForMainMenu) {
                    case "0":
                    case "exit":
                        exitMainMenu = true;
                        break;
                    case "1":
                    case "add":
                        addNewProduct();
                        break;
                    case "?":
                    case "help":
                        getHelp("main");
                        break;
                    default:
                        System.out.println("This command isn't support. Please read help documentation. (For help type \"?\", or \"help\")");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void readMainMenuCommands() {
        try {
            File commands = new File("src/com/javaguru/shoppinglist/UI/MainMenuCommands");
            Scanner reader = new Scanner(commands);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    private void getHelp(String menu) {
        switch (menu) {
            case "main":
                System.out.println("(0), or exit - to exit from the program;");
                System.out.println("(1), or add - to add new product;");
                System.out.println("(2), or get - to get information from the databese;");
                System.out.println("(3), or update - to update the database information;");
                System.out.println("(4), or delete - to delete a product by ID;");
                System.out.println("(5), or generate - to fill the database test of the products;");
                System.out.println("(6), or drop - to drop all from the database;");
                break;
            case "get":
                System.out.println("(0), or up - back to the main menu;");
                System.out.println("(1), or all - get all information products in the database;");
                System.out.println("(2), or byid - get information about product in the database by ID;");
                System.out.println("(3), or bycat - get information about product in the database by category;");
                break;
            case "update":
                System.out.println("(0), or up - back to main menu;");
                System.out.println("(1), or bycat - update information of products in the database by category;");
                System.out.println("(2), or byid - update information of product in the database by ID;");
                break;
        }
    }

    private BufferedReader reader() {
        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStream);
        return reader;
    }

    private String readCategories() {
        System.out.println("Available product categories: ");

        printProductsCategoriesList(ProductCategory.getCategories());

        BufferedReader reader = reader();

        System.out.println("Enter product category(or 0-8): ");
        String productCategory = null;
        try {
            productCategory = reader.readLine().toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (productCategory) {
            case "0":
                productCategory = "ALCOHOL";
                break;
            case "1":
                productCategory = "BREAD";
                break;
            case "2":
                productCategory = "FISH";
                break;
            case "3":
                productCategory = "FRUITS";
                break;
            case "4":
                productCategory = "MEAT";
                break;
            case "5":
                productCategory = "MILK";
                break;
            case "6":
                productCategory = "SOFT_DRINKS";
                break;
            case "7":
                productCategory = "SWEETS";
                break;
            case "8":
                productCategory = "VEGETABLES";
                break;
            case "":
                productCategory = null;
                break;
        }
        return productCategory;
    }

    private BigDecimal readBigDecimal() {
        BufferedReader reader = reader();
        String decimal = null;

        try {
            decimal = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (decimal.isEmpty()) ? new BigDecimal("0.00") : new BigDecimal(decimal).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal readDiscount() {
        System.out.println("Enter discount (from 0 to 100): ");
        return readBigDecimal();
    }

    private void addNewProduct() throws IOException {
        CreateRequest productCreateRequest = new CreateRequest();
        BufferedReader reader = reader();

        System.out.println("Product name: ");
        String productName;
        productName = reader.readLine();
        productCreateRequest.setProductName(productName);

        System.out.println("Product price: ");
        BigDecimal productPrice = readBigDecimal();
        productCreateRequest.setProductPrice(productPrice);

        String productCategory = readCategories();
        productCreateRequest.setProductCategory(productCategory);

        BigDecimal productDiscount = readDiscount();
        productCreateRequest.setProductDiscount(productDiscount);

        System.out.println("Product description: ");
        String productDescription;
        productDescription = reader.readLine();
        productCreateRequest.setProductDescription(productDescription);

        CreateResponse createResponse = productService.addProduct(productCreateRequest);
        if (createResponse.hasErrors()) {
            printErrorsList(createResponse.getValidationErrors());
        } else {
            System.out.println("Product has successfuly added.");
        }
    }

    private void printProduct(Product product) {
        String EURO = "\u20ac";
        System.out.println("ID: " + product.getProductID() +
                ", Product: " + product.getProductName() +
                ", Regular price: " + product.getProductPrice() + EURO + ", " +
                "Discount: " + product.getProductDiscount() + "%, " +
                "Actual price: " + product.calculateActualPrice() + EURO + ", " +
                "Category: " + product.getProductCategory() + ", " +
                "Description: " + product.getProductDescription() + ".");
    }

    private void printProductsList(Map<Long, Product> productsList) {
        for (Map.Entry<Long, Product> product : productsList.entrySet()) {
            printProduct(product.getValue());
        }
    }

    private void printProductsCategoriesList(List<ProductCategory> list) {
        for (ProductCategory productCategory : list) {
            System.out.println(list.indexOf(productCategory) + " - " + productCategory.name());
        }
    }

    private void printErrorsList(List<ValidationErrors> errorsList) {
        for (ValidationErrors validationErrors : errorsList) {
            System.out.println(validationErrors.getResponse());
        }
    }
}

package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ValidationTest {
    private CreateRequest milkWithoutName;
    private CreateRequest milkWithoutPrice;
    private CreateRequest milkWithoutCategory;

    private CreateRequest milkWithShorterThanThreeSymbolsName;
    private CreateRequest milkWithLongerThanThirtyTwoSymbolsName;

    private CreateRequest milkWithNegativePrice;
    private CreateRequest milkWithZeroPrice;

    private CreateRequest milkWithNegativeAndOutOfRangeDiscount;

    private CreateRequest milkWithShorterThanEightSymbolsDescription;
    private CreateRequest milkWithLongerThanSixtySymbolsDescription;

    private CreateRequest milkWithLowerThanTwentyPrice;

    private CreateRequest milk;

    @Before
    public void setUp() {
        String name = "Milk";
        BigDecimal price = new BigDecimal("35");
        String  category = "MILK";
        BigDecimal discount = new BigDecimal("20");
        String description = "BlaBlaBlaBlaBla";

        milkWithoutName = new CreateRequest();
        milkWithoutName.setProductName("");
        milkWithoutName.setProductPrice(price);
        milkWithoutName.setProductCategory(category);

        milkWithoutPrice = new CreateRequest();
        milkWithoutPrice.setProductName(name);
        milkWithoutPrice.setProductPrice(null);
        milkWithoutPrice.setProductCategory(category);

        milkWithoutCategory = new CreateRequest();
        milkWithoutCategory.setProductName(name);
        milkWithoutCategory.setProductPrice(price);
        milkWithoutCategory.setProductCategory(null);

        milkWithShorterThanThreeSymbolsName = new CreateRequest();
        milkWithShorterThanThreeSymbolsName.setProductName("m");
        milkWithShorterThanThreeSymbolsName.setProductPrice(price);
        milkWithShorterThanThreeSymbolsName.setProductCategory(category);

        milkWithLongerThanThirtyTwoSymbolsName = new CreateRequest();
        milkWithLongerThanThirtyTwoSymbolsName.setProductName("MilkPienaCowGoatMeatCocksMyLovelySadisticFate");
        milkWithLongerThanThirtyTwoSymbolsName.setProductPrice(price);
        milkWithLongerThanThirtyTwoSymbolsName.setProductCategory(category);

        milkWithNegativePrice = new CreateRequest();
        milkWithNegativePrice.setProductName(name);
        milkWithNegativePrice.setProductPrice(new BigDecimal("-2.8"));
        milkWithNegativePrice.setProductCategory(category);

        milkWithZeroPrice = new CreateRequest();
        milkWithZeroPrice.setProductName(name);
        milkWithZeroPrice.setProductPrice(new BigDecimal("0.0"));
        milkWithZeroPrice.setProductCategory(category);

        milkWithNegativeAndOutOfRangeDiscount = new CreateRequest();
        milkWithNegativeAndOutOfRangeDiscount.setProductName(name);
        milkWithNegativeAndOutOfRangeDiscount.setProductPrice(price);
        milkWithNegativeAndOutOfRangeDiscount.setProductCategory(category);
        milkWithNegativeAndOutOfRangeDiscount.setProductDiscount(new BigDecimal("-50"));

        milkWithShorterThanEightSymbolsDescription = new CreateRequest();
        milkWithShorterThanEightSymbolsDescription.setProductName(name);
        milkWithShorterThanEightSymbolsDescription.setProductPrice(price);
        milkWithShorterThanEightSymbolsDescription.setProductCategory(category);
        milkWithShorterThanEightSymbolsDescription.setProductDescription("Less 8");

        milkWithLongerThanSixtySymbolsDescription = new CreateRequest();
        milkWithLongerThanSixtySymbolsDescription.setProductName(name);
        milkWithLongerThanSixtySymbolsDescription.setProductPrice(price);
        milkWithLongerThanSixtySymbolsDescription.setProductCategory(category);
        milkWithLongerThanSixtySymbolsDescription.setProductDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        milkWithLowerThanTwentyPrice = new CreateRequest();
        milkWithLowerThanTwentyPrice.setProductName(name);
        milkWithLowerThanTwentyPrice.setProductPrice(new BigDecimal("18"));
        milkWithLowerThanTwentyPrice.setProductCategory("MILK");
        milkWithLowerThanTwentyPrice.setProductDiscount(discount);

        milk = new CreateRequest();
        milk.setProductName(name);
        milk.setProductPrice(price);
        milk.setProductCategory(category);
        milk.setProductDiscount(discount);
        milk.setProductDescription(description);
    }

    @Test
    public void validateCreateRequestWithoutName() {
        Validation validate = new Validation();

        String expectedError = "Name can't be empty";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithoutName);

        for (ValidationErrors validationErrors : list)
        {
            if (validationErrors.getResponse().equals(expectedError))
            {
                System.out.println("Test for validation of the create request with empty name has passed");
            } else
            {
                System.out.println("Test for validation of the create request with empty name has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithoutPrice() {
        Validation validate = new Validation();

        String expectedError = "Price can't be empty";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithoutPrice);

        for (ValidationErrors validationErrors : list)
        {
            if (validationErrors.getResponse().equals(expectedError))
            {
                System.out.println("Test for validation of the create request with empty price has passed");
            } else
            {
                System.out.println("Test for validation of the create request with empty price has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithoutCategory() {
        Validation validate = new Validation();

        String expectedError = "Category can't be empty";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithoutCategory);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with empty category has passed");
            } else {
                System.out.println("Test for validation of the create request with empty category has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithNameShorterThanThreeSymbols() {
        Validation validate = new Validation();

        String expectedError = "Name can be only from 3 to 32 symbols long";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithShorterThanThreeSymbolsName);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with name shorter than 3 symbols has passed");
            } else {
                System.out.println("Test for validation of the create request with name shorter than 3 symbols has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithNameLongerThanTwentySymbols() {
        Validation validate = new Validation();

        String expectedError = "Name can be only from 3 to 32 symbols long";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithLongerThanThirtyTwoSymbolsName);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with name longer than 32 symbols has passed");
            } else {
                System.out.println("Test for validation of the create request with name longer than 32 symbols has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithNegativePrice() {
        Validation validate = new Validation();

        String expectedError = "Price can't be negative, or 0";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithNegativePrice);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with negative price has passed");
            } else {
                System.out.println("Test for validation of the create request with negative price has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithZeroPrice() {
        Validation validate = new Validation();

        String expectedError = "Price can't be negative, or 0";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithZeroPrice);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with zero price has passed");
            } else {
                System.out.println("Test for validation of the create request with zero price has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithNegativeAndOutOfRangeDiscount() {
        Validation validate = new Validation();

        String expectedError = "Discount can be only from 0 to 100, or empty";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithNegativeAndOutOfRangeDiscount);

        for (ValidationErrors validationErrors : list) {
            if ( validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with negative or out of range discount has passed");
            } else {
                System.out.println("Test for validation of the create request with negative or out of range discount has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithShorterThanEightSymbolsDescription() {
        Validation validate = new Validation();

        String expectedError = "Description can be only from 8 to 60 symbols long";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithShorterThanEightSymbolsDescription);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with shorter than 8 symbols has passed");
            } else {
                System.out.println("Test for validation of the create request with shorter than 8 symbols has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithLongerThanSixtySymbolsDescription() {
        Validation validate = new Validation();

        String expectedError = "Description can be only from 8 to 60 symbols long";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithLongerThanSixtySymbolsDescription);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with longer than 60 symbols has passed");
            } else {
                System.out.println("Test for validation of the create request with longer than 60 symbols has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateCreateRequestWithLowerThanTwentyPrice() {
        Validation validate = new Validation();

        String expectedError = "Discount can't be applied to price lower than 20 euro";

        List<ValidationErrors> list = validate.validateCreateRequest(milkWithLowerThanTwentyPrice);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the create request with lower than 20 price has passed");
            } else {
                System.out.println("Test for validation of the create request with lower than 20 price has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateFindRequestWithEmptyFields() {
        Validation validate = new Validation();

        String expectedError = "ID, or name, or category field for search can't be empty";

        FindRequest findRequest = new FindRequest();

        List<ValidationErrors> list = validate.validateFindRequest(findRequest);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the find request with empty fields has passed");
            } else {
                System.out.println("Test for validation of the find request with empty fields has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateFindRequestWithAllFieldsFilled() {
        Validation validate = new Validation();

        String expectedError = "Search is available only by ID, or name, or category";

        FindRequest findRequest = new FindRequest();
        findRequest.setProductID(Long.valueOf(0));
        findRequest.setProductName("Milk");

        findRequest.setProductCategory(ProductCategory.MILK);

        List<ValidationErrors> list = validate.validateFindRequest(findRequest);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the find request with all filled fields has passed");
            } else {
                System.out.println("Test for validation of the find request with all filled fields has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateUpdateRequestWithEmptyFields() {
        Validation validate = new Validation();

        String expectedError = "ID, or category field for update can't be empty";

        UpdateRequest updateRequest = new UpdateRequest();

        List<ValidationErrors> list = validate.validateUpdateRequest(updateRequest);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the update request with empty fields has passed");
            } else {
                System.out.println("Test for validation of the update request with empty fields has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }

    @Test
    public void validateUpdateRequestWithAllFieldsFilled() {
        Validation validate = new Validation();

        String expectedError = "Update is available only by ID, or category";

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setProductID(Long.valueOf(0));
        updateRequest.setProductCategory(ProductCategory.MILK);

        List<ValidationErrors> list = validate.validateUpdateRequest(updateRequest);

        for (ValidationErrors validationErrors : list) {
            if (validationErrors.getResponse().equals(expectedError)) {
                System.out.println("Test for validation of the update request with all filled fields has passed");
            } else {
                System.out.println("Test for validation of the update request with all filled fields has failed");
                System.out.println("Expected result is: " + expectedError);
                System.out.println("Actual result is: " + validationErrors.getResponse());
            }
        }
    }
}
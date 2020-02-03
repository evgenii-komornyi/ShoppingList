package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Catalog.Product.ProductCategory;
import com.javaguru.shoppinglist.Catalog.Product.Request.CreateRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.FindRequest;
import com.javaguru.shoppinglist.Catalog.Product.Request.UpdateRequest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

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

    private Validation validate;

    private FindRequest findRequest;

    private UpdateRequest updateRequest;

    @Before
    public void setUp() {
        String name = "Milk";
        BigDecimal price = new BigDecimal("35");
        String  category = "MILK";
        BigDecimal discount = new BigDecimal("20");
        String description = "BlaBlaBlaBlaBla";

        validate = new Validation();

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

        findRequest = new FindRequest();
        updateRequest = new UpdateRequest();
    }

    @Test
    public void validateCreateRequestWithoutName() {
        assertThat(validate.validateCreateRequest(milkWithoutName)).contains(ValidationErrors.NAME_LENGTH_VIOLATION);
    }

    @Test
    public void validateCreateRequestWithoutPrice() {
        assertThat(validate.validateCreateRequest(milkWithoutPrice)).contains(ValidationErrors.EMPTY_PRICE);
    }

    @Test
    public void validateCreateRequestWithoutCategory() {
        assertThat(validate.validateCreateRequest(milkWithoutCategory)).contains(ValidationErrors.EMPTY_CATEGORY);
    }

    @Test
    public void validateCreateRequestWithNameShorterThanThreeSymbols() {
        assertThat(validate.validateCreateRequest(milkWithShorterThanThreeSymbolsName)).contains(ValidationErrors.NAME_LENGTH_VIOLATION);
    }

    @Test
    public void validateCreateRequestWithNameLongerThanTwentySymbols() {
        assertThat(validate.validateCreateRequest(milkWithLongerThanThirtyTwoSymbolsName)).contains(ValidationErrors.NAME_LENGTH_VIOLATION);
    }

    @Test
    public void validateCreateRequestWithNegativePrice() {
        assertThat(validate.validateCreateRequest(milkWithNegativePrice)).contains(ValidationErrors.NEGATIVE_OR_ZERO_PRICE);
    }

    @Test
    public void validateCreateRequestWithZeroPrice() {
        assertThat(validate.validateCreateRequest(milkWithZeroPrice)).contains(ValidationErrors.NEGATIVE_OR_ZERO_PRICE);
    }

    @Test
    public void validateCreateRequestWithNegativeAndOutOfRangeDiscount() {
        assertThat(validate.validateCreateRequest(milkWithNegativeAndOutOfRangeDiscount)).contains(ValidationErrors.INVALID_DISCOUNT_RANGE);
    }

    @Test
    public void validateCreateRequestWithShorterThanEightSymbolsDescription() {
        assertThat(validate.validateCreateRequest(milkWithShorterThanEightSymbolsDescription)).contains(ValidationErrors.DESCIPTION_LENGTH_VIOLATION);
    }

    @Test
    public void validateCreateRequestWithLongerThanSixtySymbolsDescription() {
        assertThat(validate.validateCreateRequest(milkWithLongerThanSixtySymbolsDescription)).contains(ValidationErrors.DESCIPTION_LENGTH_VIOLATION);
    }

    @Test
    public void validateCreateRequestWithLowerThanTwentyPrice() {
        assertThat(validate.validateCreateRequest(milkWithLowerThanTwentyPrice)).contains(ValidationErrors.DISCOUNT_APPLICATION_LIMIT_VIOLATION);
    }

    @Test
    public void validateFindRequestWithEmptyFields() {
        assertThat(validate.validateFindRequest(findRequest)).contains(ValidationErrors.NO_SEARCH_CRITERIA);
    }

    @Test
    public void validateFindRequestWithAllFieldsFilled() {
        findRequest.setProductID(Long.valueOf(0));
        findRequest.setProductName("Milk");
        findRequest.setProductCategory(ProductCategory.MILK);

        assertThat(validate.validateFindRequest(findRequest)).contains(ValidationErrors.CONFLICT_SEARCH_PARAMS);
    }

    @Test
    public void validateUpdateRequestWithEmptyFields() {
        assertThat(validate.validateUpdateRequest(updateRequest)).contains(ValidationErrors.NO_UPDATE_CRITERIA);
    }
}
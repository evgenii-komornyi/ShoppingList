package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product.Request.CreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CreateRequestValidationTest {
    private CreateRequestValidation createRequestValidation = new CreateRequestValidation();
    private FindRequestValidation findRequestValidation = new FindRequestValidation();
    private UpdateRequestValidation updateRequestValidation = new UpdateRequestValidation();

    @InjectMocks
    private Validation victim = new Validation(createRequestValidation, findRequestValidation, updateRequestValidation);

    private String name = "Milk";
    private BigDecimal price = new BigDecimal("35");
    private String  category = "MILK";
    private BigDecimal discount = new BigDecimal("20");
    private String description = "BlaBlaBlaBlaBla";

    private CreateRequest productWithoutName = productWithoutName();

    private CreateRequest productWithoutName() {
        productWithoutName = new CreateRequest();
        productWithoutName.setProductName("");
        productWithoutName.setProductPrice(price);
        productWithoutName.setProductCategory(category);

        return productWithoutName;
    }

    @Test
    public void validateCreateRequestWithoutName() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithoutName)).contains(ValidationErrors.NAME_LENGTH_VIOLATION);
    }

    private CreateRequest productWithoutPrice = productWithoutPrice();

    private CreateRequest productWithoutPrice() {
        productWithoutPrice = new CreateRequest();
        productWithoutPrice.setProductName(name);
        productWithoutPrice.setProductPrice(null);
        productWithoutPrice.setProductCategory(category);

        return productWithoutPrice;
    }

    @Test
    public void validateCreateRequestWithoutPrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithoutPrice)).contains(ValidationErrors.EMPTY_PRICE);
    }

    private CreateRequest productWithoutCategory = productWithoutCategory();

    private CreateRequest productWithoutCategory() {
        productWithoutCategory = new CreateRequest();
        productWithoutCategory.setProductName(name);
        productWithoutCategory.setProductPrice(price);
        productWithoutCategory.setProductCategory(null);

        return productWithoutCategory;
    }

    @Test
    public void validateCreateRequestWithoutCategory() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithoutCategory)).contains(ValidationErrors.EMPTY_CATEGORY);
    }

    private CreateRequest productWithShorterThanThreeSymbolsName = productWithShorterThanThreeSymbolsName();

    private CreateRequest productWithShorterThanThreeSymbolsName() {
        productWithShorterThanThreeSymbolsName = new CreateRequest();
        productWithShorterThanThreeSymbolsName.setProductName("m");
        productWithShorterThanThreeSymbolsName.setProductPrice(price);
        productWithShorterThanThreeSymbolsName.setProductCategory(category);

        return productWithShorterThanThreeSymbolsName;
    }

    @Test
    public void validateCreateRequestWithNameShorterThanThreeSymbols() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithShorterThanThreeSymbolsName)).contains(ValidationErrors.NAME_LENGTH_VIOLATION);
    }

    private CreateRequest productWithLongerThanThirtyTwoSymbolsName = productWithLongerThanThirtyTwoSymbolsName();

    private CreateRequest productWithLongerThanThirtyTwoSymbolsName() {
        productWithLongerThanThirtyTwoSymbolsName = new CreateRequest();
        productWithLongerThanThirtyTwoSymbolsName.setProductName("MilkPienaCowGoatMeatCocksMyLovelySadisticFate");
        productWithLongerThanThirtyTwoSymbolsName.setProductPrice(price);
        productWithLongerThanThirtyTwoSymbolsName.setProductCategory(category);

        return productWithLongerThanThirtyTwoSymbolsName;
    }

    @Test
    public void validateCreateRequestWithNameLongerThanTwentySymbols() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithLongerThanThirtyTwoSymbolsName)).contains(ValidationErrors.NAME_LENGTH_VIOLATION);
    }

    private CreateRequest productWithNegativePrice = productWithNegativePrice();

    private CreateRequest productWithNegativePrice() {
        productWithNegativePrice = new CreateRequest();
        productWithNegativePrice.setProductName(name);
        productWithNegativePrice.setProductPrice(new BigDecimal("-2.8"));
        productWithNegativePrice.setProductCategory(category);

        return productWithNegativePrice;
    }

    @Test
    public void validateCreateRequestWithNegativePrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithNegativePrice)).contains(ValidationErrors.NEGATIVE_OR_ZERO_PRICE);
    }

    private CreateRequest productWithZeroPrice = productWithZeroPrice();

    private CreateRequest productWithZeroPrice() {
        productWithZeroPrice = new CreateRequest();
        productWithZeroPrice.setProductName(name);
        productWithZeroPrice.setProductPrice(new BigDecimal("0.0"));
        productWithZeroPrice.setProductCategory(category);

        return productWithZeroPrice;
    }

    @Test
    public void validateCreateRequestWithZeroPrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithZeroPrice)).contains(ValidationErrors.NEGATIVE_OR_ZERO_PRICE);
    }

    private CreateRequest productWithNegativeAndOutOfRangeDiscount = productWithNegativeAndOutOfRangeDiscount();

    private CreateRequest productWithNegativeAndOutOfRangeDiscount() {
        productWithNegativeAndOutOfRangeDiscount = new CreateRequest();
        productWithNegativeAndOutOfRangeDiscount.setProductName(name);
        productWithNegativeAndOutOfRangeDiscount.setProductPrice(price);
        productWithNegativeAndOutOfRangeDiscount.setProductCategory(category);
        productWithNegativeAndOutOfRangeDiscount.setProductDiscount(new BigDecimal("-50"));

        return productWithNegativeAndOutOfRangeDiscount;
    }

    @Test
    public void validateCreateRequestWithNegativeAndOutOfRangeDiscount() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithNegativeAndOutOfRangeDiscount)).contains(ValidationErrors.INVALID_DISCOUNT_RANGE);
    }

    private CreateRequest productWithShorterThanEightSymbolsDescription = productWithShorterThanEightSymbolsDescription();

    private CreateRequest productWithShorterThanEightSymbolsDescription() {
        productWithShorterThanEightSymbolsDescription = new CreateRequest();
        productWithShorterThanEightSymbolsDescription.setProductName(name);
        productWithShorterThanEightSymbolsDescription.setProductPrice(price);
        productWithShorterThanEightSymbolsDescription.setProductCategory(category);
        productWithShorterThanEightSymbolsDescription.setProductDescription("Less 8");

        return productWithShorterThanEightSymbolsDescription;
    }

    @Test
    public void validateCreateRequestWithShorterThanEightSymbolsDescription() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithShorterThanEightSymbolsDescription)).contains(ValidationErrors.DESCIPTION_LENGTH_VIOLATION);
    }

    private CreateRequest productWithLongerThanSixtySymbolsDescription = productWithLongerThanSixtySymbolsDescription();

    private CreateRequest productWithLongerThanSixtySymbolsDescription() {
        productWithLongerThanSixtySymbolsDescription = new CreateRequest();
        productWithLongerThanSixtySymbolsDescription.setProductName(name);
        productWithLongerThanSixtySymbolsDescription.setProductPrice(price);
        productWithLongerThanSixtySymbolsDescription.setProductCategory(category);
        productWithLongerThanSixtySymbolsDescription.setProductDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        return productWithLongerThanSixtySymbolsDescription;
    }

    @Test
    public void validateCreateRequestWithLongerThanSixtySymbolsDescription() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithLongerThanSixtySymbolsDescription)).contains(ValidationErrors.DESCIPTION_LENGTH_VIOLATION);
    }

    private CreateRequest productWithLowerThanTwentyPrice = productWithLowerThanTwentyPrice();

    private CreateRequest productWithLowerThanTwentyPrice() {
        productWithLowerThanTwentyPrice = new CreateRequest();
        productWithLowerThanTwentyPrice.setProductName(name);
        productWithLowerThanTwentyPrice.setProductPrice(new BigDecimal("18"));
        productWithLowerThanTwentyPrice.setProductCategory("MILK");
        productWithLowerThanTwentyPrice.setProductDiscount(discount);

        return productWithLowerThanTwentyPrice;
    }

    @Test
    public void validateCreateRequestWithLowerThanTwentyPrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithLowerThanTwentyPrice)).contains(ValidationErrors.DISCOUNT_APPLICATION_LIMIT_VIOLATION);
    }
}
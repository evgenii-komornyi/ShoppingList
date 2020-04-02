package com.javaguru.shoppinglist.service.validationProduct;

import com.javaguru.shoppinglist.domain.product.request.ProductCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductCartProductCreateRequestProductValidationTest {
    private ProductCreateRequestValidation productCreateRequestValidation = new ProductCreateRequestValidation();
    private ProductFindRequestValidation productFindRequestValidation = new ProductFindRequestValidation();
    private ProductUpdateRequestValidation productUpdateRequestValidation = new ProductUpdateRequestValidation();

    private ProductValidation victim = new ProductValidation(productCreateRequestValidation, productFindRequestValidation, productUpdateRequestValidation);

    private String name = "Milk";
    private BigDecimal price = new BigDecimal("35");
    private String  category = "MILK";
    private BigDecimal discount = new BigDecimal("20");

    private ProductCreateRequest productWithoutName = productWithoutName();

    private ProductCreateRequest productWithoutName() {
        productWithoutName = new ProductCreateRequest();
        productWithoutName.setProductName("");
        productWithoutName.setProductPrice(price);
        productWithoutName.setProductCategory(category);

        return productWithoutName;
    }

    @Test
    public void validateCreateRequestWithoutName() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithoutName)).contains(ProductValidationErrors.NAME_LENGTH_VIOLATION);
    }

    private ProductCreateRequest productWithoutPrice = productWithoutPrice();

    private ProductCreateRequest productWithoutPrice() {
        productWithoutPrice = new ProductCreateRequest();
        productWithoutPrice.setProductName(name);
        productWithoutPrice.setProductPrice(null);
        productWithoutPrice.setProductCategory(category);

        return productWithoutPrice;
    }

    @Test
    public void validateCreateRequestWithoutPrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithoutPrice)).contains(ProductValidationErrors.EMPTY_PRICE);
    }

    private ProductCreateRequest productWithoutCategory = productWithoutCategory();

    private ProductCreateRequest productWithoutCategory() {
        productWithoutCategory = new ProductCreateRequest();
        productWithoutCategory.setProductName(name);
        productWithoutCategory.setProductPrice(price);
        productWithoutCategory.setProductCategory(null);

        return productWithoutCategory;
    }

    @Test
    public void validateCreateRequestWithoutCategory() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithoutCategory)).contains(ProductValidationErrors.EMPTY_CATEGORY);
    }

    private ProductCreateRequest productWithShorterThanThreeSymbolsName = productWithShorterThanThreeSymbolsName();

    private ProductCreateRequest productWithShorterThanThreeSymbolsName() {
        productWithShorterThanThreeSymbolsName = new ProductCreateRequest();
        productWithShorterThanThreeSymbolsName.setProductName("m");
        productWithShorterThanThreeSymbolsName.setProductPrice(price);
        productWithShorterThanThreeSymbolsName.setProductCategory(category);

        return productWithShorterThanThreeSymbolsName;
    }

    @Test
    public void validateCreateRequestWithNameShorterThanThreeSymbols() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithShorterThanThreeSymbolsName)).contains(ProductValidationErrors.NAME_LENGTH_VIOLATION);
    }

    private ProductCreateRequest productWithLongerThanThirtyTwoSymbolsName = productWithLongerThanThirtyTwoSymbolsName();

    private ProductCreateRequest productWithLongerThanThirtyTwoSymbolsName() {
        productWithLongerThanThirtyTwoSymbolsName = new ProductCreateRequest();
        productWithLongerThanThirtyTwoSymbolsName.setProductName("MilkPienaCowGoatMeatCocksMyLovelySadisticFate");
        productWithLongerThanThirtyTwoSymbolsName.setProductPrice(price);
        productWithLongerThanThirtyTwoSymbolsName.setProductCategory(category);

        return productWithLongerThanThirtyTwoSymbolsName;
    }

    @Test
    public void validateCreateRequestWithNameLongerThanTwentySymbols() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithLongerThanThirtyTwoSymbolsName)).contains(ProductValidationErrors.NAME_LENGTH_VIOLATION);
    }

    private ProductCreateRequest productWithNegativePrice = productWithNegativePrice();

    private ProductCreateRequest productWithNegativePrice() {
        productWithNegativePrice = new ProductCreateRequest();
        productWithNegativePrice.setProductName(name);
        productWithNegativePrice.setProductPrice(new BigDecimal("-2.8"));
        productWithNegativePrice.setProductCategory(category);

        return productWithNegativePrice;
    }

    @Test
    public void validateCreateRequestWithNegativePrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithNegativePrice)).contains(ProductValidationErrors.NEGATIVE_OR_ZERO_PRICE);
    }

    private ProductCreateRequest productWithZeroPrice = productWithZeroPrice();

    private ProductCreateRequest productWithZeroPrice() {
        productWithZeroPrice = new ProductCreateRequest();
        productWithZeroPrice.setProductName(name);
        productWithZeroPrice.setProductPrice(new BigDecimal("0.0"));
        productWithZeroPrice.setProductCategory(category);

        return productWithZeroPrice;
    }

    @Test
    public void validateCreateRequestWithZeroPrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithZeroPrice)).contains(ProductValidationErrors.NEGATIVE_OR_ZERO_PRICE);
    }

    private ProductCreateRequest productWithNegativeAndOutOfRangeDiscount = productWithNegativeAndOutOfRangeDiscount();

    private ProductCreateRequest productWithNegativeAndOutOfRangeDiscount() {
        productWithNegativeAndOutOfRangeDiscount = new ProductCreateRequest();
        productWithNegativeAndOutOfRangeDiscount.setProductName(name);
        productWithNegativeAndOutOfRangeDiscount.setProductPrice(price);
        productWithNegativeAndOutOfRangeDiscount.setProductCategory(category);
        productWithNegativeAndOutOfRangeDiscount.setProductDiscount(new BigDecimal("-50"));

        return productWithNegativeAndOutOfRangeDiscount;
    }

    @Test
    public void validateCreateRequestWithNegativeAndOutOfRangeDiscount() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithNegativeAndOutOfRangeDiscount)).contains(ProductValidationErrors.INVALID_DISCOUNT_RANGE);
    }

    private ProductCreateRequest productWithShorterThanEightSymbolsDescription = productWithShorterThanEightSymbolsDescription();

    private ProductCreateRequest productWithShorterThanEightSymbolsDescription() {
        productWithShorterThanEightSymbolsDescription = new ProductCreateRequest();
        productWithShorterThanEightSymbolsDescription.setProductName(name);
        productWithShorterThanEightSymbolsDescription.setProductPrice(price);
        productWithShorterThanEightSymbolsDescription.setProductCategory(category);
        productWithShorterThanEightSymbolsDescription.setProductDescription("Less 8");

        return productWithShorterThanEightSymbolsDescription;
    }

    @Test
    public void validateCreateRequestWithShorterThanEightSymbolsDescription() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithShorterThanEightSymbolsDescription)).contains(ProductValidationErrors.DESCIPTION_LENGTH_VIOLATION);
    }

    private ProductCreateRequest productWithLongerThanSixtySymbolsDescription = productWithLongerThanSixtySymbolsDescription();

    private ProductCreateRequest productWithLongerThanSixtySymbolsDescription() {
        productWithLongerThanSixtySymbolsDescription = new ProductCreateRequest();
        productWithLongerThanSixtySymbolsDescription.setProductName(name);
        productWithLongerThanSixtySymbolsDescription.setProductPrice(price);
        productWithLongerThanSixtySymbolsDescription.setProductCategory(category);
        productWithLongerThanSixtySymbolsDescription.setProductDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        return productWithLongerThanSixtySymbolsDescription;
    }

    @Test
    public void validateCreateRequestWithLongerThanSixtySymbolsDescription() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithLongerThanSixtySymbolsDescription)).contains(ProductValidationErrors.DESCIPTION_LENGTH_VIOLATION);
    }

    private ProductCreateRequest productWithLowerThanTwentyPrice = productWithLowerThanTwentyPrice();

    private ProductCreateRequest productWithLowerThanTwentyPrice() {
        productWithLowerThanTwentyPrice = new ProductCreateRequest();
        productWithLowerThanTwentyPrice.setProductName(name);
        productWithLowerThanTwentyPrice.setProductPrice(new BigDecimal("18"));
        productWithLowerThanTwentyPrice.setProductCategory("MILK");
        productWithLowerThanTwentyPrice.setProductDiscount(discount);

        return productWithLowerThanTwentyPrice;
    }

    @Test
    public void validateCreateRequestWithLowerThanTwentyPrice() {
        assertThat(victim.getCreateRequestValidation().validateCreateRequest(productWithLowerThanTwentyPrice)).contains(ProductValidationErrors.DISCOUNT_APPLICATION_LIMIT_VIOLATION);
    }
}
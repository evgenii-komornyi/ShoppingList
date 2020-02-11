package com.javaguru.shoppinglist.domain.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ProductCategory {
    ALCOHOL, BREAD, FISH, FRUITS, MEAT, MILK, SOFT_DRINKS, SWEETS, VEGETABLES;

    public static List<ProductCategory> getCategories() {
        return new ArrayList<>(Arrays.asList(ProductCategory.values()));
    }
}

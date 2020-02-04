package com.javaguru.shoppinglist.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ProductCategory {
    ALCOHOL, BREAD, FISH, FRUITS, MEAT, MILK, SOFT_DRINKS, SWEETS, VEGETABLES;

    public static List<ProductCategory> getCategories() {
        List<ProductCategory> categories = new ArrayList<>(Arrays.asList(ProductCategory.values()));

        return categories;
    }
}

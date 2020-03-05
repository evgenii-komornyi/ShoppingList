package com.javaguru.shoppinglist.domain.product.response;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.ProductCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductID(rs.getLong("productId"));
        product.setProductName(rs.getString("productName"));
        product.setProductRegularPrice(rs.getBigDecimal("productRegularPrice"));
        product.setProductCategory(ProductCategory.valueOf(rs.getString("productCategory")));
        product.setProductDiscount(rs.getBigDecimal("productDiscount"));
        product.setProductDescription(rs.getString("productDescription"));

        return product;
    }
}

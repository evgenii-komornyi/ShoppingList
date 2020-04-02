package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import com.javaguru.shoppinglist.domain.product.response.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
@Profile("jdbc")
public class ProductDALImpl implements ProductRepository<Product> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ProductDALImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product create(Product item) {
        String query = "insert into products(productName, productRegularPrice, productCategory, productDiscount, productDescription) values(?,?,?,?,?)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getProductName());
            ps.setString(2, String.valueOf(item.getProductRegularPrice()));
            ps.setString(3, String.valueOf(item.getCategory()));
            ps.setString(4, String.valueOf(item.getProductDiscount()));
            ps.setString(5, item.getProductDescription());

            return ps;
        }, generatedKeyHolder);

        item.setProductID(generatedKeyHolder.getKey().longValue());
        return item;
    }

    @Override
    public List<Product> read(ProductFindRequest productFindRequest) {
        String query = "select * from products where ";

        if (productFindRequest.getProductID() != null) {
            query = query + "productId=" + productFindRequest.getProductID();
        }

        if (productFindRequest.getProductName() != null && !productFindRequest.getProductName().isEmpty()) {
            query = query + "productName=\"" + productFindRequest.getProductName() + "\"";
        }

        if (productFindRequest.getProductCategory() != null) {
            query = query + "productCategory=\"" + productFindRequest.getProductCategory() + "\"";
        }

        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));

        return products;
    }

    @Override
    public Product readByID(ProductFindRequest productFindRequest) {
        List<Product> products = read(productFindRequest);
        if (!products.isEmpty()) {
            return products.get(0);
        }
        return null;
    }

    @Override
    public Product updateByID(ProductUpdateRequest updateRequest) throws CannotGetJdbcConnectionException {
        String query = "update products set productName=?, productRegularPrice=?, productCategory=?, productDiscount=?, productDescription=? where productId=" + updateRequest.getProductID();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, updateRequest.getProductName());
            ps.setString(2, String.valueOf(updateRequest.getProductPrice()));
            ps.setString(3, updateRequest.getProductCategory());
            ps.setString(4, String.valueOf(updateRequest.getProductDiscount()));
            ps.setString(5, updateRequest.getProductDescription());

            return ps;
        });

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(updateRequest.getProductID());

        return readByID(productFindRequest);
    }

    @Override
    public boolean delete(ProductFindRequest itemID) {
        String query = "delete from products where productId=?";
        int countOfDeletedItem =  jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, itemID.getProductID());

            return ps;
        });

        return countOfDeletedItem > 0;
    }

    @Override
    public List<Product> findAll() {
        String query = "select * from products";

        return jdbcTemplate.query(query, new ProductMapper());
    }
}

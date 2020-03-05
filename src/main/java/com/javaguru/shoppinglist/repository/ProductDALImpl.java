package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
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
public class ProductDALImpl implements Repository<Product> {
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
            ps.setString(3, String.valueOf(item.getProductCategory()));
            ps.setString(4, String.valueOf(item.getProductDiscount()));
            ps.setString(5, item.getProductDescription());

            return ps;
        }, generatedKeyHolder);

        item.setProductID(generatedKeyHolder.getKey().longValue());
        return item;
    }

    @Override
    public List<Product> read(FindRequest findRequest) {
        String query = "select * from products where ";

        if (findRequest.getProductID() != null) {
            query = query + "productId=" + findRequest.getProductID();
        }

        if (findRequest.getProductName() !=null && !findRequest.getProductName().isEmpty()) {
            query = query + "productName=\"" + findRequest.getProductName() + "\"";
        }

        if (findRequest.getProductCategory() != null) {
            query = query + "productCategory=\"" + findRequest.getProductCategory() + "\"";
        }

        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));

        return products;
    }

    @Override
    public Product readByID(FindRequest findRequest) {
        List<Product> products = read(findRequest);
        if (!products.isEmpty()) {
            return products.get(0);
        }
        return null;
    }

    @Override
    public Product updateByID(UpdateRequest updateRequest) throws CannotGetJdbcConnectionException {
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

        FindRequest findRequest = new FindRequest();
        findRequest.setProductID(updateRequest.getProductID());

        return readByID(findRequest);
    }

    @Override
    public boolean delete(FindRequest itemID) {
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

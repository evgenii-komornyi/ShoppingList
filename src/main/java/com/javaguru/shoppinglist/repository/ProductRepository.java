package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.ProductFindRequest;
import com.javaguru.shoppinglist.domain.product.request.ProductUpdateRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@Profile("hibernate")
@Transactional
class ProductRepository implements Repository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product create(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public List<Product> read(ProductFindRequest productFindRequest) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> products = new ArrayList<>();

        if (productFindRequest.getProductID() != null) {
            products = session.createCriteria(Product.class).add(Restrictions.eq("id", productFindRequest.getProductID())).list();
        }

        if (productFindRequest.getProductName() != null && !productFindRequest.getProductName().isEmpty()) {
            products = session.createCriteria(Product.class).add(Restrictions.eq("productName", productFindRequest.getProductName())).list();
        }

        if (productFindRequest.getProductCategory() != null) {
            products = session.createCriteria(Product.class).add(Restrictions.eq("category", productFindRequest.getProductCategory())).list();
        }

        return products;
    }

    @Override
    public Product readByID(ProductFindRequest productFindRequest) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id", productFindRequest.getProductID()))
                .uniqueResult();
        return product;
    }

    @Override
    public Product updateByID(ProductUpdateRequest updateRequest) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "update Product set name=:name, price=:price, " +
                        "category=:category, discount=:discount, " +
                        "description=:description " +
                        "where id=" + updateRequest.getProductID()
                );

        query.setString("name", updateRequest.getProductName());
        query.setString("price", String.valueOf(updateRequest.getProductPrice()));
        query.setString("category", updateRequest.getProductCategory());
        query.setString("discount", String.valueOf(updateRequest.getProductDiscount()));
        query.setString("description", updateRequest.getProductDescription());

        query.executeUpdate();

        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setProductID(updateRequest.getProductID());

        return readByID(productFindRequest);
    }

    @Override
    public boolean delete(ProductFindRequest itemID) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, itemID.getProductID());

        if (product != null) {
            session.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }
}

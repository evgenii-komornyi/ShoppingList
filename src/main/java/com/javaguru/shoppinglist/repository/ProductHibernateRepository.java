package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.product.Product;
import com.javaguru.shoppinglist.domain.product.request.FindRequest;
import com.javaguru.shoppinglist.domain.product.request.UpdateRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("hibernate")
@Transactional
class ProductHibernateRepository implements ProductRepository<Product> {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product create(Product item) {
        sessionFactory.getCurrentSession().save(item);
        return item;
    }

    @Override
    public List<Product> read(FindRequest findRequest) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> products = null;

        if (findRequest.getProductID() != null) {
            products = session.createCriteria(Product.class).add(Restrictions.eq("id", findRequest.getProductID())).list();
        }

        if (findRequest.getProductName() != null && !findRequest.getProductName().isEmpty()) {
            products = session.createCriteria(Product.class).add(Restrictions.eq("productName", findRequest.getProductName())).list();
        }

        if (findRequest.getProductCategory() != null) {
            products = session.createCriteria(Product.class).add(Restrictions.eq("productCategory", findRequest.getProductCategory())).list();
        }

        return products;
    }

    @Override
    public Product readByID(FindRequest findRequest) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id", findRequest.getProductID()))
                .uniqueResult();
        return product;
    }

    @Override
    public Product updateByID(UpdateRequest updateRequest) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "update Product set productName=:productName, productRegularPrice=:productRegularPrice, productCategory=:productCategory, productDiscount=:productDiscount, productDescription=:productDescription where productId=" + updateRequest.getProductID()
                );

        query.setString("productName", updateRequest.getProductName());
        query.setString("productRegularPrice", String.valueOf(updateRequest.getProductPrice()));
        query.setString("productCategory", updateRequest.getProductCategory());
        query.setString("productDiscount", String.valueOf(updateRequest.getProductDiscount()));
        query.setString("productDescription", updateRequest.getProductDescription());

        query.executeUpdate();

        FindRequest findRequest = new FindRequest();
        findRequest.setProductID(updateRequest.getProductID());

        return readByID(findRequest);
    }

    @Override
    public boolean delete(FindRequest itemID) {
        Session session;
        Product product;

        session = sessionFactory.getCurrentSession();
        product = (Product) session.load(Product.class, itemID.getProductID());

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

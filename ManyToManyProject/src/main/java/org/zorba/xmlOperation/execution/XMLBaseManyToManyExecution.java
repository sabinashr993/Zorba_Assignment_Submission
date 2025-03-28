package org.zorba.xmlOperation.execution;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.zorba.utility.HibernateUtility;
import org.zorba.xmlOperation.entity.Consumer;
import org.zorba.xmlOperation.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class XMLBaseManyToManyExecution {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

        Consumer consumer = new Consumer();
        consumer.setConsumerName("suman");
        consumer.setConsumerAddress("Bangalore");
        consumer.setConsumerMobile("12345678");

        Product product = new Product();
        product.setProductName("Laptop");
        product.setProductDescription("Dell Laptop");
        product.setProductQuantity(1);

        Product product1 = new Product();
        product1.setProductName("Mobile");
        product1.setProductDescription("Samsung Mobile");
        product1.setProductQuantity(1);

        Set<Product> products = new HashSet<>();
        products.add(product);
        products.add(product1);

        consumer.setProducts(products);

        try {
            tx = session.beginTransaction();
            session.persist(consumer);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

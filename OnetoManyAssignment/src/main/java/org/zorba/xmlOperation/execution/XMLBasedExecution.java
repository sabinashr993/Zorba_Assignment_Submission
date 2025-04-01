package org.zorba.xmlOperation.execution;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.zorba.utility.HibernateUtility;
import org.zorba.xmlOperation.entity.EmailAccount;
import org.zorba.xmlOperation.entity.User;

import java.util.HashSet;
import java.util.Set;

public class XMLBasedExecution {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        User user = new User("sabina", "usa", 1234567890L);
        Set<EmailAccount> emailAccountSet = new HashSet<>();

        EmailAccount email1 = new EmailAccount();
        email1.setAccType("gmail");
        email1.setEmailId("sabina@gmail.com");
        email1.setUser(user);

        EmailAccount email2 = new EmailAccount();
        email2.setAccType("yahoo");
        email2.setEmailId("sabina@yahoo.com");
        email2.setUser(user);

        emailAccountSet.add(email1);
        emailAccountSet.add(email2);

        user.setAccountSet(emailAccountSet);

        try {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }
}

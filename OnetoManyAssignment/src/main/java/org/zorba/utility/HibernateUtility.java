package org.zorba.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    public static SessionFactory getSessionFactory() {
        // Read the Configuration (hibernate.cfg.xml) File
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Create Session factory from the Configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}

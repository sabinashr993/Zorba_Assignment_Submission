package org.zorba.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zorba.hibernate_inheritance.entity.Bicycle;
import org.zorba.hibernate_inheritance.entity.Bus;
import org.zorba.hibernate_inheritance.entity.Car;
import org.zorba.hibernate_inheritance.entity.Vehicle;

import java.awt.*;

public class HibernateUtility {
    public static SessionFactory getSessionFactory() {
        // Read the Configuration (hibernate.cfg.xml) File
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(Vehicle.class);
        configuration.addAnnotatedClass(Bus.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Bicycle.class);

        // Create Session factory from the Configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}

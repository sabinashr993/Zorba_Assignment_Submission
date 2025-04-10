package org.zorba.hibernate_inheritance.execution;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.zorba.hibernate_inheritance.entity.Bicycle;
import org.zorba.hibernate_inheritance.entity.Bus;
import org.zorba.hibernate_inheritance.entity.Car;
import org.zorba.utility.HibernateUtility;

public class AnnotationBasedTablePerClassExecution {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        Bus bus = new Bus();
        bus.setVehicleName("Bus");
        bus.setNumberOfWheels(4);
        bus.setCapacity(20);
        bus.setComfortType("AC");


        Car car = new Car();
        car.setVehicleName("Car");
        car.setNumberOfWheels(4);
        car.setCapacity(5);
        car.setMake("VW");
        car.setModel("Golf");
        car.setYear(2019);
        car.setColor("Blue");

        Bicycle bicycle = new Bicycle();
        bicycle.setVehicleName("Bicycle");
        bicycle.setNumberOfWheels(2);
        bicycle.setCapacity(2);
        bicycle.setType("Road Bike");


        try {
            tx = session.beginTransaction();

            session.persist(bus);
            session.persist(car);
            session.persist(bicycle);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }

        try {
            Bus retrievedObj = session.get(Bus.class, 101);
            System.out.println("retrievedObj: " + retrievedObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.zorba.xmlOperation.execution;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.zorba.utility.HibernateUtility;
import org.zorba.xmlOperation.entity.DriverLicense;
import org.zorba.xmlOperation.entity.Person;

import java.time.LocalDate;

public class XMLBasedOneToOneOperation {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        Person person = new Person();
        person.setPersonName("John");
        LocalDate dateOfBirth = LocalDate.of(1992, 11, 10);
        person.setPersonDob(dateOfBirth);
        person.setSocialSecurity(123456789);

        DriverLicense driverLicense = new DriverLicense();
        driverLicense.setLicenseIssueCounty("USA");
        LocalDate dateOfIssue = dateOfBirth.plusYears(18);
        driverLicense.setLicenseIssueDate(dateOfIssue);
        driverLicense.setLicenseExpiryDate(dateOfIssue.plusYears(5).plusMonths(6).plusDays(25));

        person.setDriverLicense(driverLicense);

        try {
            tx = session.beginTransaction();
            session.persist(person);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

    }
}

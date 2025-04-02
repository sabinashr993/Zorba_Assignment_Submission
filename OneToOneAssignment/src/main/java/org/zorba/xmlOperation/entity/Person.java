package org.zorba.xmlOperation.entity;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    private int personId;
    private String personName;
    private LocalDate personDob;
    private int socialSecurity;
    private DriverLicense driverLicense;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public LocalDate getPersonDob() {
        return personDob;
    }

    public void setPersonDob(LocalDate personDob) {
        this.personDob = personDob;
    }

    public int getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(int socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personDob=" + personDob +
                ", socialSecurity=" + socialSecurity +
                ", driverLicense=" + driverLicense +
                '}';
    }
}

package org.zorba.xmlOperation.entity;

import java.time.LocalDate;
import java.util.Date;

public class DriverLicense {
    private int licenseId;
    private String licenseIssueCounty;
    private LocalDate licenseIssueDate;
    private LocalDate licenseExpiryDate;
    private Person person;

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseIssueCounty() {
        return licenseIssueCounty;
    }

    public void setLicenseIssueCounty(String licenseIssueCounty) {
        this.licenseIssueCounty = licenseIssueCounty;
    }

    public LocalDate getLicenseIssueDate() {
        return licenseIssueDate;
    }

    public void setLicenseIssueDate(LocalDate licenseIssueDate) {
        this.licenseIssueDate = licenseIssueDate;
    }

    public LocalDate getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(LocalDate licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "DriverLicense{" +
                "licenseId=" + licenseId +
                ", licenseIssueCounty='" + licenseIssueCounty + '\'' +
                ", licenseIssueDate=" + licenseIssueDate +
                ", licenseExpiryDate=" + licenseExpiryDate +
                ", person=" + person +
                '}';
    }
}

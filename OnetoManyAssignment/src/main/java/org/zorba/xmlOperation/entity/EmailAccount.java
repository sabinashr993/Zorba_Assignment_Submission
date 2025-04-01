package org.zorba.xmlOperation.entity;

public class EmailAccount {
    private int accId;
    private String accType;
    private String emailId;
    private User user;

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "EmailAccount{" +
                "accId=" + accId +
                ", ccType='" + accType + '\'' +
                ", emailId='" + emailId + '\'' +
                ", user=" + user +
                '}';
    }

    public EmailAccount(String accType, String emailId) {
        this.accType = accType;
        this.emailId = emailId;
    }

    public EmailAccount() {
    }
}

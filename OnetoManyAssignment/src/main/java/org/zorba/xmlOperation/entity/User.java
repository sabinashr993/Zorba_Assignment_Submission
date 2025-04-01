package org.zorba.xmlOperation.entity;

import java.util.Set;

public class User {
    private int userId;
    private String userName;
    private String userAddress;
    private Long userMobile;
    private Set<EmailAccount> accountSet;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Long getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(Long userMobile) {
        this.userMobile = userMobile;
    }

    public Set<EmailAccount> getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(Set<EmailAccount> accountSet) {
        this.accountSet = accountSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userMobile=" + userMobile +
                ", accountSet=" + accountSet +
                '}';
    }

    public User(String userName, String userAddress, Long userMobile) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userMobile = userMobile;
    }

    public User() {
    }
}

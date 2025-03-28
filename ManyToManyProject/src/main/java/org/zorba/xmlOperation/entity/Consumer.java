package org.zorba.xmlOperation.entity;

import java.util.Set;

public class Consumer {
    private int consumerId;
    private String consumerName;
    private String consumerAddress;
    private String consumerMobile;
    private Set<Product> products;

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress;
    }

    public String getConsumerMobile() {
        return consumerMobile;
    }

    public void setConsumerMobile(String consumerMobile) {
        this.consumerMobile = consumerMobile;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "consumerId=" + consumerId +
                ", consumerName='" + consumerName + '\'' +
                ", consumerAddress='" + consumerAddress + '\'' +
                ", consumerMobile='" + consumerMobile + '\'' +
                ", products=" + products +
                '}';
    }
}

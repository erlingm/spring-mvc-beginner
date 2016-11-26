package com.packt.webstore.domain;

import java.io.Serializable;

/**
 * Created by Erling Molde on 02.11.2016.
 */
public class Customer implements Serializable {
    private Long customerId;
    private String name;
    private Address billingAddress;
    private String phoneNumber;

    public Customer() {
        super();
        this.billingAddress = new Address();
    }

    public Customer(Long customerId, String name) {
        this();
        this.customerId = customerId;
        this.name = name;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return customerId != null ? customerId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Customer customer = (Customer) o;

        return customerId != null ? customerId.equals(customer.customerId) : customer.customerId == null;
    }
}
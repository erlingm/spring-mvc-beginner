package com.packt.webstore.service;

import com.packt.webstore.domain.Customer;

import java.util.List;

/**
 * Created by Erling Molde on 02.11.2016.
 */
public interface CustomerService {
    List<Customer> getAllCustomers();

    void addCustomer(Customer newCustomer);
}

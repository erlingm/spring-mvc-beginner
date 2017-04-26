package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Customer;

import java.util.List;

/**
 * Created by Erling Molde on 02.11.2016.
 */
public interface CustomerRepository {
    List<Customer> getAllCustomers();

    boolean isCustomerExist(String customerId);

    Customer getCustomer(String customerId);

    void saveCustomer(Customer customer);
}

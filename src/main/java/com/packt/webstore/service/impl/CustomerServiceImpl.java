package com.packt.webstore.service.impl;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.AddressRepository;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erling Molde on 02.11.2016.
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.getAllCustomers();
        allCustomers.forEach(c -> {
            Map<Long, Address> addressMap = new HashMap<>();
            Long addressId = c.getBillingAddress().getId();
            Address billingAddress = addressMap.computeIfAbsent(addressId, id -> addressRepository.getAddress(id));
            c.setBillingAddress(billingAddress);
        });
        return allCustomers;
    }

    @Override
    public boolean isCustomerExist(String customerId) {
        return customerRepository.isCustomerExist(customerId);
    }

    @Override
    public Customer getCustomer(String customerId) {
        Customer customer = customerRepository.getCustomer(customerId);
        Long billingAddressId = customer.getBillingAddress().getId();
        Address address = addressRepository.getAddress(billingAddressId);
        customer.setBillingAddress(address);
        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Address billingAddress = customer.getBillingAddress();
        addressRepository.isExistAddress(billingAddress.getId());
    }

    @Override
    public void addCustomer(Customer newCustomer) {

    }
}
package com.packt.webstore.service.impl;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.AddressRepository;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.domain.repository.OrderRepository;
import com.packt.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Erling Molde on 26.11.2016.
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Long saveOrder(Order order) {
        Address shippingAddress = order.getShippingDetail().getShippingAddress();
        addressRepository.saveAddress(shippingAddress);
        Customer customer = order.getCustomer();
        boolean customerExist = customerRepository.isCustomerExist(customer.getName());
        if (!customerExist) {
            customerRepository.saveCustomer(customer);
        }
        Address billingAddress = customer.getBillingAddress();
        addressRepository.saveAddress(billingAddress);
        return orderRepository.saveOrder(order);
    }
}
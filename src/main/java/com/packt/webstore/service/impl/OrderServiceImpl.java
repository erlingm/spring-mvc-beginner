package com.packt.webstore.service.impl;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.OrderRepository;
import com.packt.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Erling Molde on 26.11.2016.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Long saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    }
}
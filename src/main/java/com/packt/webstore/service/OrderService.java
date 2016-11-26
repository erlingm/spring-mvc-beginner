package com.packt.webstore.service;

import com.packt.webstore.domain.Order;

/**
 * Created by Erling Molde on 26.11.2016.
 */
public interface OrderService {
    Long saveOrder(Order order);
}
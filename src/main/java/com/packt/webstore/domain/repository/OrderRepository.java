package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Order;

/**
 * Created by Erling Molde on 26.11.2016.
 */
public interface OrderRepository {
    long saveOrder(Order order);
}
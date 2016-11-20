package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.dto.CartDto;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public interface CartRepository {
    void create(CartDto cartDto);

    Cart read(String id);

    void update(String id, CartDto cartDto);

    void delete(String id);

    void addItem(String cartId, String productId);

    void removeItem(String cartId, String productId);
}
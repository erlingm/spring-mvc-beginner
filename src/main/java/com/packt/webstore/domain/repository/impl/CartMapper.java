package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.service.ProductService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public class CartMapper implements RowMapper<Cart> {
    private CartItemMapper cartItemMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public CartMapper(NamedParameterJdbcTemplate jdbcTemplate, ProductService productService) {
        this.jdbcTemplate = jdbcTemplate;
        this.cartItemMapper = new CartItemMapper(productService);
    }

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("ID");
        Cart cart = new Cart(id);

        String SQL = "SELECT * FROM CART_ITEM WHERE CART_ID = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<CartItem> cartItems = jdbcTemplate.query(SQL, params, cartItemMapper);
        cart.setCartItems(cartItems);

        return cart;
    }
}
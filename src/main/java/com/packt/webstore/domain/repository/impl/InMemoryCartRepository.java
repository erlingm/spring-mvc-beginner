package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.CartRepository;
import com.packt.webstore.dto.CartDto;
import com.packt.webstore.dto.CartItemDto;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erling Molde on 19.11.2016.
 */
@Repository
public class InMemoryCartRepository implements CartRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ProductService productService;

    @Override
    public void create(CartDto cartDto) {
        String INSERT_CART_SQL = "INSERT INTO CART(ID) VALUES (:id)";

        Map<String, Object> cartParams = new HashMap<>();
        cartParams.put("id", cartDto.getId());

        jdbcTemplate.update(INSERT_CART_SQL, cartParams);

        cartDto.getCartItems().forEach(cartItemDto -> {

            Product productById = productService.getProductById(cartItemDto.getProductId());

            String INSERT_CART_ITEM_SQL = "INSERT INTO CART_ITEM(ID,PRODUCT_ID,CART_ID,QUANTITY)" +
                    " VALUES (:id,:product_id,:cart_id,:quantity)";

            Map<String, Object> cartItemsParams = new HashMap<>();
            cartItemsParams.put("id", cartItemDto.getId());
            cartItemsParams.put("product_id", productById.getProductId());
            cartItemsParams.put("cart_id", cartDto.getId());
            cartItemsParams.put("quantity", cartItemDto.getQuantity());

            jdbcTemplate.update(INSERT_CART_ITEM_SQL, cartItemsParams);
        });
    }

    @Override
    public Cart read(String id) {
        String SQL = "SELECT * FROM CART WHERE ID = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        CartMapper cartMapper = new CartMapper(jdbcTemplate, productService);
        try {
            return jdbcTemplate.queryForObject(SQL, params, cartMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(String id, CartDto cartDto) {
        List<CartItemDto> cartItems = cartDto.getCartItems();
        for (CartItemDto cartItem : cartItems) {
            String SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity, PRODUCT_ID = :productId WHERE ID = :id AND CART_ID = :cartId";
            Map<String, Object> params = new HashMap<>();
            params.put("id", cartItem.getId());
            params.put("quantity", cartItem.getQuantity());
            params.put("productId", cartItem.getProductId());
            params.put("cartId", id);

            jdbcTemplate.update(SQL, params);
        }
    }

    @Override
    public void delete(String id) {
        String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
        String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);
        jdbcTemplate.update(SQL_DELETE_CART, params);
    }

    @Override
    public void clearCart(String cartId) {
        String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", cartId);

        jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);
    }

    @Override
    public void addItem(String cartId, String productId) {
        String SQL;
        Cart cart;

        cart = read(cartId);
        if (cart == null) {
            CartItemDto newCartItemDto = new CartItemDto();
            newCartItemDto.setId(cartId + productId);
            newCartItemDto.setProductId(productId);
            newCartItemDto.setQuantity(1);

            CartDto newCartDto = new CartDto(cartId);
            newCartDto.addCartItem(newCartItemDto);
            create(newCartDto);
            return;
        }

        Map<String, Object> cartItemsParams = new HashMap<>();
        if (cart.getItemByProductId(productId) == null) {
            SQL = "INSERT INTO CART_ITEM (ID, PRODUCT_ID, CART_ID, QUANTITY) VALUES (:id, :productId, :cartId, :quantity)";
            cartItemsParams.put("id", cartId + productId);
            cartItemsParams.put("quantity", 1);
        } else {
            SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity WHERE CART_ID = :cartId AND PRODUCT_ID = :productId";
            CartItem existingItem = cart.getItemByProductId(productId);
            cartItemsParams.put("id", existingItem.getId());
            cartItemsParams.put("quantity", existingItem.getQuantity() + 1);
        }
        cartItemsParams.put("productId", productId);
        cartItemsParams.put("cartId", cartId);

        jdbcTemplate.update(SQL, cartItemsParams);
    }

    @Override
    public void removeItem(String cartId, String productId) {
        String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE PRODUCT_ID = :productId AND CART_ID = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", cartId);
        params.put("productId", productId);

        jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);
    }
}
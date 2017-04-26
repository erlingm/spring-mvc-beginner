package com.packt.webstore.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Erling Molde on 26.04.2017.
 */
public class CartItemTest {

    private CartItem cartItem;

    @Before
    public void setUp() throws Exception {
        cartItem = new CartItem("1");
    }

    @Test
    public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
        // Arrange
        Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
        cartItem.setProduct(iphone);

        // Act
        BigDecimal totalPrice = cartItem.getTotalPrice();

        // Assert
        Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
    }

}
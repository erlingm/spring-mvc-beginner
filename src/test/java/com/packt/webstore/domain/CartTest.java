package com.packt.webstore.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Erling Molde on 26.04.2017.
 */
public class CartTest {

    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart("10001");
    }

    @Test
    public void cart_grandTotal_should_be_sum_of_item_totals() {
        CartItem item1 = new CartItem("1");
        Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
        item1.setProduct(iphone);

        CartItem item2 = new CartItem("2");
        Product galxyNote = new Product("P1235", "Samsung Galaxy Note 10.1", new BigDecimal(1500));
        item2.setProduct(galxyNote);

        cart.setCartItems(Arrays.asList(item1, item2));

        BigDecimal grandTotal = cart.getGrandTotal();

        Assert.assertEquals(new BigDecimal(2000), grandTotal);
    }
}
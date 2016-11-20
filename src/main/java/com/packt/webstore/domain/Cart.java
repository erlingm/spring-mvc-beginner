package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public class Cart implements Serializable {
    private String id;
    private List<CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartItem getItemByProductId(String productId) {
        return cartItems.stream().filter(cartItem -> cartItem.getProduct().getProductId().equals(productId)).findAny().orElse(null);
    }

    public void updateGrandTotal() {
        Function<CartItem, BigDecimal> totalMapper = CartItem::getTotalPrice;
        BigDecimal grandTotal = cartItems.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.setGrandTotal(grandTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Cart cart = (Cart) o;

        return id != null ? id.equals(cart.id) : cart.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
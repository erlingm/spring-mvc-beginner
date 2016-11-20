package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public class CartItem implements Serializable {
    private String id;
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;

    public CartItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        this.updateTotalPrice();
        return totalPrice;
    }

    public void updateTotalPrice() {
        this.totalPrice = this.product.getUnitPrice().multiply(new BigDecimal(this.quantity));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        CartItem cartItem = (CartItem) obj;

        return id != null ? id.equals(cartItem.id) : cartItem.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
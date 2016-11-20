package com.packt.webstore.dto;

import java.io.Serializable;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public class CartItemDto implements Serializable {
    private String id;
    private String productId;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
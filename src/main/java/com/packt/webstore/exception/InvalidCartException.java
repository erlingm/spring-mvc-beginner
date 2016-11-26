package com.packt.webstore.exception;

/**
 * Created by Erling Molde on 26.11.2016.
 */
public class InvalidCartException extends RuntimeException {
    private String cartId;

    public InvalidCartException(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
}
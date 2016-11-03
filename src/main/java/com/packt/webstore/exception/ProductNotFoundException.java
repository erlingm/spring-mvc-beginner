package com.packt.webstore.exception;

/**
 * Created by Erling Molde on 03.11.2016.
 */
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -694354952032299587L;

    private String productId;

    public ProductNotFoundException(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}

package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Product;

import java.util.List;

/**
 * Created by Erling Molde on 02.11.2016.
 */
public interface ProductRepository {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    void updateStock(String productId, long noOfUnits);
}

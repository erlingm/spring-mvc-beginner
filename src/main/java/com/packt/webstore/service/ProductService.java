package com.packt.webstore.service;

import com.packt.webstore.domain.Product;

import java.util.List;

/**
 * Created by Erling Molde on 02.11.2016.
 */
public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    void updateAllStock();
}

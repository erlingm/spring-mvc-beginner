package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by Erling Molde on 02.11.2016.
 */
public interface ProductRepository {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    Product getProductById(String productId);

    void addProduct(Product product);

    void updateStock(String productId, long noOfUnits);
}

package com.packt.webstore.service.impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Erling Molde on 02.11.2016.
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    @Override
    public void updateAllStock() {
        List<Product> allProducts = productRepository.getAllProducts();
        allProducts.stream()
                .filter(product -> product.getUnitsInStock() < 500L)
                .forEach(product -> productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000));
    }
}
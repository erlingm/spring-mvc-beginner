package com.packt.webstore.validator;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Erling Molde on 04.11.2016.
 */
@Component
public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

    private ProductService productService;

    @Autowired
    public ProductIdValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void initialize(ProductId constraintAnnotation) {
        // intentionally left blank; this is the place to initialize the constraint annotation for any sensible default values.
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Product product;
        try {
            product = productService.getProductById(value);
        } catch (ProductNotFoundException e) {
            return true;
        }

        return product == null;
    }
}
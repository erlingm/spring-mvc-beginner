package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public class CategoryValidator implements ConstraintValidator<Category, String> {
    private List<String> allowedCategories;

    public CategoryValidator() {
        allowedCategories = Arrays.asList("Smartphone", "Tablet", "Laptop", "Head phones");
    }

    public void initialize(Category constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return allowedCategories.contains(value);
    }
}
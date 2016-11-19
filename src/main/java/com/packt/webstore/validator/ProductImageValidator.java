package com.packt.webstore.validator;

import com.packt.webstore.domain.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Erling Molde on 19.11.2016.
 */
public class ProductImageValidator implements Validator {

    private long allowedSize;

    public ProductImageValidator() {
        allowedSize = 100 * 1024;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        MultipartFile productImage = product.getProductImage();
        if (productImage != null && productImage.getSize() > allowedSize) {
            errors.rejectValue("productImage", "com.packt.webstore.validator.ProductImageValidator.message");
        }
    }
}

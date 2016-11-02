package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by Erling Molde on 02.11.2016.
 */
@Controller
@RequestMapping("/market")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/products/{productCategory}")
    public String getProductsByCategory(Model model, @PathVariable String productCategory) {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        return "products";
    }

    @RequestMapping("/products/filter/{params}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/products/{category}/{price}")
    public String filterProducts(@MatrixVariable(pathVar = "price") Map<String, String> priceRange, @PathVariable String category, @RequestParam String brand, Model model) {
        List<Product> productsByCategory = productService.getProductsByCategory(category);
        List<Product> products = productsByCategory.stream()
                .filter(p -> p.getUnitPrice().compareTo(new BigDecimal(priceRange.get("low"))) >= 0)
                .filter(p -> p.getUnitPrice().compareTo(new BigDecimal(priceRange.get("high"))) <= 0)
                .filter(p -> brand.equals(p.getManufacturer()))
                .collect(toList());
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/update/stock")
    public String updateStock(Model model) {
        productService.updateAllStock();
        return "redirect:/market/products";
    }
}
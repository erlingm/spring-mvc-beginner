package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        List<Product> products;
        List<Product> products1 = productService.getProductsByCategory(category);

        if (products1.isEmpty()) {
            products = products1;
        } else {
            products = products1.stream()
                    .filter(p -> !priceRange.containsKey("low") || p.getUnitPrice().compareTo(new BigDecimal(priceRange.get("low"))) >= 0)
                    .filter(p -> !priceRange.containsKey("high") || p.getUnitPrice().compareTo(new BigDecimal(priceRange.get("high"))) <= 0)
                    .filter(p -> brand == null || brand.trim().isEmpty() || brand.equals(p.getManufacturer()))
                    .collect(toList());
        }
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result) {
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0)
            throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        productService.addProduct(newProduct);
        return "redirect:/market/products";
    }

    @RequestMapping("/update/stock")
    public String updateStock(Model model) {
        productService.updateAllStock();
        return "redirect:/market/products";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder binder) {
        binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category", "unitsInStock", "condition");
    }
}
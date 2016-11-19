package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.UnitsInStockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private UnitsInStockValidator unitsInStockValidator;

    @RequestMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/products/{productCategory}")
    public String getProductsByCategory(Model model, @PathVariable String productCategory) {
        List<Product> products = productService.getProductsByCategory(productCategory);

        if (products == null || products.isEmpty())
            throw new NoProductsFoundUnderCategoryException();

        model.addAttribute("products", products);
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
    public String getProductById(@RequestParam("id") String productId, Model model, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        String pdfDirectory = servletContext.getRealPath("/resources/pdf/manual-");
        File manualFile = new File(pdfDirectory + product.getProductId() + ".pdf");
        model.addAttribute("hasManual", manualFile.exists());
        return "product";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
                                           BindingResult result, HttpServletRequest request) {
        if (result.hasErrors())
            return "addProduct";
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0)
            throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        String resourceDirectory = request.getSession().getServletContext().getRealPath("/resources/");
        if (resourceDirectory != null) {
            MultipartFile productImage = newProduct.getProductImage();
            if (productImage != null && !productImage.isEmpty())
                try {
                    File dest = new File(resourceDirectory + "images/" + newProduct.getProductId() + ".png");
                    productImage.transferTo(dest);
                } catch (IOException e) {
                    throw new RuntimeException("Product Image saving failed", e);
                }
            MultipartFile productManual = newProduct.getProductManual();
            if (productManual != null && !productManual.isEmpty())
                try {
                    File pdfDirectory = new File(resourceDirectory + "/pdf");
                    if (!pdfDirectory.exists())
                        pdfDirectory.mkdirs();
                    File dest = new File(pdfDirectory + "/manual-" + newProduct.getProductId() + ".pdf");
                    productManual.transferTo(dest);
                } catch (IOException e) {
                    throw new RuntimeException("Product Manual saving failed", e);
                }
        }
        productService.addProduct(newProduct);
        return "redirect:/market/products";
    }

    @RequestMapping("/update/stock")
    public String updateStock(Model model) {
        productService.updateAllStock();
        return "redirect:/market/products";
    }

    @RequestMapping("/products/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder binder) {
        binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer",
                "category", "unitsInStock", "condition",
                "productImage", "productManual",
                "language");
        binder.setValidator(unitsInStockValidator);

        /* Example of customizing a PropertyEditor to translate java.util.Date from a form to a bean */
        DateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL().append("?").append(req.getQueryString()).toString());
        mav.setViewName("productNotFound");
        return mav;
    }
}
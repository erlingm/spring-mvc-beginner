package com.packt.webstore.controller;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Erling Molde on 02.11.2016.
 */
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());

        return "customers";
    }

    @RequestMapping(value = "/customers/add",method = RequestMethod.GET)
    public String getAddNewCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "addCustomer";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String processAddNewCustomer(@ModelAttribute("newCustomer") Customer newCustomer) {
        customerService.addCustomer(newCustomer);
        return "redirect:/customers";
    }
}
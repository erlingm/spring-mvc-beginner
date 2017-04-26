package com.packt.webstore.controller;

import com.packt.webstore.config.WebApplicationContextConfig;
import com.packt.webstore.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by Erling Molde on 26.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetProducts() throws Exception {
        this.mockMvc.perform(get("/market/products"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    public void testGetProductById() throws Exception {
        // Arrange
        Product product = new Product("P1234", "iPhone 5s", new BigDecimal(500));

        // Act & Assert
        this.mockMvc.perform(get("/market/product").param("id", "P1234"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
    }

}
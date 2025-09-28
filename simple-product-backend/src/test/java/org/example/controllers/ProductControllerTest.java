package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.SimpleProductJavaApp;
import org.example.core.Product;
import org.example.dtos.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = SimpleProductJavaApp.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProducts() throws Exception {
        Product product1 = new Product("product 1");
        Product product2 = new Product("product 2");

        when(productService.findAll()).thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("product 1"))
                .andExpect(jsonPath("$[1].name").value("product 2"));
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product1 = new Product("product 1");

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(product1);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("product 1"));
    }
}


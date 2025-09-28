package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtos.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return ProductMapper.MAPPER.productToProductDTO(productService.findAll());
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        log.info("Received request to create product: {}", productDTO.getName());
        return ProductMapper.MAPPER.productToProductDTO(productService.createProduct(productDTO));
    }
}

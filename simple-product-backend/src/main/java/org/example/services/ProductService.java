package org.example.services;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.Product;
import org.example.dtos.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.repositories.ProductRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Returns all the existing products
     * @return
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Creates a new product
     * @param productDTO
     * @return
     */
    public Product createProduct(ProductDTO productDTO) {
        log.info("creating a new product {}", productDTO.getName());

        if (StringUtils.isBlank(productDTO.getName())) {
            throw new IllegalArgumentException("Cannot create a product with invalid name.");
        }

        return productRepository.save(ProductMapper.MAPPER.productDTOtoProduct(productDTO));
    }
}

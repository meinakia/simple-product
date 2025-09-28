package services;

import org.example.core.Product;
import org.example.dtos.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.repositories.ProductRepository;
import org.example.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO("sample product");
        Product expectedProduct = ProductMapper.MAPPER.productDTOtoProduct(productDTO);

        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        Product result = productService.createProduct(productDTO);
        assertNotNull(result);
        assertEquals(expectedProduct.getName(), result.getName());

        assertNotNull(result.getCreatedTimestamp());
        assertNotNull(result.getUpdatedTimestamp());
        assertNotNull(result.getUpdatedBy());
        assertNotNull(result.getCreatedBy());
    }

    @Test
    void testCreateProductShouldThrow() {
        ProductDTO productDTO = new ProductDTO("");
        assertThrows(Exception.class, () -> productService.createProduct(productDTO));

    }

    @Test
    void testGetProducts() {
        Product expectedProduct = new Product("sample product");
        expectedProduct.setId(1000L);
        expectedProduct.setCreatedBy(1L);
        expectedProduct.setUpdatedBy(1L);
        expectedProduct.setCreatedTimestamp(OffsetDateTime.parse("2025-09-26T14:30:00-05:00"));
        expectedProduct.setUpdatedTimestamp(OffsetDateTime.parse("2025-09-26T14:40:00-05:00"));

        when(productRepository.findAll()).thenReturn(Collections.singletonList(expectedProduct));

        List<Product> result = productService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());

        assertEquals(expectedProduct.getName(), result.get(0).getName());
        assertEquals(expectedProduct.getId(), result.get(0).getId());
        assertEquals(expectedProduct.getCreatedBy(), result.get(0).getCreatedBy());
        assertEquals(expectedProduct.getUpdatedBy(), result.get(0).getUpdatedBy());
        assertEquals(expectedProduct.getCreatedTimestamp(), result.get(0).getCreatedTimestamp());
        assertEquals(expectedProduct.getUpdatedTimestamp(), result.get(0).getUpdatedTimestamp());
    }

    @Test
    void testGetProductsWithEmptyList() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> result = productService.findAll();
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}

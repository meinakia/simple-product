package org.example.mappers;

import org.example.core.Product;
import org.example.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product productDTOtoProduct(ProductDTO productDTO);
    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productsToProductsDTO(List<Product> product);
}

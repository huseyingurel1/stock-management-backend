package com.huseyingurel.mapper;

import com.huseyingurel.dto.ProductDto;
import com.huseyingurel.entity.Product;

public class ProductMapper {

    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getProductCount(),
                product.getProductPrice()
        );
    }

    public static Product productDtoToProduct(ProductDto productDto ) {
        return new Product(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getProductCount(),
                productDto.getProductPrice()
        );
    }
}

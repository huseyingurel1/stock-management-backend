package com.huseyingurel.service;

import com.huseyingurel.dto.ProductDto;
import com.huseyingurel.entity.Product;
import com.huseyingurel.exception.ResourceNotFoundException;
import com.huseyingurel.mapper.ProductMapper;
import com.huseyingurel.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.productDtoToProduct(productDto);
        Product savedproduct= productRepository.save(product);
        return ProductMapper.productToProductDto(savedproduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product =productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product is not exist with given id " + productId));
        return ProductMapper.productToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(ProductMapper::productToProductDto)
                .collect(Collectors.toList());

    }

    @Override
    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        Product product =productRepository.findById(productId).orElseThrow(
                () ->   new ResourceNotFoundException("Product is not exist with given id " + productId)
        );
        product.setProductName(productDto.getProductName());
        product.setProductCount(productDto.getProductCount());
        product.setProductPrice(productDto.getProductPrice());

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.productToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(
                () ->   new ResourceNotFoundException("Product is not exist with given id " + productId)
        );
        productRepository.deleteById(productId);

    }
}

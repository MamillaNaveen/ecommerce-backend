package com.naveen.ecommerce.product.service.impl;

import com.naveen.ecommerce.category.entity.Category;
import com.naveen.ecommerce.category.repository.CategoryRepository;
import com.naveen.ecommerce.category.exception.CategoryAlreadyExistsException;
import com.naveen.ecommerce.common.exception.ResourceNotFoundException;
import com.naveen.ecommerce.product.dto.request.ProductRequest;
import com.naveen.ecommerce.product.dto.response.ProductResponse;
import com.naveen.ecommerce.product.entity.Product;
import com.naveen.ecommerce.product.exception.ProductAlreadyExistsException;
import com.naveen.ecommerce.product.mapper.ProductMapper;
import com.naveen.ecommerce.product.repository.ProductRepository;
import com.naveen.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new ProductAlreadyExistsException("Product SKU already exists");
        }

        if (productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException("Product SKU already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId())
        );

        Product product = productMapper.toEntity(request);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findByActiveTrue()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findByIdAndActiveTrue(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id)
        );

        if (!product.getSku().equals(request.getSku())
                && productRepository.existsBySku(request.getSku())) {

            throw new ProductAlreadyExistsException("Product SKU already exists");
        }

        if (!product.getName().equals(request.getName())
                && productRepository.existsByName(request.getName())) {

            throw new ProductAlreadyExistsException("Product name already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId())
        );

        productMapper.updateEntity(request, product);

        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        product.setActive(false);

        productRepository.save(product);
    }

}
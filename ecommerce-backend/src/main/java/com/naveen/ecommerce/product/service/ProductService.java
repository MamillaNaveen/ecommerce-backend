package com.naveen.ecommerce.product.service;

import com.naveen.ecommerce.product.dto.request.ProductRequest;
import com.naveen.ecommerce.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
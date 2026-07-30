package com.naveen.ecommerce.product.repository;

import com.naveen.ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);

    boolean existsByName(String name);

    List<Product> findByActiveTrue();

    Optional<Product> findByIdAndActiveTrue(Long id);
}
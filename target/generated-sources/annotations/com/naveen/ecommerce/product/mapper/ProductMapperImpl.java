package com.naveen.ecommerce.product.mapper;

import com.naveen.ecommerce.category.entity.Category;
import com.naveen.ecommerce.product.dto.request.ProductRequest;
import com.naveen.ecommerce.product.dto.response.ProductResponse;
import com.naveen.ecommerce.product.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T22:33:53+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.description( request.getDescription() );
        product.price( request.getPrice() );
        product.stockQuantity( request.getStockQuantity() );
        product.brand( request.getBrand() );
        product.sku( request.getSku() );

        return product.build();
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.categoryId( productCategoryId( product ) );
        productResponse.categoryName( productCategoryName( product ) );
        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.description( product.getDescription() );
        productResponse.price( product.getPrice() );
        productResponse.stockQuantity( product.getStockQuantity() );
        productResponse.brand( product.getBrand() );
        productResponse.sku( product.getSku() );
        productResponse.active( product.getActive() );

        return productResponse.build();
    }

    @Override
    public void updateEntity(ProductRequest request, Product product) {
        if ( request == null ) {
            return;
        }

        product.setName( request.getName() );
        product.setDescription( request.getDescription() );
        product.setPrice( request.getPrice() );
        product.setStockQuantity( request.getStockQuantity() );
        product.setBrand( request.getBrand() );
        product.setSku( request.getSku() );
    }

    private Long productCategoryId(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String productCategoryName(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}

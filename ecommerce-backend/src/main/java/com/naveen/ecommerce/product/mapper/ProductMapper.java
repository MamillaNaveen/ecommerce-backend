package com.naveen.ecommerce.product.mapper;

import com.naveen.ecommerce.product.dto.request.ProductRequest;
import com.naveen.ecommerce.product.dto.response.ProductResponse;
import com.naveen.ecommerce.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "active", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(ProductRequest request, @MappingTarget Product product);
}
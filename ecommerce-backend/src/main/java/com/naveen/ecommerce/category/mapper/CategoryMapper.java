package com.naveen.ecommerce.category.mapper;

import com.naveen.ecommerce.category.dto.request.CategoryRequest;
import com.naveen.ecommerce.category.dto.response.CategoryResponse;
import com.naveen.ecommerce.category.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);
}
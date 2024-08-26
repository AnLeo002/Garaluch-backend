package com.BackendGaraLunch.productsSystem.service;

import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTO;
import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTO;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTOResponse;

import java.util.List;

public interface CategoryService {
    CategoryDTOResponse findCategoryById(Long id);
    CategoryDTOResponse findCategoryByName(String name);
    List<CategoryDTOResponse> findAllCategories();
    CategoryDTOResponse createCategory(CategoryDTO categoryDTO);
    CategoryDTOResponse updateCategory(CategoryDTO categoryDTO,Long id);
    void deleteCategory(Long id);
}

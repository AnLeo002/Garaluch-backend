package com.BackendGaraLunch.productsSystem.service.impl;

import com.BackendGaraLunch.productsSystem.persisitence.CategoryEntity;
import com.BackendGaraLunch.productsSystem.repo.CategoryRepo;
import com.BackendGaraLunch.productsSystem.service.CategoryService;
import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTO;
import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo repo;
    @Override
    public CategoryDTOResponse findCategoryById(Long id) {
        return repo.findById(id).map(categoryEntity -> new CategoryDTOResponse(categoryEntity.getId(), categoryEntity.getName()))
                .orElseThrow(()->new RuntimeException("La categoria no se encuentra en la base de datos"));
    }

    @Override
    public CategoryDTOResponse findCategoryByName(String name) {
        return repo.findCategoryEntityByName(name).map(categoryEntity -> new CategoryDTOResponse(categoryEntity.getId(), categoryEntity.getName()))
                .orElseThrow(()->new RuntimeException("La categoría no se encuentra en la base de datos buscando por el nombre"));
    }

    @Override
    public List<CategoryDTOResponse> findAllCategories() {
        return repo.findAll().stream().map(categoryEntity -> new CategoryDTOResponse(categoryEntity.getId(), categoryEntity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTOResponse createCategory(CategoryDTO categoryDTO) {

        if(repo.findCategoryEntityByName(categoryDTO.name()).isPresent()){
            throw new RuntimeException();
        }

        CategoryEntity category = CategoryEntity.builder()
                .name(categoryDTO.name())
                .build();
        CategoryEntity categorySave = repo.save(category);
        return new CategoryDTOResponse(categorySave.getId(),categorySave.getName());
    }

    @Override
    public CategoryDTOResponse updateCategory(CategoryDTO categoryDTO, Long id) {

        CategoryEntity category = repo.findById(id).orElseThrow(()->new RuntimeException("La categoría no se encuentra en la base de datos"));

        category.setName(categoryDTO.name());

        CategoryEntity categorySave = repo.save(category);

        return new CategoryDTOResponse(categorySave.getId(),categorySave.getName());
    }

    @Override
    public void deleteCategory(Long id) {
        repo.deleteById(id);
    }
}

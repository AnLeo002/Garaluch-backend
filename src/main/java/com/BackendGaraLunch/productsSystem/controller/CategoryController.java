package com.BackendGaraLunch.productsSystem.controller;

import com.BackendGaraLunch.productsSystem.persisitence.CategoryEntity;
import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTO;
import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTO;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTOResponse;
import com.BackendGaraLunch.productsSystem.service.impl.CategoryServiceImpl;
import com.BackendGaraLunch.productsSystem.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl service;

    @GetMapping("/findAll")
    public ResponseEntity<List> findAllCategories(){
        return new ResponseEntity<>(service.findAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryDTOResponse> findCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(service.findCategoryByName(name),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTOResponse> findCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(service.findCategoryById(id),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<CategoryDTOResponse> saveCategory(@RequestBody CategoryDTO categoryDTO){
        try{
            return new ResponseEntity<>(service.createCategory(categoryDTO),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTOResponse> updateCategory(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(service.updateCategory(categoryDTO,id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

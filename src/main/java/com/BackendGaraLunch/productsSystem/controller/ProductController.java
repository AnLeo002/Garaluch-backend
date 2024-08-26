package com.BackendGaraLunch.productsSystem.controller;

import com.BackendGaraLunch.productsSystem.persisitence.ProductEntity;
import com.BackendGaraLunch.productsSystem.repo.ProductRepo;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTO;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTOResponse;
import com.BackendGaraLunch.productsSystem.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductServiceImpl service;

    @GetMapping("/findAll")
    public ResponseEntity findAllProducts(){
        return new ResponseEntity<>(service.findAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDTOResponse> findProductByName(@PathVariable String name){
        return new ResponseEntity<>(service.findProductByName(name),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> findProductById(@PathVariable Long id){
        return new ResponseEntity<>(service.findProductById(id),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ProductDTOResponse> saveProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(service.createProduct(productDTO),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(service.updateProduct(productDTO,id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

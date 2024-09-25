package com.BackendGaraLunch.productsSystem.service.impl;

import com.BackendGaraLunch.productsSystem.persisitence.CategoryEntity;
import com.BackendGaraLunch.productsSystem.persisitence.ProductEntity;
import com.BackendGaraLunch.productsSystem.repo.CategoryRepo;
import com.BackendGaraLunch.productsSystem.repo.ProductRepo;
import com.BackendGaraLunch.productsSystem.service.ProductService;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTO;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.ProductInvoiceDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo repo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public ProductDTOResponse findProductById(Long id) {
        return repo.findById(id).map(productEntity -> new ProductDTOResponse(productEntity.getId(), productEntity.getName(), productEntity.getAmount(), productEntity.getPrice(), productEntity.getDescription(),productEntity.getDateBuying(),productEntity.getCategory().getName(),productEntity.getUrl(),productEntity.getWeight()))
                .orElseThrow(()->new RuntimeException("El producto no se encuentra registrado en la base de datos"));
    }

    @Override
    public ProductDTOResponse findProductByName(String name) {
        return repo.findProductEntityByName(name).map(productEntity -> new ProductDTOResponse(productEntity.getId(), productEntity.getName(), productEntity.getAmount(), productEntity.getPrice(), productEntity.getDescription(),productEntity.getDateBuying(),productEntity.getCategory().getName(),productEntity.getUrl(),productEntity.getWeight()))
                .orElseThrow(()->new RuntimeException("El producto no se encuentra registrado en la base de datos"));
    }

    @Override
    public List<ProductDTOResponse> findAllProducts() {

        return repo.findAll().stream()
                .map(productEntity -> new ProductDTOResponse(productEntity.getId(), productEntity.getName(), productEntity.getAmount(), productEntity.getPrice(), productEntity.getDescription(),productEntity.getDateBuying(),productEntity.getCategory().getName(),productEntity.getUrl(),productEntity.getWeight()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTOResponse createProduct(ProductDTO productDTO) {

        CategoryEntity categoryEntity = categoryRepo.findCategoryEntityByName(productDTO.category()).orElseThrow();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//Esta línea de código me permitirá pasar un string a un LocalDate

        ProductEntity product = ProductEntity.builder()
                .name(productDTO.name())
                .amount(productDTO.amount())
                .dateBuying(LocalDate.parse(productDTO.dayBuying(),dateTimeFormatter))
                .price(productDTO.price())
                .weight(productDTO.weight())
                .description(productDTO.description())
                .category(categoryEntity)
                .url(productDTO.url())
                .build();
        ProductEntity productSave = repo.save(product);

            return new ProductDTOResponse(productSave.getId(), productSave.getName(), productSave.getAmount(), productSave.getPrice(), productSave.getDescription(),productSave.getDateBuying(),productSave.getCategory().getName(),productSave.getUrl(),productSave.getWeight());
    }

    @Override
    public ProductDTOResponse updateProduct(ProductDTO productDTO,Long id) {

        CategoryEntity categoryEntity = categoryRepo.findCategoryEntityByName(productDTO.category()).orElseThrow();

        ProductEntity product = repo.findById(id).orElseThrow(()->new RuntimeException("El producto no se encuentra en la base de datos"));
        product.setAmount(productDTO.amount());
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setWeight(productDTO.weight());
        product.setDescription(productDTO.description());
        product.setUrl(productDTO.url());
        product.setCategory(categoryEntity);
        ProductEntity productSave = repo.save(product);

        return new ProductDTOResponse(productSave.getId(), productSave.getName(), productSave.getAmount(), productSave.getPrice(), productSave.getDescription(),productSave.getDateBuying(),productSave.getCategory().getName(),productSave.getUrl(),productSave.getWeight());
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void updateAmountProduct(List<ProductInvoiceDTOResponse> productInvoiceDTOResponses) {

        productInvoiceDTOResponses.stream()
                .forEach(productUpdate -> {
                    ProductEntity product = repo.findById(productUpdate.id()).get();
                    if(product.getAmount() < productUpdate.amount()){
                        throw new RuntimeException("La cantidad ingresada no coincide con la cantidad en stock");
                    }else{
                        product.setAmount(product.getAmount()-productUpdate.amount());
                        repo.save(product);
                    }
                });

    }
}

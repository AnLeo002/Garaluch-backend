package com.BackendGaraLunch.productsSystem.service.impl;

import com.BackendGaraLunch.productsSystem.persisitence.ProductEntity;
import com.BackendGaraLunch.productsSystem.persisitence.PromEntity;
import com.BackendGaraLunch.productsSystem.repo.ProductRepo;
import com.BackendGaraLunch.productsSystem.repo.PromRepo;
import com.BackendGaraLunch.productsSystem.service.PromService;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.PromDTO;
import com.BackendGaraLunch.productsSystem.service.dto.PromDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.PromInvoiceDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromServiceImpl implements PromService {
    @Autowired
    private PromRepo promRepo;
    @Autowired
    private ProductRepo productRepo;
    @Override
    public PromDTOResponse saveProm(PromDTO promDTO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //try{
            PromEntity promEntity = PromEntity.builder()
                    .name(promDTO.name())
                    .description(promDTO.description())
                    .startDate(LocalDate.parse(promDTO.startDate(),dateTimeFormatter))
                    .endDate(LocalDate.parse(promDTO.endDate(),dateTimeFormatter))
                    .price(promDTO.price())
                    .amount(promDTO.amount())
                    .url(promDTO.url())
                    //.productEntities((Set<ProductEntity>) productRepo.findProductEntitiesByNameIn(promDTO.productEntities()))
                    .build();
            PromEntity promSave = promRepo.save(promEntity);

            /*Set<ProductDTOResponse> productDTOResponseSet = promSave.getProductEntities().stream()
                    .map(product -> new ProductDTOResponse(product.getId(), product.getName(), product.getAmount(), product.getPrice(), product.getDescription(),product.getDateBuying(),product.getCategory().getName(),product.getUrl()))
                    .collect(Collectors.toSet());*/

            return new PromDTOResponse(promSave.getId(),promSave.getName(),promSave.getDescription(),promSave.getStartDate(),promSave.getEndDate(),promSave.getPrice(), promSave.getUrl(),promSave.getAmount(),null) ;
        /*}catch (Exception e){
            throw new RuntimeException("Error al guardar la promoción");
        }*/

    }

    @Override
    public List<PromDTOResponse> findAll() {
        return promRepo.findAll().stream()
                .map(promEntity ->
                        new PromDTOResponse(promEntity.getId(),promEntity.getName(),promEntity.getDescription(),promEntity.getStartDate(),promEntity.getEndDate(),promEntity.getPrice(), promEntity.getUrl(),promEntity.getAmount(),
                                promEntity.getProductEntities().stream()
                                    .map(product -> new ProductDTOResponse(product.getId(), product.getName(), product.getAmount(), product.getPrice(), product.getDescription(),product.getDateBuying(),product.getCategory().getName(), product.getUrl(),product.getWeight()))
                                    .collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    @Override
    public PromDTOResponse findPromById(Long id) {
        return promRepo.findById(id)
                .map(promEntity -> new PromDTOResponse(promEntity.getId(),promEntity.getName(),promEntity.getDescription(),promEntity.getStartDate(),promEntity.getEndDate(),promEntity.getPrice(), promEntity.getUrl(),promEntity.getAmount(),
                        promEntity.getProductEntities().stream()
                                .map(product -> new ProductDTOResponse(product.getId(), product.getName(), product.getAmount(), product.getPrice(), product.getDescription(),product.getDateBuying(),product.getCategory().getName(),product.getUrl(),product.getWeight()))
                                .collect(Collectors.toSet())))
                .orElseThrow();
    }

    @Override
    public PromDTOResponse findPromByName(String name) {
        return promRepo.findPromEntityByName(name)
                .map(promEntity -> new PromDTOResponse(promEntity.getId(),promEntity.getName(),promEntity.getDescription(),promEntity.getStartDate(),promEntity.getEndDate(),promEntity.getPrice(), promEntity.getUrl(),promEntity.getAmount(),
                        promEntity.getProductEntities().stream()
                                .map(product -> new ProductDTOResponse(product.getId(), product.getName(), product.getAmount(), product.getPrice(), product.getDescription(),product.getDateBuying(),product.getCategory().getName(),product.getUrl(),product.getWeight()))
                                .collect(Collectors.toSet())))
                .orElseThrow();
    }

    @Override
    public PromDTOResponse updateProm(PromDTO promDTO,Long id) {

        PromEntity prom = promRepo.findById(id).orElseThrow(()-> new RuntimeException("La promocion no se encuentra en la base de datos"));

        Set<ProductEntity> productEntities = productRepo.findProductEntitiesByNameIn(promDTO.productEntities()).stream().collect(Collectors.toSet());

        prom.setName(promDTO.name());
        prom.setDescription(promDTO.description());
        prom.setPrice(promDTO.price());
        prom.setUrl(promDTO.url());
        prom.setAmount(promDTO.amount());
        prom.setProductEntities(productEntities);

        PromEntity promSave = promRepo.save(prom);

        Set<ProductDTOResponse> productDTOResponseSet = promSave.getProductEntities().stream()
                .map(product -> new ProductDTOResponse(product.getId(), product.getName(), product.getAmount(), product.getPrice(), product.getDescription(),product.getDateBuying(),product.getCategory().getName(),product.getUrl(),product.getWeight()))
                .collect(Collectors.toSet());
        return new PromDTOResponse(promSave.getId(),promSave.getName(),promSave.getDescription(),promSave.getStartDate(),promSave.getEndDate(),promSave.getPrice(), promSave.getUrl(),promSave.getAmount(),productDTOResponseSet);
    }

    @Override
    public void deleteProm(Long id) {
        promRepo.deleteById(id);
    }

    @Override
    public void updateAmountProm(List<PromInvoiceDTOResponse> promInvoiceDTOResponses) {
        promInvoiceDTOResponses.stream()
                .forEach(promUpdate -> {
                    PromEntity prom = promRepo.findById(promUpdate.id()).get();
                    if(prom.getAmount() < promUpdate.amount()){
                        throw new RuntimeException("La cantidad ingresada no coincide con la cantidad en stock");
                    }else{
                        prom.setAmount(prom.getAmount()-promUpdate.amount());
                        promRepo.save(prom);
                    }
                });
    }
}

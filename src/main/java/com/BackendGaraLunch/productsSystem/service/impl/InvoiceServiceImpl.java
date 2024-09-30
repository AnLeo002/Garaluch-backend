package com.BackendGaraLunch.productsSystem.service.impl;

import com.BackendGaraLunch.persistence.UserEntity;
import com.BackendGaraLunch.productsSystem.persisitence.InvoiceEntity;
import com.BackendGaraLunch.productsSystem.persisitence.ProductInvoiceEntity;
import com.BackendGaraLunch.productsSystem.persisitence.PromInvoiceEntity;
import com.BackendGaraLunch.productsSystem.repo.InvoiceRepo;
import com.BackendGaraLunch.productsSystem.repo.ProductRepo;
import com.BackendGaraLunch.productsSystem.repo.PromRepo;
import com.BackendGaraLunch.productsSystem.service.InvoiceService;
import com.BackendGaraLunch.productsSystem.service.dto.InvoiceDTO;
import com.BackendGaraLunch.productsSystem.service.dto.InvoiceDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.ProductInvoiceDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.PromInvoiceDTOResponse;
import com.BackendGaraLunch.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepo repo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PromRepo promRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    PromServiceImpl promService;
    @Autowired
    ProductServiceImpl productService;
    @Override
    public InvoiceDTOResponse findInvoiceById(Long id) {

        return repo.findById(id)
                .map(invoice -> new InvoiceDTOResponse(invoice.getId(),invoice.getUser().getUsername(),invoice.isPayment(),invoice.getTotal(),invoice.getDate(),
                                invoice.getPromInvoiceEntities().stream()
                                        .map(promInvoiceEntity -> new PromInvoiceDTOResponse(promInvoiceEntity.getId(),promInvoiceEntity.getPromAmount()))
                                        .collect(Collectors.toList()),
                                invoice.getProductInvoiceEntities().stream()
                                        .map(productInvoiceEntity -> new ProductInvoiceDTOResponse(productInvoiceEntity.getId(), productInvoiceEntity.getProductAmount()))
                                        .collect(Collectors.toList())))
                            .orElseThrow();
    }
    @Override
    public InvoiceDTOResponse createInvoice(InvoiceDTO invoiceDTO) {

        UserEntity user = userRepo.findUserEntityByUsername(invoiceDTO.username()).orElseThrow(()->new RuntimeException("El nombre de usuario no se encuentra registrado en el sistema"));

        InvoiceEntity invoice = InvoiceEntity.builder()
                .user(user)
                .payment(invoiceDTO.payment())
                .total(invoiceDTO.total())
                .date(LocalDate.now())
                .build();

        List<PromInvoiceEntity> promInvoiceEntities = invoiceDTO.promInvoiceDTOList().stream().map(prom ->
                PromInvoiceEntity.builder()
                        .prom(promRepo.findById(prom.id()).orElseThrow(()->new RuntimeException("Promoción no encontrada")))
                        .promAmount(prom.amount())
                        .invoice(invoice)
                        .build()).collect(Collectors.toList());
        List<ProductInvoiceEntity> productInvoiceEntities = invoiceDTO.productInvoiceDTOList().stream().map(product -> ProductInvoiceEntity.builder()
                        .product(productRepo.findById(product.id()).orElseThrow(()->new RuntimeException("Producto no encontrado")))
                        .productAmount(product.amount())
                        .invoice(invoice)
                        .build()).collect(Collectors.toList());

        invoice.setProductInvoiceEntities(productInvoiceEntities);
        invoice.setPromInvoiceEntities(promInvoiceEntities);

        promService.updateAmountProm(invoiceDTO.promInvoiceDTOList());
        productService.updateAmountProduct(invoiceDTO.productInvoiceDTOList());

        InvoiceEntity invoiceSave = repo.save(invoice);

        return new InvoiceDTOResponse(invoiceSave.getId(),invoiceSave.getUser().getUsername(),invoiceSave.isPayment(),invoiceSave.getTotal(),invoiceSave.getDate(),
                invoiceSave.getPromInvoiceEntities().stream()
                        .map(promInvoiceEntity -> new PromInvoiceDTOResponse(promInvoiceEntity.getId(),promInvoiceEntity.getPromAmount()))
                        .collect(Collectors.toList()),
                invoiceSave.getProductInvoiceEntities().stream()
                        .map(productInvoiceEntity -> new ProductInvoiceDTOResponse(productInvoiceEntity.getId(), productInvoiceEntity.getProductAmount()))
                        .collect(Collectors.toList()));
    }

    @Override
    public InvoiceDTOResponse updateInvoice(InvoiceDTO invoiceDTO) {
        UserEntity user = userRepo.findUserEntityByUsername(invoiceDTO.username()).orElseThrow(()->new RuntimeException("El nombre de usuario no se encuentra registrado en el sistema"));
        InvoiceEntity invoice = repo.findById(invoiceDTO.id()).orElseThrow(()->new RuntimeException("La factura no se encuentra en la base de datos"));

        invoice.setUser(user);
        invoice.setPayment(invoiceDTO.payment());
        invoice.setTotal(invoiceDTO.total());
        invoice.setProductInvoiceEntities(invoiceDTO.productInvoiceDTOList().stream().map(product -> ProductInvoiceEntity.builder()
                .product(productRepo.findById(product.id()).orElseThrow())
                .productAmount(product.amount())
                .build()).collect(Collectors.toList()));
        invoice.setPromInvoiceEntities(invoiceDTO.promInvoiceDTOList().stream().map(prom -> PromInvoiceEntity.builder()
                .prom(promRepo.findById(prom.id()).orElseThrow())
                .promAmount(prom.amount())
                .build()).collect(Collectors.toList()));

        InvoiceEntity invoiceSave = repo.save(invoice);

        return new InvoiceDTOResponse(invoiceSave.getId(),invoiceSave.getUser().getUsername(),invoiceSave.isPayment(),invoiceSave.getTotal(),invoiceSave.getDate(),
                invoiceSave.getPromInvoiceEntities().stream()
                        .map(promInvoiceEntity -> new PromInvoiceDTOResponse(promInvoiceEntity.getId(),promInvoiceEntity.getPromAmount()))
                        .collect(Collectors.toList()),
                invoiceSave.getProductInvoiceEntities().stream()
                        .map(productInvoiceEntity -> new ProductInvoiceDTOResponse(productInvoiceEntity.getId(), productInvoiceEntity.getProductAmount()))
                        .collect(Collectors.toList()));
    }

    @Override
    public List<InvoiceDTOResponse> findAllInvoices() {
        return repo.findAll().stream()
                .map(invoice -> new InvoiceDTOResponse(invoice.getId(),invoice.getUser().getUsername(),invoice.isPayment(),invoice.getTotal(),invoice.getDate(),
                        invoice.getPromInvoiceEntities().stream()
                                .map(promInvoiceEntity -> new PromInvoiceDTOResponse(promInvoiceEntity.getId(),promInvoiceEntity.getPromAmount()))
                                .collect(Collectors.toList()),
                        invoice.getProductInvoiceEntities().stream()
                                .map(productInvoiceEntity -> new ProductInvoiceDTOResponse(productInvoiceEntity.getId(), productInvoiceEntity.getProductAmount()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInvoice(Long id) {
        repo.deleteById(id);
    }

    @Override
    public InvoiceDTOResponse updateAmount(InvoiceDTO invoiceDTO) {

        productService.updateAmountProduct(invoiceDTO.productInvoiceDTOList());
        promService.updateAmountProm(invoiceDTO.promInvoiceDTOList());

        LocalDate date = repo.findById(invoiceDTO.id()).get().getDate();

        return new InvoiceDTOResponse(invoiceDTO.id(), invoiceDTO.username(), invoiceDTO.payment(), invoiceDTO.total(),date,invoiceDTO.promInvoiceDTOList(),invoiceDTO.productInvoiceDTOList());
    }

    @Override
    public List<InvoiceDTOResponse> findAllInvoicesByUsername(String username) {
         List<InvoiceDTOResponse> invoiceDTOResponses = repo.findByUserUsername(username).stream()
                .map(invoiceEntity -> new InvoiceDTOResponse(invoiceEntity.getId(),invoiceEntity.getUser().getUsername(), invoiceEntity.isPayment(), invoiceEntity.getTotal(),invoiceEntity.getDate(),
                        invoiceEntity.getPromInvoiceEntities().stream()
                                .map(prom -> new PromInvoiceDTOResponse(prom.getId(),prom.getPromAmount()))
                                .collect(Collectors.toList()),
                        invoiceEntity.getProductInvoiceEntities().stream()
                                .map(product -> new ProductInvoiceDTOResponse(product.getId(),product.getProductAmount()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

         if (invoiceDTOResponses.isEmpty()){
             return Collections.emptyList();//Si la lista esta vacia, va a retornar la lista vacia
         }else{
             return invoiceDTOResponses;
         }
    }
}

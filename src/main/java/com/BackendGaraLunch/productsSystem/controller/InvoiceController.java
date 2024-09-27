package com.BackendGaraLunch.productsSystem.controller;

import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTO;
import com.BackendGaraLunch.productsSystem.service.dto.CategoryDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.InvoiceDTO;
import com.BackendGaraLunch.productsSystem.service.dto.InvoiceDTOResponse;
import com.BackendGaraLunch.productsSystem.service.impl.CategoryServiceImpl;
import com.BackendGaraLunch.productsSystem.service.impl.InvoiceServiceImpl;
import com.BackendGaraLunch.productsSystem.service.impl.PromServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@Slf4j
public class InvoiceController {
    @Autowired
    private InvoiceServiceImpl service;

    @GetMapping("/findAll")
    public ResponseEntity<List> findAllInvoices(){
        return new ResponseEntity<>(service.findAllInvoices(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTOResponse> findInvoiceById(@PathVariable Long id){
        return new ResponseEntity<>(service.findInvoiceById(id),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<InvoiceDTOResponse> saveInvoice(@RequestBody InvoiceDTO invoiceDTO){
        try{
            return new ResponseEntity<>(service.createInvoice(invoiceDTO),HttpStatus.CREATED);
        }catch(Exception e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<InvoiceDTOResponse> updateInvoice(@PathVariable Long id,@RequestBody InvoiceDTO invoiceDTO){
        return ResponseEntity.ok(service.updateInvoice(invoiceDTO));
    }
    @PutMapping("/update/amount")
    public ResponseEntity<InvoiceDTOResponse> updateAmountInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return ResponseEntity.ok(service.updateAmount(invoiceDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoice(@PathVariable Long id){
        service.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

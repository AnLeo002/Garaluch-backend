package com.BackendGaraLunch.productsSystem.controller;

import com.BackendGaraLunch.productsSystem.service.dto.PromDTO;
import com.BackendGaraLunch.productsSystem.service.dto.PromDTOResponse;
import com.BackendGaraLunch.productsSystem.service.impl.PromServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prom")
@Slf4j
public class PromController {
    @Autowired
    private PromServiceImpl service;

    @GetMapping("/findAll")
    public ResponseEntity<List> findAllProms(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<PromDTOResponse> findPromByName(@PathVariable String name){
        return new ResponseEntity<>(service.findPromByName(name),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PromDTOResponse> findPromById(@PathVariable Long id){
        return new ResponseEntity<>(service.findPromById(id),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<PromDTOResponse> saveProm(@RequestBody PromDTO promDTO){
        log.info(promDTO.toString());
        return new ResponseEntity<>(service.saveProm(promDTO),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PromDTOResponse> updateProm(@PathVariable Long id,@RequestBody PromDTO promDTO){
        log.info(promDTO.toString());
        return ResponseEntity.ok(service.updateProm(promDTO,id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProm(@PathVariable Long id){
        service.deleteProm(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

package com.BackendGaraLunch.productsSystem.service;

import com.BackendGaraLunch.productsSystem.service.dto.PromDTO;
import com.BackendGaraLunch.productsSystem.service.dto.PromDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.PromInvoiceDTOResponse;

import java.util.List;

public interface PromService {
    PromDTOResponse saveProm(PromDTO promDTO);
    List<PromDTOResponse> findAll();
    PromDTOResponse findPromById(Long id);
    PromDTOResponse findPromByName(String name);
    PromDTOResponse updateProm(PromDTO promDTO,Long id);
    void deleteProm(Long id);
    void updateAmountProm(List<PromInvoiceDTOResponse> promInvoiceDTOResponses);
}

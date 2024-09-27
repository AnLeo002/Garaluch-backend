package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record InvoiceDTO (Long id,
                          @NonNull String username,
                           boolean payment,
                           BigDecimal total,
                          @NonNull List<PromInvoiceDTOResponse> promInvoiceDTOList,
                          @NonNull List<ProductInvoiceDTOResponse> productInvoiceDTOList){
}

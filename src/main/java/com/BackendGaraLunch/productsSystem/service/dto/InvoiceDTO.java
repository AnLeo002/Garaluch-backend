package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public record InvoiceDTO (Long id,
                          @NonNull String username,
                          @NonNull boolean payment,
                          @NonNull BigDecimal total,
                          @NonNull List<PromInvoiceDTOResponse> promInvoiceDTOList,
                          @NonNull List<ProductInvoiceDTOResponse> productInvoiceDTOList){
}

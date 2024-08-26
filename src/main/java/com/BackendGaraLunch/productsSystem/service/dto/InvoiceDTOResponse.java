package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public record InvoiceDTOResponse(@NonNull Long id,
                                 @NonNull String username,
                                  boolean payment,
                                  BigDecimal total,
                                 @NonNull List<PromInvoiceDTOResponse> promInvoiceList,
                                 @NonNull List<ProductInvoiceDTOResponse> productInvoiceList) {
}

package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record InvoiceDTOResponse(@NonNull Long id,
                                 @NonNull String username,
                                 @NonNull boolean payment,
                                 @NonNull BigDecimal total,
                                 LocalDate date,
                                 @NonNull List<PromInvoiceDTOResponse> promInvoiceList,
                                 @NonNull List<ProductInvoiceDTOResponse> productInvoiceList) {
}

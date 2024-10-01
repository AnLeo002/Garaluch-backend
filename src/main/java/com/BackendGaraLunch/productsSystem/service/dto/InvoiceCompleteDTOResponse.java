package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public record InvoiceCompleteDTOResponse(Long id,
                                         @NonNull String username,
                                         boolean payment,
                                         BigDecimal total,
                                         @NonNull List<PromDTOResponse> promDTOList,
                                         @NonNull List<ProductDTOResponse> productDTOList

) {
}

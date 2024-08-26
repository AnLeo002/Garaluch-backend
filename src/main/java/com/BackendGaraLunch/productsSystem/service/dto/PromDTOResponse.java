package com.BackendGaraLunch.productsSystem.service.dto;

import com.BackendGaraLunch.productsSystem.persisitence.ProductEntity;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record PromDTOResponse(Long id,
                              @NonNull String name,
                              @NonNull String description,
                              @NonNull LocalDate startDate,
                              @NonNull LocalDate endDate,
                               BigDecimal price,
                               String url,
                              int amount,
                              Set<ProductDTOResponse> productEntities) {
}

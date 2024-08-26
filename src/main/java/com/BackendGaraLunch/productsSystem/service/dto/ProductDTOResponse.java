package com.BackendGaraLunch.productsSystem.service.dto;

import com.BackendGaraLunch.productsSystem.persisitence.CategoryEntity;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record ProductDTOResponse(Long id,
                                 @NonNull String name,
                                 @NonNull int amount,
                                 @NonNull BigDecimal price,
                                 @NonNull String description,
                                 @NonNull LocalDate dayBuying,
                                 @NonNull String category,
                                 String url) {
}

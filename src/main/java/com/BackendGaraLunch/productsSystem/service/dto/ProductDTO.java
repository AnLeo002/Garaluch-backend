package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(Long id,
                         @NonNull String name,
                         @NonNull int amount,
                         @NonNull BigDecimal price,
                         @NonNull String description,
                         @NonNull String category,
                         @NonNull String dayBuying,
                         @NonNull String url,
                         @NonNull String weight){ }
package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record PromDTO (@NonNull String name,
                        @NonNull String description,
                        @NonNull String startDate,
                        @NonNull String endDate,
                        @NonNull BigDecimal price,
                        @NonNull String url,
                        @NonNull int amount,
                        List<String> productEntities) {
}

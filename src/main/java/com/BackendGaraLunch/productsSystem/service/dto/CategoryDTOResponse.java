package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

public record CategoryDTOResponse(@NonNull Long id,
                                  @NonNull String name) {
}

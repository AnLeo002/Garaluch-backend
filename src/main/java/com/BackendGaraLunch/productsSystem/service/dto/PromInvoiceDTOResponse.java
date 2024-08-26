package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

public record PromInvoiceDTOResponse(@NonNull Long id,
                                     @NonNull int amount) {
}

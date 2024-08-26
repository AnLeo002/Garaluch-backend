package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

public record ProductInvoiceDTOResponse(@NonNull Long id,
                                        @NonNull int amount) {
}

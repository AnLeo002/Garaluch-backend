package com.BackendGaraLunch.productsSystem.service.dto;

import lombok.NonNull;

public record PromInvoiceDTOResponse( Long id,
                                     @NonNull int amount) {
}

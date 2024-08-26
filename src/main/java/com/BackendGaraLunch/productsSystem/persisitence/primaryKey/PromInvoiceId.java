package com.BackendGaraLunch.productsSystem.persisitence.primaryKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PromInvoiceId implements Serializable {//En esta clase vamos a genera una llave primaria para unir 2 tablas
    private Long invoiceId;
    private Long promId;
}

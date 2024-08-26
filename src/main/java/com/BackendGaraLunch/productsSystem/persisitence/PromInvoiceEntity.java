package com.BackendGaraLunch.productsSystem.persisitence;

import com.BackendGaraLunch.productsSystem.persisitence.primaryKey.PromInvoiceId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "invoice_proms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromInvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = PromEntity.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "prom_id")
    private PromEntity prom;
    @ManyToOne(targetEntity = InvoiceEntity.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;
    private int promAmount;

}

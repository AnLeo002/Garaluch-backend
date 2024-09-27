package com.BackendGaraLunch.productsSystem.persisitence;

import com.BackendGaraLunch.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private UserEntity user;
    private boolean payment;
    private BigDecimal total;
    private LocalDate date;
    @OneToMany(targetEntity = PromInvoiceEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "invoice",orphanRemoval = true)
    private List<PromInvoiceEntity> promInvoiceEntities = new ArrayList<>();
    @OneToMany(targetEntity = ProductInvoiceEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "invoice",orphanRemoval = true)
    private List<ProductInvoiceEntity> productInvoiceEntities = new ArrayList<>();
}

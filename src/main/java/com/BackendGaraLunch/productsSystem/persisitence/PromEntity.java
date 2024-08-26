package com.BackendGaraLunch.productsSystem.persisitence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "promotions")
public class PromEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String url;
    private BigDecimal price;
    private int amount;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "prom_product" ,joinColumns = @JoinColumn(name = "prom_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<ProductEntity> productEntities = new HashSet<>();

    @OneToMany(targetEntity = PromInvoiceEntity.class,fetch = FetchType.LAZY,mappedBy = "prom",cascade =  CascadeType.ALL )
    private List<PromInvoiceEntity> promInvoiceEntities = new ArrayList<>();
}

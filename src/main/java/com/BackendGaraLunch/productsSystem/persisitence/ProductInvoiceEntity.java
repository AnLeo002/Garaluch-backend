package com.BackendGaraLunch.productsSystem.persisitence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_product")
@Builder
public class ProductInvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = ProductEntity.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@MapsId("id")//La info que va a tener esta tabla solo sera el id
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne(targetEntity = InvoiceEntity.class,fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private InvoiceEntity invoice;
    private int productAmount;
}

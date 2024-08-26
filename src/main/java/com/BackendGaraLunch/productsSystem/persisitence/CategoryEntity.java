package com.BackendGaraLunch.productsSystem.persisitence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity = ProductEntity.class,fetch = FetchType.LAZY ,mappedBy = "category")
    private List<ProductEntity> productEntities;

}

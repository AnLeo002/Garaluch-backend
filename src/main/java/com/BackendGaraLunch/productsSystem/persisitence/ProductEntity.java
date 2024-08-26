package com.BackendGaraLunch.productsSystem.persisitence;

import com.BackendGaraLunch.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int amount;
    private BigDecimal price;
    @Column(name = "date_buying")
    private LocalDate dateBuying;
    private String description;
    private String url;
    private String weight;
    @ManyToOne(targetEntity = CategoryEntity.class ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private CategoryEntity category;
    @OneToMany(mappedBy = "product")
    private List<ProductInvoiceEntity> productInvoiceEntities;

    /*@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "products_users",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userEntityList = new ArrayList<>();*/

}

package com.BackendGaraLunch.productsSystem.repo;

import com.BackendGaraLunch.productsSystem.persisitence.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Long> {

    Optional<ProductEntity> findProductEntityByName (String name);
    List<ProductEntity> findProductEntitiesByNameIn(List<String> name);
}

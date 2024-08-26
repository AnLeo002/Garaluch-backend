package com.BackendGaraLunch.productsSystem.repo;

import com.BackendGaraLunch.productsSystem.persisitence.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findCategoryEntityByName(String name);
    List<CategoryEntity> findCategoryEntitiesByNameIn(List<String> name);
}

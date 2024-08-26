package com.BackendGaraLunch.productsSystem.repo;

import com.BackendGaraLunch.persistence.RoleEntity;
import com.BackendGaraLunch.productsSystem.persisitence.PromEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromRepo extends JpaRepository<PromEntity,Long> {
    Optional<PromEntity> findPromEntityByName(String name);
}

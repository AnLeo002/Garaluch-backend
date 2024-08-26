package com.BackendGaraLunch.productsSystem.repo;

import com.BackendGaraLunch.productsSystem.persisitence.PromInvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromInvoiceRepo extends JpaRepository<PromInvoiceEntity,Long> {
}

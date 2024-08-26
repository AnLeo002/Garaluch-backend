package com.BackendGaraLunch.productsSystem.repo;

import com.BackendGaraLunch.productsSystem.persisitence.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceEntity, Long> {

}

package com.BackendGaraLunch.productsSystem.repo;

import com.BackendGaraLunch.productsSystem.persisitence.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceEntity, Long> {
    public List<InvoiceEntity> findByUserUsername(String username);//Con este metodo podremos encontrar las facturas buscando el nombre usuario del objeto UserEntity
}

package org.baps.api.demo.repositories;

import org.baps.api.demo.models.entities.Karyakar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KaryakarRepository extends JpaRepository<Karyakar, Long>, JpaSpecificationExecutor<Karyakar> {
    
}
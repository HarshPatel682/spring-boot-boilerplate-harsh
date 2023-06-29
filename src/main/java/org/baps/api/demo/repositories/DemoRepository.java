package org.baps.api.demo.repositories;

import org.baps.api.demo.models.entities.Demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemoRepository extends JpaRepository<Demo, String>, JpaSpecificationExecutor<Demo> {
}

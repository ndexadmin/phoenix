package com.ndexsystems.phoenix.repositories;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndexsystems.phoenix.entities.OrganizationUnit;

@Repository
public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, BigDecimal> {
   
}

package com.ndexsystems.phoenix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.ndexsystems.phoenix.entities.FinancialInstitution;

@Repository
public interface FinancialInstitutionRepository extends JpaRepository<FinancialInstitution, Long> {

	@Procedure(procedureName = "sps_FinancialInstitution")
	FinancialInstitution loadFinancialInstitution();
}

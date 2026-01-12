package com.ndexsystems.phoenix.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ndexsystems.phoenix.entities.AuditItem;

@Repository
public interface AuditItemRepository extends JpaRepository<AuditItem, BigDecimal> {
	@Query("select coalesce(max(a.OBJECT_ID), 0) from AuditItem a")
	BigDecimal findMaxObjectId();
}

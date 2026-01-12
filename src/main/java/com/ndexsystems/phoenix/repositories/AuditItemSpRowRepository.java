// ==============================
// REPO 2 : AuditItemSpRowRepository (LIST SPs)
// ==============================
package com.ndexsystems.phoenix.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ndexsystems.phoenix.entities.AuditItemSpRow;

@Repository
public interface AuditItemSpRowRepository extends JpaRepository<AuditItemSpRow, BigDecimal> {
    @Procedure(name = "AuditItemSpRow.byImportance")
    List<AuditItemSpRow> findByImportance(
        @Param("v_h_cursorStart") Integer cursorStart,
        @Param("v_h_range") Integer range,
        @Param("v_h_organizationUnitId") String organizationUnitId,
        @Param("v_h_selUserId") String selUserId,
        @Param("v_h_selCategory") String selCategory,
        @Param("v_h_selImportance") String selImportance,
        @Param("v_h_selStartingDate") LocalDateTime selStartingDate,
        @Param("v_h_selEndingDate") LocalDateTime selEndingDate,
        @Param("v_h_searchDescription") String searchDescription,
        @Param("v_h_searchCriteria") String searchCriteria,
        @Param("v_h_language") String language
    );

    @Procedure(name = "AuditItemSpRow.byDescDate")
    List<AuditItemSpRow> findByDescDate(
        @Param("v_h_cursorStart") Integer cursorStart,
        @Param("v_h_range") Integer range,
        @Param("v_h_organizationUnitId") String organizationUnitId,
        @Param("v_h_selUserId") String selUserId,
        @Param("v_h_selCategory") String selCategory,
        @Param("v_h_selImportance") String selImportance,
        @Param("v_h_selStartingDate") LocalDateTime selStartingDate,
        @Param("v_h_selEndingDate") LocalDateTime selEndingDate,
        @Param("v_h_searchDescription") String searchDescription,
        @Param("v_h_searchCriteria") String searchCriteria,
        @Param("v_h_language") String language
    );
}

// ==============================
// ENTITY 2 : SP RESULT (LIST SPs)
// ==============================
package com.ndexsystems.phoenix.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "AuditItemSpRow.byImportance",
        procedureName = "sp_AuditItemByImportance",
        resultClasses = AuditItemSpRow.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_cursorStart", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_range", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_organizationUnitId", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selUserId", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selCategory", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selImportance", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selStartingDate", type = LocalDateTime.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selEndingDate", type = LocalDateTime.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_searchDescription", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_searchCriteria", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_language", type = String.class)
        }
    ),

    @NamedStoredProcedureQuery(
        name = "AuditItemSpRow.byDescDate",
        procedureName = "sp_AuditItemByDescDate",
        resultClasses = AuditItemSpRow.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_cursorStart", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_range", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_organizationUnitId", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selUserId", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selCategory", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selImportance", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selStartingDate", type = LocalDateTime.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_selEndingDate", type = LocalDateTime.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_searchDescription", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_searchCriteria", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_h_language", type = String.class)
        }
    )
})
@Entity
@Table(name = "AuditItem")
@Data
public class AuditItemSpRow {

	@Id
    private BigDecimal OBJECT_ID;
    private String category;
    private LocalDateTime nDate;
    private String englishDescription;
    @Transient
    private String frenchDescription;
    private String importance;
    private String userId;
}

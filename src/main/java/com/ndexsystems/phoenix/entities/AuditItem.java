package com.ndexsystems.phoenix.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "AuditItem")
@Data
public class AuditItem {

	@Id
	@Column(name = "OBJECT_ID")
	private BigDecimal OBJECT_ID;

	@Column(name = "OBJECT_TIMESTAMP")
	private BigDecimal OBJECT_TIMESTAMP;

	@Column(name = "OBJECT_COUNTER")
	private Integer OBJECT_COUNTER;
	
	@Column(name = "Institution_Key")
	private BigDecimal institution_Key;

	@Column(length = 5)
	private String category;

	private LocalDateTime nDate;

	@Column(length = 2048)
	private String englishDescription;

	@Column(length = 2048)
	private String frenchDescription;

	@Column(length = 5)
	private String importance;

	@Column(length = 45)
	private String userId;

	private BigDecimal StemLog_Key;

	@PrePersist
	void prePersist() {
		if (OBJECT_TIMESTAMP == null) {
			OBJECT_TIMESTAMP = BigDecimal.valueOf(System.currentTimeMillis());
		}
		if (OBJECT_COUNTER == null) {
			OBJECT_COUNTER = 1;
		}
	}
}
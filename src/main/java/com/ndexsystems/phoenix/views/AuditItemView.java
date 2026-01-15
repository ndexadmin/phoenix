package com.ndexsystems.phoenix.views;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuditItemView {

	private String category;
	private LocalDateTime nDate;
	private String description;
	private String importance;
	private String userId;
	
	public AuditItemView() {}
}
package com.ndexsystems.phoenix.views;

import lombok.Data;

@Data
public class ManualAuditLogView {
	private String activityDate;
	private String importance;
	private String activityType;
	private String userId;
	private String englishDescription;
    private String frenchDescription;
}

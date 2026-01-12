package com.ndexsystems.phoenix.views;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ActivitySearchView {

	private String category;
	private String importance;
	private String userId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fromDate = LocalDate.now();
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate toDate = LocalDate.now();
	private String searchDescription;
	private String searchCriteria = "CONTAINS";
}
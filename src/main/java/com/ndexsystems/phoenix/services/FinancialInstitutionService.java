package com.ndexsystems.phoenix.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.config.TenantContext;
import com.ndexsystems.phoenix.mapper.FinancialInstitutionViewMapper;
import com.ndexsystems.phoenix.repositories.FinancialInstitutionRepository;
import com.ndexsystems.phoenix.views.FinancialInstitutionView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class FinancialInstitutionService {

	private final FinancialInstitutionRepository repository;
	private final FinancialInstitutionViewMapper mapper;
	
	
	@Transactional
	public FinancialInstitutionView load() {
		return Optional.ofNullable(repository.loadFinancialInstitution()).map(mapper::entityToViewtoView).orElseGet(() -> {
			log.warn("FinancialInstitution introuvable pour le tenant {}", TenantContext.getTenant());
			return null;
		});
	}

	
}


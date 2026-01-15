package com.ndexsystems.phoenix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ndexsystems.phoenix.entities.OrganizationUnit;
import com.ndexsystems.phoenix.views.OrganizationUnitView;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrganizationUnitMapperEntityToView {
	OrganizationUnitView toView(OrganizationUnit entity);
}

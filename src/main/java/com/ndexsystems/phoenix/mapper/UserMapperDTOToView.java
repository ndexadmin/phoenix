package com.ndexsystems.phoenix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.views.UserView;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapperDTOToView {

	UserView toView(UserDTO dto);
}

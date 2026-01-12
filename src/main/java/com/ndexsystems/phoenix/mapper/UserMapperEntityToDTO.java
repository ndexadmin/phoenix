package com.ndexsystems.phoenix.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ndexsystems.phoenix.dto.NdexEnumeration;
import com.ndexsystems.phoenix.dto.NdexEnumerationElement;
import com.ndexsystems.phoenix.dto.NdexEnumerations;
import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.entities.User;
import com.ndexsystems.phoenix.util.UserContext;

@Mapper(componentModel = "spring")
public abstract class UserMapperEntityToDTO {

	@Mapping(target = "type", ignore = true)
	@Mapping(target = "status", ignore = true)
	public abstract UserDTO toDTO(User entity);

	@AfterMapping
	protected void mapStatus(User entity, @MappingTarget UserDTO dto) {
		if (entity.getStatus() == null)
			return;

		for (UserStatus us : UserStatus.values()) {
			if (us.getPersistentId().equalsIgnoreCase(entity.getStatus())) {
				dto.setStatus(us);
				return;
			}
		}
	}
	@AfterMapping
	protected void mapType(User entity, @MappingTarget UserDTO dto) {

	    UserLanguageResources resources = UserContext.get();
	    if (resources == null || resources.ndexEnums() == null)
	        return;

	    NdexEnumerations ndex = resources.ndexEnums();

	    String ntype = entity.getNtype();
	    if (ntype == null || ntype.isBlank())
	        return;

	    NdexEnumeration enumUserType = ndex.get("usertype");
	    if (enumUserType == null)
	        return;

	    NdexEnumerationElement resolved = enumUserType.getByBackOfficeId(ntype);
	    if (resolved != null) {
	        dto.setType(resolved);
	    }
	}
}

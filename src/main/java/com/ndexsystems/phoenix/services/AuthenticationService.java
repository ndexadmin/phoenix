package com.ndexsystems.phoenix.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.entities.User;
import com.ndexsystems.phoenix.mapper.UserMapperDTOToView;
import com.ndexsystems.phoenix.mapper.UserMapperEntityToDTO;
import com.ndexsystems.phoenix.repositories.UserRepository;
import com.ndexsystems.phoenix.services.security.PasswordValidator;
import com.ndexsystems.phoenix.services.security.ProductAccessService;
import com.ndexsystems.phoenix.views.UserView;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	private final ProductAccessService productAccessService;
	private final PasswordValidator passwordValidator;
	private final UserMapperEntityToDTO mapperEntityToDTO;
	private final UserMapperDTOToView mapperDTOToView;

	@Transactional
	public UserView authenticate(String loginId, String password, String product) {
		User user = null;
		user = repository.userTableLoginId(loginId);
		if (user == null) {
			throw new RuntimeException("Invalid login");
		}
		UserDTO userDTO = mapperEntityToDTO.toDTO(user);
		if (!productAccessService.isAllowedToUseProduct(userDTO, product)) {
			throw new RuntimeException("Product access denied");
		}

		String digest = user.getPassword();
		String salt = user.getSalt();

		boolean ok = passwordValidator.validPassword(password, digest, salt);

		if (!ok) {
			throw new RuntimeException("Invalid password");
		}
		return mapperDTOToView.toView(userDTO);
	}
}

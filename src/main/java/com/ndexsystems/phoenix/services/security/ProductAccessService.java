package com.ndexsystems.phoenix.services.security;

import org.springframework.stereotype.Service;

import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.util.Constants;

@Service
public class ProductAccessService {
	
	

	public boolean isAllowedToUseProduct(UserDTO user, String product) {

        if (product == null) return false; 	
        String type = user.getType().backOfficeId();

        if (product.equalsIgnoreCase(Constants.PRODUCT_FULLSERVICE)) {
            return type.equals(Constants.TYPE_0)
                    || type.equals(Constants.TYPE_1);
        }

        if (product.equalsIgnoreCase(Constants.PRODUCT_ONLINE)) {
            return type.equals(Constants.TYPE_2)
                    || type.equalsIgnoreCase(Constants.TYPE_OTHER)
                    || type.equalsIgnoreCase(Constants.TYPE_DOCSH);
        }

        return product.isEmpty();
    }
}
	

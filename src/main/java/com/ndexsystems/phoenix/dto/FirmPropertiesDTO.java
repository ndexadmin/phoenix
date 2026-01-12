package com.ndexsystems.phoenix.dto;

import java.util.Collection;
import java.util.HashMap;

import com.ndexsystems.phoenix.dto.enume.Custodian;

import lombok.Data;

@Data
public class FirmPropertiesDTO {
	private static final String ALL_CUSTODIAN_ID = "*";
	private String firmId;
	private HashMap<String, String> properties = new HashMap<>();
	private Collection<Custodian> custodians = null;
	private Collection<Custodian> activeCustodians = null;
	private HashMap<Custodian, Integer> rankByCustodian = null;
	private String singleFirmProperty = null;
	
	
	public FirmPropertiesDTO(String firmId) {
	    this.firmId = firmId;
	}
	
    private String getFirmProperty(String propertyName, String custodianId) {

        if (custodianId == null) {
            custodianId = ALL_CUSTODIAN_ID;
        }

        String key = propertyName + "." + custodianId.toUpperCase();
        return properties.get(key);
    }
	
	  public boolean isAmericanBehavior() {
	        return Boolean.parseBoolean(
	            getFirmProperty("american.behaviour.at.custodian.level", ALL_CUSTODIAN_ID)
	        );
	    }


	public boolean isTaxLotAccountLevel() {
		return false;
	}


	public boolean isTaxLotAccountingAtCustodianLevel() {
		 return Boolean.parseBoolean(
		            getFirmProperty("tax.lot.accounting.at.custodian.level", ALL_CUSTODIAN_ID)
		        );
		    }


	public boolean isTaxLotAccountingOutsideCustodian() {
		return false;
	}

}

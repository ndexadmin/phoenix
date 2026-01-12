package com.ndexsystems.phoenix.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class NdexEnumeration {

	private final String name;

	private final Map<String, NdexEnumerationElement> resourceDomain = new LinkedHashMap<>();

	private final Map<String, NdexEnumerationElement> backOfficeDomain = new LinkedHashMap<>();

	private NdexEnumerationElement defaultValue;

	public NdexEnumeration(String name) {
		this.name = name;
	}

	public NdexEnumerationElement getByResourceKey(String key) {
		return resourceDomain.get(key);
	}

	public NdexEnumerationElement getByBackOfficeId(String id) {
		return backOfficeDomain.get(id);
	}

	public NdexEnumerationElement etByBackOfficeId(String id) {
		return backOfficeDomain.get(id);
	}

	public Collection<NdexEnumerationElement> getAll() {
		return resourceDomain.values();
	}

	static NdexEnumeration fromProperties(String name, Map<String, String> props) {

		NdexEnumeration enumeration = new NdexEnumeration(name);

		Map<String, Map<String, String>> grouped = new HashMap<>();

		props.forEach((fullKey, value) -> {

			String[] parts = fullKey.split("\\.");

			if (parts.length == 2) {

				return;
			}

			String resourceKey = parts[1];
			String attr = parts[2];

			grouped.computeIfAbsent(resourceKey, k -> new HashMap<>()).put(attr, value);
		});

		grouped.forEach((resourceKey, values) -> {

			String backOfficeId = values.get("backofficeid");
			String displayValue = values.get("value");

			NdexEnumerationElement element = new NdexEnumerationElement(backOfficeId, name, resourceKey, displayValue);

			enumeration.resourceDomain.put(resourceKey, element);

			if (backOfficeId != null) {
				enumeration.backOfficeDomain.put(backOfficeId, element);
			}
		});

		props.forEach((fullKey, value) -> {
			if (fullKey.endsWith(".defaultvalue")) {
				String[] parts = value.split("\\.");
				String defaultKey = parts[1];
				enumeration.defaultValue = enumeration.resourceDomain.get(defaultKey);
			}
		});

		return enumeration;
	}
}

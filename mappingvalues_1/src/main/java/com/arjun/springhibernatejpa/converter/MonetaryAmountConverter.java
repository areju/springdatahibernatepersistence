package com.arjun.springhibernatejpa.converter;

import javax.persistence.AttributeConverter;

import com.arjun.springhibernatejpa.model.MonetaryAmount;

public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String>{

	@Override
	public String convertToDatabaseColumn(MonetaryAmount attribute) {
		return attribute.toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String dbData) {
		return MonetaryAmount.fromString(dbData);
	}

}

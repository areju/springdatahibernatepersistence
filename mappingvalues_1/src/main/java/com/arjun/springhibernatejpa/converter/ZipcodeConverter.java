package com.arjun.springhibernatejpa.converter;

import javax.persistence.AttributeConverter;

import com.arjun.springhibernatejpa.model.GermanZipcode;
import com.arjun.springhibernatejpa.model.SwissZipcode;
import com.arjun.springhibernatejpa.model.Zipcode;

public class ZipcodeConverter implements AttributeConverter<Zipcode,String> {

	@Override
	public String convertToDatabaseColumn(Zipcode attribute) {
		return attribute.getValue();
	}

	@Override
	public Zipcode convertToEntityAttribute(String dbData) {
		if(dbData.length() == 5)
			return new GermanZipcode(dbData);
		if(dbData.length() == 4)
			return new SwissZipcode(dbData);
		
		throw new IllegalArgumentException("Invalid Zip code ..");
	}

}
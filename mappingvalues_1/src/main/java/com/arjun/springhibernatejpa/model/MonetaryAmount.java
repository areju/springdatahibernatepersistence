package com.arjun.springhibernatejpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MonetaryAmount implements Serializable {
	
	private final BigDecimal value;
	private final Currency currency;
	
	public MonetaryAmount(BigDecimal val, Currency cur) {
		this.value = val;
		this.currency = cur;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		return ((MonetaryAmount) obj).getValue() == this.getValue() && ((MonetaryAmount) obj).getCurrency() == this.getCurrency(); 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(value,currency);
	}
	
	@Override
	public String toString() {
		return value + " " + currency;
	}
	
	public static MonetaryAmount fromString(String s) {
		String[] split = s.split(" ");
		return new MonetaryAmount(new BigDecimal(split[0]),Currency.getInstance(split[1]));
	}
	

}

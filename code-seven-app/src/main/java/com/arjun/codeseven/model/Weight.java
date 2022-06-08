package com.arjun.codeseven.model;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AttributeOverride( name = "name", column = @Column(name = "WEIGHT_NAME"))
@AttributeOverride( name = "symbol", column = @Column(name = "WEIGHT_SYMBOL"))
public class Weight extends Measurement {
	
	public static Weight kilograms(String symbol_name, String symbol, BigDecimal weight) {
		return new Weight("Kilograms", "kgs", weight);
		
	}
	
	public static Weight pounds(String symbol_name, String symbol, BigDecimal weight) {
		return new Weight("Pounds","lbs",weight);
	}
	
	@NotNull
	private BigDecimal weight;
	
	public Weight() {
		
	}
	
	public Weight(String symbol_name, String symbol, BigDecimal weight) {
		super(symbol_name,symbol);
		this.weight = weight;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	

}

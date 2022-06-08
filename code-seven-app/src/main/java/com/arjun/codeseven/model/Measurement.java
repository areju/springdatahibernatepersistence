package com.arjun.codeseven.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Measurement {
	@NotNull
	private String name;
	@NotNull
	private String symbol;

	protected Measurement() {

	}

	public Measurement(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}

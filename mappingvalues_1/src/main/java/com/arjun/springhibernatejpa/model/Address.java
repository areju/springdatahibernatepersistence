package com.arjun.springhibernatejpa.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
	
	@NotNull
	@Column(nullable = false)
	private String street;
		
	@NotNull
	@AttributeOverride(
			name = "name",
			column = @Column(name = "CITY", nullable = false))
	private City city;
	
	public Address() {
		
	}
	
	
    public Address(String street, String zipcode, City city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}

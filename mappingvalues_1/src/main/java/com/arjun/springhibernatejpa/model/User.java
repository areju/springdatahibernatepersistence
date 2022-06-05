package com.arjun.springhibernatejpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.manning.javapersistence.ch06.model.Address;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;
	
	private String username;
	
	private Address homeAddress;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
	
	
	

}

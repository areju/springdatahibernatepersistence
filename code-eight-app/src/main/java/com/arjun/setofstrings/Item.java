package com.arjun.setofstrings;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.arjun.Constants;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id;
	
	private String name;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "IMAGE",
			joinColumns = @JoinColumn(name = "ITEM_ID"))
	@Column(name = "FILENAME")
	private Set<String> images = new HashSet<>();
	
	public Item() {
		
	}
		
	public Item(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getImages() {
		return Collections.unmodifiableSet(images);
	}
	
	public void addImage(String img) {
		images.add(img);
	}
	
	
	

}

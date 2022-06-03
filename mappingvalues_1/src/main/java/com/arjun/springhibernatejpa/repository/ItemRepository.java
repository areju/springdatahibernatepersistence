package com.arjun.springhibernatejpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.arjun.springhibernatejpa.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	
	Iterable<Item> findByMetricWeight(double weight);

}

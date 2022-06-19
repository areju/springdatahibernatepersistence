package com.arjun.repositories.setofstrings;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arjun.setofstrings.Item;


public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Query("select i from Item i inner join fetch i.images where i.id = :id ")
	public Item findItemWithImages(@Param("id") Long id);
	
	@Query(value = "select FILENAME from IMAGE where ITEM_ID = ?1", nativeQuery = true)
	public Set<String> findImagesNative(@Param("id") Long id);

}

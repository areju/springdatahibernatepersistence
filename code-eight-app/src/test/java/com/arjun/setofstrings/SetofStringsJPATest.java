package com.arjun.setofstrings;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.config.setofstrings.SpringDataConfiguration;
import com.arjun.repositories.setofstrings.ItemRepository;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class SetofStringsJPATest {

	@Autowired
	private ItemRepository itemRepo;
	
	@Test
	void storeLoadEntities( ) {
		
		Item item = new Item("Spring");
		item.addImage("img1.jpg");
		item.addImage("img2.jpg");
		item.addImage("img3.jpg");
		item.addImage("img4.jpg");
		
		itemRepo.save(item);
		
		Item item2 = itemRepo.findItemWithImages(item.getId());
		
		List<Item> items2 = itemRepo.findAll();
		
		Set<String> images = itemRepo.findImagesNative(item.getId());
		
		assertAll(
				() -> assertEquals(item2.getImages().size(), items2.get(0).getImages().size()),
				() -> assertEquals(item2.getImages().size(), images.size()),
				() -> assertEquals(item2.getImages().size(), 4),
				() -> assertEquals( images.size(), 4)
				);
		
	}
	
}

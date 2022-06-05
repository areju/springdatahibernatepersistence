package com.arjun.springhibernatejpa;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.springhibernatejpa.configuration.SpringDataConfiguration;
import com.arjun.springhibernatejpa.model.Item;
import com.arjun.springhibernatejpa.model.User;
import com.arjun.springhibernatejpa.repository.ItemRepository;
import com.arjun.springhibernatejpa.repository.UserRepository;




@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingValuesSpringDataJPATest {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {

        User user = new User();
        user.setUsername("username");
        userRepository.save(user);

        Item item = new Item();
        item.setName("Some Item");
        item.setMetricWeight(2);
        item.setDescription("descriptiondescription");
        itemRepository.save(item);

        List<User> users = (List<User>) userRepository.findAll();
        List<Item> items = (List<Item>) itemRepository.findByMetricWeight(2.0);

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("username", users.get(0).getUsername()),
                () -> assertEquals(1, items.size()),
                () -> assertEquals("AUCTION: Some Item", items.get(0).getName()),
                () -> assertEquals("descriptiondescription", items.get(0).getDescription()),
                () -> assertEquals("descriptiond...", items.get(0).getShortDescription()),
                () -> assertEquals(2.0, items.get(0).getMetricWeight())
        );

    }

}
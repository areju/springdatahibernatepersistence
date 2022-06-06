package com.arjun.springhibernatejpa;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.hibernate.type.descriptor.java.LocaleTypeDescriptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.springhibernatejpa.configuration.SpringDataConfiguration;
import com.arjun.springhibernatejpa.model.Address;
import com.arjun.springhibernatejpa.model.AuctionType;
import com.arjun.springhibernatejpa.model.City;
import com.arjun.springhibernatejpa.model.Item;
import com.arjun.springhibernatejpa.model.MonetaryAmount;
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

    	City city = new City();
    	city.setName("abc city");
    	city.setCountry("abc country");
    	city.setZipcode("12345");
    	
        User user = new User();
        user.setUsername("abc user");
        user.setHomeAddress(new Address("abc street", city));
        userRepository.save(user);

        Item item = new Item();
        item.setName("Some Item");
        item.setMetricWeight(2);
        item.setDescription("descriptiondescription");
        item.setBuyNowPrice(new MonetaryAmount(new BigDecimal("1.1"), Currency.getInstance(Locale.US)));
        itemRepository.save(item);

        List<User> users = (List<User>) userRepository.findAll();
        List<Item> items = (List<Item>) itemRepository.findByMetricWeight(2.0);

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("abc user", users.get(0).getUsername()),
                () -> assertEquals("abc street", user.getHomeAddress().getStreet()),
                () -> assertEquals("12345", user.getHomeAddress().getCity().getZipcode()),
                () -> assertEquals("abc city", user.getHomeAddress().getCity().getName()),
                () -> assertEquals("abc country", user.getHomeAddress().getCity().getCountry()),
                () -> assertEquals(1, items.size()),
                () -> assertEquals("AUCTION: Some Item", items.get(0).getName()),
                () -> assertEquals(AuctionType.HIGHEST_BID, items.get(0).getAuctionType()),
                () -> assertEquals("descriptiondescription", items.get(0).getDescription()),
                () -> assertEquals("descriptiond...", items.get(0).getShortDescription()),
                () -> assertEquals(2.0, items.get(0).getMetricWeight()),
                () -> assertEquals(LocalDate.now(), items.get(0).getCreatedOn()),
                () -> assertTrue(ChronoUnit.SECONDS.between(LocalDateTime.now(),items.get(0).getLastModified()) < 1),
                () -> assertEquals(new BigDecimal("1.00"), items.get(0).getInitialPrice()),
                () -> assertEquals(new BigDecimal("1.1") , items.get(0).getBuyNowPrice().getValue()),
                () -> assertEquals(Currency.getInstance(Locale.US), items.get(0).getBuyNowPrice().getCurrency()),
                () -> assertEquals("1.1 USD", items.get(0).getBuyNowPrice().toString())
        );

    }

}

package com.arjun.codeseven;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.codeseven.configuration.SpringDataConfiguration;
import com.arjun.codeseven.model.BankAccount;
import com.arjun.codeseven.model.BillingDetails;
import com.arjun.codeseven.model.CreditCard;
import com.arjun.codeseven.repositories.BankAccountRepository;
import com.arjun.codeseven.repositories.BillingDetailsRepository;
import com.arjun.codeseven.repositories.CreditCardRepository;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJPATest {
	
	@Autowired
	private CreditCardRepository creditCardRepo;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	@Autowired
    private BillingDetailsRepository<BillingDetails, Long> billingDetailsRepository;
	
	@Test
	void storeLoadEntities() {
		
		
		CreditCard creditcard = new CreditCard("abc owner", "1234567890123456", "12", "32");
		billingDetailsRepository.save(creditcard);
		
		BankAccount ba = new BankAccount("abc owner1", "account one", "abc bank","abc swift");
		billingDetailsRepository.save(ba);
		
		// TABLE PER CONCRETE CLASS WITH INHERENT POLYMORPHMISM: NOT RECOMENDED 
		/*
		List<CreditCard> cards = creditCardRepo.findByOwner("abc owner");
		List<BankAccount> accounts = bankAccountRepo.findByOwner("abc owner");
		
		List<CreditCard> cards1 = creditCardRepo.findByExpYear("32");
		List<BankAccount> accounts2 = bankAccountRepo.findBySwift("abc swift");
		
		assertAll(
				() -> assertEquals(1, cards.size()),
				() -> assertEquals("abc owner", cards.get(0).getOwner()),
				() -> assertEquals(1, cards1.size()),
				() -> assertEquals("32", cards1.get(0).getExpYear()),
				() -> assertEquals("abc owner", accounts.get(0).getOwner()),
				() -> assertEquals(1, accounts.size()),
				() -> assertEquals("abc swift", accounts2.get(0).getSwift()),
				() -> assertEquals(1, accounts2.size()));
		*/
		
		//TABLE PER CONCRETE CLASS WITH UNIONS
		
		/*
		List<BillingDetails> det = billingDetailsRepository.findByOwner("abc owner1");
		
		assertAll(
				() -> assertEquals(2, billingDetailsRepository.findAll().size()),
				() -> assertEquals(1, billingDetailsRepository.findByOwner("abc owner").size()),
				() -> assertEquals("abc owner1", det.get(0).getOwner()));
		*/
		
		//TABLE PER HIERARCHY: SINGLE TABLE
		
		List<BillingDetails> det = billingDetailsRepository.findByOwner("abc owner1");
		assertAll(
				() -> assertEquals(2, billingDetailsRepository.findAll().size()),
				() -> assertEquals(1, det.size()),
				() -> assertEquals("abc owner1", det.get(0).getOwner()));
		
		
	}
	
	

}

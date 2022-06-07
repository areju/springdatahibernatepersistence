package com.arjun.codeseven;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.codeseven.configuration.SpringDataConfiguration;
import com.arjun.codeseven.model.BankAccount;
import com.arjun.codeseven.model.CreditCard;
import com.arjun.codeseven.repositories.BankAccountRepository;
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
	
	@Test
	void storeLoadEntities() {
		
		CreditCard creditcard = new CreditCard("abc owner", "1234567890123456", "12", "32");
		creditCardRepo.save(creditcard);
		
		BankAccount ba = new BankAccount("abc owner", "account one", "abc bank","abc swift");
		bankAccountRepo.save(ba);
		
		
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
		
	}
	
	

}

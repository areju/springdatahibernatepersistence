package com.arjun.codeseven.repositories;

import java.util.List;

import com.arjun.codeseven.model.CreditCard;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {
	
	List<CreditCard> findByExpYear(String expYear);

}

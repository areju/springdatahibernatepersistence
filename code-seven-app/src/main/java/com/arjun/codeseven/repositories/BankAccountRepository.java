package com.arjun.codeseven.repositories;

import java.util.List;

import com.arjun.codeseven.model.BankAccount;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {
	
	List<BankAccount> findBySwift(String swift);

}

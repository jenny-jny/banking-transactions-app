package com.revature.repository;

import java.util.List;

import com.revature.model.Account;

public interface AccountRepository {

	List<Account> getAllAccounts();
	
	void insertAcccount(Account a);
	//necessary?
	Account getAccountByAccountNumber(long accountNumber);
	void updateAccount(Account a);
	void deleteAccount(Account a);
	
}

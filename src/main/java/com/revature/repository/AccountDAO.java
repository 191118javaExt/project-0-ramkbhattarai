package com.revature.repository;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO {

	public abstract List<Account> getAllAccounts();
	
	public abstract List<Account> getAllAccountsOfUser(User u);
	
	public abstract Account getAccountById(int id);
	
	public abstract boolean addAccount(Account a);
	
	public abstract boolean updateAccount(Account a);
	
	public abstract boolean deleteAccount(Account a);
	
	public abstract Account getAccountIdBYPinNumber(int pinNumber);
	
}

package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repository.AccountDAO;
import com.revature.repository.AccountDAOIMPL;

public class AccountServices {
	
	AccountDAO accountDAO = new AccountDAOIMPL(); 
		
	
	
	public  List<Account> getAllAccounts(){
		return accountDAO.getAllAccounts();
	}
	
	public  List<Account> getAllAccountsOfUser(User u){
		return accountDAO.getAllAccountsOfUser(u);
	}
	
	public  Account getAccountById(int id) {
		return accountDAO.getAccountById(id);
	}
	
	public  boolean addAccount(Account a) {
		return accountDAO.addAccount(a);
	}
	
	public  boolean updateAccount(Account a) {
		return accountDAO.updateAccount(a);
	}
	
	public  boolean deleteAccount(Account a) {
		return accountDAO.deleteAccount(a);
	}

}

package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repository.AccountDAO;
import com.revature.repository.AccountDAOIMPL;

public class AccountServices {
	
	AccountDAO accountDAO = new AccountDAOIMPL(); 
	InputServices is = new InputServices();	
	
	
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
	
	public  Account getAccountBYAccountNumber(int pin) {
		return accountDAO.getAccountBYAccountNumber(pin);
	}
	
	public  boolean updateBalanceOfAccount(Account a, double amount) {
		return accountDAO.updateBalanceOfAccount(a, amount);
	}
	public Set<Integer> getAllPinNumbers(){
		return accountDAO.getAllPinNumbers();
	}
	
	//========================================================

	public void createAccountWithUser(User user) {
		String accounttype = is.getStringInput("Account Type");
		 int pin = is.ensureIntegerInput("Pin Number");
		 boolean isJoint = is.getBooleanInput("Creating Joint Account");
		Account a = createAccount(accounttype,pin ,isJoint , user.getId());
		boolean makeSureAccountIsSaved = addAccount(a);
		if(!makeSureAccountIsSaved) {
			System.out.println("You successfully created account. CONGRATULATIONS!!!");
		}else {
			System.out.println("Coun't create an account. Please try again.");
		}
		
	}
	
	
	public  Account createAccount(String accountType, int pin, boolean isJoint, int userId) {
		System.out.println();
		Account a = null;
		if(accountType.equalsIgnoreCase("checking")) {
			
			a = new Account(0, "checking", 0, 0, 0.02, pin, isJoint, 2, userId);
		}else if(accountType.equalsIgnoreCase("saving")) {
			a = new Account(0, "saving", 0, 0, 0.05, pin, isJoint, 2, userId);
		}else {
		System.out.println("You can only enter Saving or Checking");
		}
		return a;
	}

	
	
	
}

package com.revature;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserService;

public class Driver {

	public static void main(String[] args) {
		UserService us = new UserService();
		AccountServices as = new AccountServices();
		
//		User u = new User(0,"Achyut Prasad", "Bhattarai","Urmila",1,true, true,true);
//		Account a = new Account(0,"saving",1234,100000.0,10.0,true);
		
		//us.addUser(u);
		//as.addAccount(a);
		
		List<User> userList = us.getAllUsers();
		List<Account> accountList = as.getAllAccounts();
				
		for(User u1 : userList) {
			System.out.println(u1);
		}
		
		for(Account a1: accountList) {
			System.out.println(a1);
		}
	}

}

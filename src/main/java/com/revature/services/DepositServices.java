package com.revature.services;

import com.revature.models.Account;
import com.revature.models.User;

public class DepositServices {
	DisplayServices dis = new DisplayServices();
	InputServices is  = new InputServices();
	AccountServices as = new AccountServices();

	public boolean deposit(User u) {
		if(dis.displayAccounts(u)) {
			int id = is.ensureIntegerInput("Account ID");
			double amount = is.ensureDoubleInput("Deposit Amount");
			Account a = as.getAccountById(id);
			depositBalanceInAccount(a, amount);
			return true;
		}
		return false;
	}

	public boolean depositBalanceInAccount(Account a, double amount) {
		if(a.getStatus() == 1) {
			System.out.println("Your Account was canceled. You can't deposit. Contact Admin or Employee for more Information.");
			return false;
		}
		else if(a.getStatus() == 2) {
			System.out.println("Your Account is still in pending. You can't deposit. Contact Admin or Employee for more Information.");
			return false;
		}else {
		
			if(amount <= 0) {
				System.out.println("You can't deposit negative amount or Zero amount.");
				return false;
			}else {
				
				as.updateBalanceOfAccount(a, amount);
				Account a1 = as.getAccountById(a.getId());
				System.out.println("Congratulation you successfully deposited $" +amount + " and your current balance is $" + a1.getBalance() );
				return true;
			}
			
		}
		
	}

}

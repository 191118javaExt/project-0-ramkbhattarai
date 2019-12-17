package com.revature.services;

import com.revature.models.Account;
import com.revature.models.User;

public class WithdrawServices {
	AccountServices as = new AccountServices();
	DisplayServices dis = new DisplayServices();
	InputServices is = new InputServices();

	public boolean withdraw(User u) {
		if(dis.displayAccounts(u)) {
			int id = is.ensureIntegerInput("Account ID");
			double amount = is.ensureDoubleInput("Withdraw Amount");
			Account a = as.getAccountById(id);
			withdrawBalanceFromAccount(a, amount);
			return true;
		}
		return false;
	}
	
	public  boolean withdrawBalanceFromAccount(Account a, double amount) {
		if(a.getStatus() == 1) {
			System.out.println("Your Account was canceled. You can't withdraw. Contact Admin or Employee for more Information.");
			return false;
		}
		else if(a.getStatus() == 2) {
			System.out.println("Your Account is still in pending. You can't withdraw. Contact Admin or Employee for more Information.");
			return false;
		}else {
			
			if(amount <= 0) {
				System.out.println("You can't withdraw Negative or Zero amount");
				return false;
			}else {
				
				if(a.getBalance() < amount) {
					System.out.println("You can't withdraw the amount more than your balance.");
					return false;
				}else {
					as.updateBalanceOfAccount(a, (-1*amount));
					Account a1 = as.getAccountById(a.getId());
					System.out.println("Congratulation you successfully withdrew $" +amount + " and your current balance is $"+a1.getBalance() );
					return true;
				}
			}
			
		}
		
	}

}

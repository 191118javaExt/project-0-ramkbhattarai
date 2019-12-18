package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;


public class WithdrawServices {
	AccountServices as = new AccountServices();
	DisplayServices dis = new DisplayServices();
	InputServices is = new InputServices();
	private static Logger log = Logger.getLogger(WithdrawServices.class);

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
	
	public  double withdrawBalanceFromAccount(Account a, double amount) {
		double ans = 0.0;
		if(a.getStatus() == 1) {
			System.out.println("Your Account was canceled. You can't withdraw. Contact Admin or Employee for more Information.");
			
		}else if(a.getStatus() == 2) {
			System.out.println("Your Account is still in pending. You can't withdraw. Contact Admin or Employee for more Information.");
		}else {
			
			if(amount <= 0) {
				System.out.println("You can't withdraw Negative or Zero amount");
				
			}else {
				
				if(a.getBalance() < amount) {
					System.out.println("You can't withdraw the amount more than your balance.");
					
				}else {
					as.updateBalanceOfAccount(a, (-1*amount));
					Account a1 = as.getAccountById(a.getId());
					log.info("Congratulation you successfully withdrew $" +amount + " and your current balance is $"+a1.getBalance());
					System.out.println("Congratulation you successfully withdrew $" +amount + " and your current balance is $"+a1.getBalance() );
					ans = a1.getBalance();
				}
			}
			
		}
		return ans;
			
		}
	
		
	}



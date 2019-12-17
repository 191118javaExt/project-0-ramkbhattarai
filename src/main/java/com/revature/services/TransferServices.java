package com.revature.services;

import com.revature.models.Account;
import com.revature.models.User;

public class TransferServices {
	AccountServices as = new AccountServices();
	DisplayServices dis = new DisplayServices();
	InputServices is = new InputServices();

	public boolean transfer(User u) {
		if(dis.displayAccounts(u)) {
			int id = is.ensureIntegerInput("Account ID");
			double amount = is.ensureDoubleInput("Transfer Amount");
			Account a = as.getAccountById(id);
			System.out.println("You should know the Account Number of Account that you would like to transfer");
			int no = is.ensureIntegerInput("Account Number");
			Account a1 = as.getAccountBYAccountNumber(no);
			transferBalanceFromAccount(a, amount, a1);
			return true;
		}
		return false;
	}
	

	private  boolean transferBalanceFromAccount(Account a, double amount, Account a1) {

		if(a.getStatus() == 1) {
			System.out.println("Your Account was canceled. You can't transfer. Contact Admin or Employee for more Information.");
			return false;
		}
		else if(a.getStatus() == 2) {
			System.out.println("Your Account is still in pending. You can't transfer. Contact Admin or Employee for more Information.");
			return false;
		}else {
			if(amount <= 0) {
				System.out.println("You can't transfer negative or Zero amount to transfer");
				return false;
			}else {
				if(a.getBalance() < amount) {
					System.out.println("You can't transfer the amount more than your balance.");
					return false;
				}else if(a1.getStatus() < 3) {
					System.out.println("The account you want to transfer is not yet approved. So you can't transfer.");
					return false;
				}
				else {
					as.updateBalanceOfAccount(a, (-1*amount));
					as.updateBalanceOfAccount(a1, amount);
					Account a2 = as.getAccountById(a.getId());
					System.out.println("Congratulation you successfully transfered $" +amount + " to another account with account number " + a1.getAccountNumber());
					System.out.println("And your current balance is $"+a2.getBalance());
					return true;
				}
			}
		}
		
	}

}

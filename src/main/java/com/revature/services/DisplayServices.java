package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;

public class DisplayServices {
	AccountServices as = new AccountServices();
	Scanner scan = new Scanner(System.in);
	UserService us = new UserService();
	InputServices is = new InputServices();
	
	public void showUsersInfo(User u) {
		System.out.println("About you");
		System.out.println(u);
		System.out.println();
		displayAccounts(u);
		
	}

	public void modifyDetailsAboutUsers(User u) {
		System.out.println("You must be either employee of the bank or the admin of the bank to look into details about users.");
		System.out.println();
		System.out.println("Enter \"employee\" if you are employee or \"admin\" if you are admin.");
		String status = scan.nextLine();
		if(status.equalsIgnoreCase("admin") && u.isAdmin()) {
			List<User> userList = us.getAllUsers();
			System.out.println("Here is the list of all the Users");
			System.out.println("========================================================");
			for(User u4 : userList) {
				System.out.println(u4);
			}
			System.out.println("========================================================");
			System.out.println();
			System.out.println();
			System.out.println("Here's the list of all the Accounts");
			System.out.println("========================================================");
			List<Account> accountList = as.getAllAccounts();
			for(Account a : accountList) {
				System.out.println(a);
			}
			System.out.println("=========================================================");
			giveOptionsToModifyUsers(u);
			giveOptionsToModifyAccounts(u);
		}else if(status.equalsIgnoreCase("employee") && u.isEmployee()) {
			System.out.println("Here's the list of all the Accounts");
			System.out.println("========================================================");
			List<Account> accountList = as.getAllAccounts();
			for(Account a : accountList) {
				System.out.println(a);
			}
			System.out.println("=========================================================");
			giveOptionsToModifyAccounts(u);
		}else {
			System.out.println("You don't have right to see other's information");
		}
		
		
	}

	public boolean displayAccounts(User u) {
		List<Account> allAccountsOfUser = as.getAllAccountsOfUser(u);
		if(allAccountsOfUser.size()<=0) {
			System.out.println("You don't have any accounts please OPEN ONE.");
			return false;
		}else {
			System.out.println("Here are the list of your accounts");
			System.out.println("=============================================================================================");
			for(Account a : allAccountsOfUser) {
				System.out.println(a);
			}
			System.out.println("============================================================================================");
			System.out.println("Please select the Account Id of the account you like." );
			return true;
		}
	}

	public void seeDetailsAboutUsers(User user) {
		System.out.println("You must be either employee of the bank or the admin of the bank to look into details about users.");
		System.out.println();
		System.out.println("Enter \"employee\" if you are employee or \"admin\" if you are admin.");
		String status = scan.nextLine();
		if(status.equalsIgnoreCase("admin") || status.equalsIgnoreCase("employee")) {
			List<User> userList = us.getAllUsers();
			System.out.println("Here is the list of all the Users");
			System.out.println("========================================================");
			for(User u4 : userList) {
				System.out.println(u4);
			}
			System.out.println("========================================================");
			System.out.println();
			System.out.println();
			System.out.println("Here's the list of all the Accounts");
			System.out.println("========================================================");
			List<Account> accountList = as.getAllAccounts();
			for(Account a : accountList) {
				System.out.println(a);
			}
			System.out.println("=========================================================");
		}
		
	}
	
	public boolean giveOptionsToModifyAccounts(User u) {
		boolean result = false;;
		System.out.println("Select account id to modify the account information.");
		int choice = is.ensureIntegerInput("Account Id");
		Account a = as.getAccountById(choice);
		System.out.println(a);
		boolean flag = true;
		while(flag) {
			
			System.out.println("What do you want to change?");
			System.out.println();
			System.out.println("Enter 0 to go back");
			System.out.println("Enter 1 to change Account Type.");
			System.out.println("Enter 2 to change Account Number.");
			System.out.println("Enter 3 to change Account Balance.");
			System.out.println("Enter 4 to change Account Interest Rate.");
			System.out.println("Enter 5 to change Account Pin Number.");
			System.out.println("Enter 6 to change Account Joint Type.");
			System.out.println("Enter 7 to change Account Status.");
			
			int choice1 = is.ensureIntegerInput("Numbers");
			switch(choice1) {
			case 0:
				System.out.println("Thank you for checking out.");
				flag = false;
				result =  false;
				break;
			case 1:
				
				String accountType = is.getStringInput("Account Type");
				a.setAccountType(accountType);
				as.updateAccount(a);
				System.out.println("Account's Type  is changed.");
				result =  true;
				break;
			case 2:
			
				int accountNumber = is.ensureIntegerInput("Account Number");
				a.setAccountNumber(accountNumber);
				as.updateAccount(a);
				System.out.println("Account's Account Number is changed.");
				result =  true;
				break;
			case 3:
				
				double balance = is.ensureDoubleInput("Balance");
				a.setBalance(balance);
				as.updateAccount(a);
				System.out.println("Account's Balance is changed.");
				result =  true;
				break;
			case 4:
				
				double interest = is.ensureDoubleInput("Interest Rate");
				a.setInterestRate(interest);
				as.updateAccount(a);
				System.out.println("Account's Interest Rate is changed.");
				result =  true;
				break;
			case 5:
				
				int pinNumber = is.ensureIntegerInput("Pin Number");
				a.setPinNumber(pinNumber);
				as.updateAccount(a);
				System.out.println("Account's Pin Number is changed.");
				result =  true;
				break;
			case 6:
				
				boolean status = is.getBooleanInput("The account's Joint Statue T for \"True\" or F for \"False\"");
				String estatus = scan.nextLine();
				if(estatus.equalsIgnoreCase("T")) {
					status = true;
				}
				a.setJoint(status);
				as.updateAccount(a);
				System.out.println("Account's Joint Status is changed.");
				result =  true;
				break;
			case 7:
				int status1;
				do {
					System.out.println("Please Know  1 cancel's the account and 3 approves the account");
					status1 = is.ensureIntegerInput("Number");
					if(!(status1 == 1 || status1 == 3)) {
						System.out.println("Please Enter either 1 or 3 only. Please try again.");
					}
				}while(!(status1 == 1 || status1 == 3));
				
				
				a.setStatus(status1);
				as.updateAccount(a);
				System.out.println("Account's status is changed");
				result =  true;
				break;
			default:
				System.out.println("There no option for your input, Please try again.");
				result =  true;
				break;
			}
		}
		return result;
	}

	
	public  boolean giveOptionsToModifyUsers(User u) {
		boolean result = false;
		int choice = is.ensureIntegerInput("User Id");
		User user1 = us.getUserById(choice);
		System.out.println(user1);
		boolean flag = true;
		while(flag) {
			System.out.println("Enter 0 to go back");
			System.out.println("Enter 1 to change user First Name.");
			System.out.println("Enter 2 to change user Last Name.");
			System.out.println("Enter 3 to change user Password.");
			System.out.println("Enter 4 to change user Employee Status.");
			
			int choice1 = is.ensureIntegerInput("Numbers");
			switch(choice1) {
			case 0:
				System.out.println("Thank you for checking out.");
				flag = false;
				result = false;
				break;
			case 1:
				String fname = is.getStringInput("First Name");
				user1.setFname(fname);
				us.updateUser(user1);
				System.out.println("User's First Name is changed.");
				result = true;
				break;
			case 2:
				
				String lname = is.getStringInput("Last Name");
				user1.setLname(lname);
				us.updateUser(user1);
				System.out.println("User's Last Name is changed.");
				result = true;
				break;
			case 3:
				
				String pass = is.getStringInput("Password");
				user1.setPassword(pass);
				us.updateUser(user1);
				System.out.println("User's Password is changed.");
				result = true;
				break;
			case 4:
			
				boolean status = is.getBooleanInput("firing the Employee");
				boolean status1 = true;
				if(status) {
					status1 = false;
				}
				user1.setEmployee(status1);
				us.updateUser(user1);
				System.out.println("User's Employee Status is changed.");
				result = true;
				break;	
			default:
				System.out.println("There no option for your input, Please try again.");
				result = true;
				break;
			}
		}
		return result;
	}


}

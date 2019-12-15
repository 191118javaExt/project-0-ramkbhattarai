package com.revature;


import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserService;



public class Driver {
	private static UserService us = new UserService();
	private static AccountServices as = new AccountServices();
				
		private static Scanner scan = new Scanner(System.in);
		
		public static void start() {
	
			checkUser();
			
		}

		

		private static void checkUser() {
			boolean check = true;
			User u = null;
			while(check) {	 
				giveOptions(); // here we will give options to the user
				int input = ensureIntegerInput();
				switch(input) {
				case 1:
					u = createUser();
					us.addUser(u);
					break;
				case 2:
					User u1 = getUserFromDB();
					if(checkUserInDB(u1)) {
						
						logIn(u1);
					}
					check = false;
					break;
				case 3:
					System.out.println("Thanking you for using app");
					System.exit(0);
				default:
					System.out.println("No such option exists. Please try again");
					break;
					
				}
				
				
			}
			
		}

		private static  User getUserFromDB() {
			String fname;
			String password;
			User u;
			
			do {
				System.out.println("Enter your first name: ");
				 fname = scan.nextLine();
				//scan.nextLine();
				System.out.println("Enter your password: ");
				 password = scan.nextLine();
				System.out.println("Checking user in DataBase");
				u = us.getUserByFnameAndPassword(fname, password);
				
			}while(u == null);
			return u;
			
		}



		private static boolean checkUserInDB(User u) {
			
			boolean flag = false;
			
				if(u != null) {
					
					flag = true;
					
				}else {
					System.out.println("No such user exists. Please try again");
					flag = false;
				}
			return flag;
		}

		private static void logIn(User user) {
			System.out.println();
			System.out.println("Welcome " + user.getFname() + " Happy to see you around");
			System.out.println("Choose your option: ");
			while(true) {
				printTransactionOption();
				int choice = ensureIntegerInput();
				switch(choice) {
				case 0:
					System.out.println("Thank you for visiting.");
					System.exit(0);
					break;
				case 1:
					deposit(user);
					break;
				case 2:
					withdraw(user);
					break;
				case 3: 
					transfer(user);
					break;
				case 4:
					//showTransactions();
					break;
				case 5:
					seeDetailsAboutUsers(user);
				default:
					System.out.println("You entered something that is not in the options.");
					break;
				}
			}
		}

		private static void seeDetailsAboutUsers(User u) {
			System.out.println("You must be either employee of the bank or the admin of the bank to look into details about users.");
			System.out.println();
			System.out.println("Enter \"emplyee\" if you are employee or \"admin\" if you are admin.");
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



		private static void giveOptionsToModifyAccounts(User u) {
			System.out.println("Enter account id to modify the account information.");
			int choice = ensureIntegerInput();
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
				
				int choice1 = ensureIntegerInput();
				switch(choice1) {
				case 0:
					System.out.println("Thank you for checking out.");
					flag = false;
					break;
//				case 1:
//					System.out.println("Please Enter the id for Account.");
//					int id = ensureIntegerInput();
//					a.setId(id);
//					as.updateAccount(a);
//					System.out.println("Account's ID is changed.");
//					break;
				case 1:
					System.out.println("Please Enter the Account Type for account");
					String accountType = scan.nextLine();
					a.setAccountType(accountType);
					as.updateAccount(a);
					System.out.println("Account's Type  is changed.");
					break;
				case 2:
					System.out.println("Please Enter the Account number for account");
					int accountNumber = ensureIntegerInput();
					a.setAccountNumber(accountNumber);
					as.updateAccount(a);
					System.out.println("Account's Account Number is changed.");
					break;
				case 3:
					System.out.println("Please Enter the Balance for account.");
					double balance = ensureDoubleInput();
					a.setBalance(balance);
					as.updateAccount(a);
					System.out.println("Account's Balance is changed.");
					break;
				case 4:
					System.out.println("Please Enter the Interest Rate for account.");
					double interest = ensureDoubleInput();
					a.setInterestRate(interest);
					as.updateAccount(a);
					System.out.println("Account's Interest Rate is changed.");
					break;
				case 5:
					System.out.println("Please Enter the Pin Number for account");
					int pinNumber = ensureIntegerInput();
					a.setPinNumber(pinNumber);
					as.updateAccount(a);
					System.out.println("Account's Pin Number is changed.");
					break;
				case 6:
					System.out.println("Please Enter the account's Joint Statue T for \"True\" or F for \"False\"");
					boolean status = false;
					String estatus = scan.nextLine();
					if(estatus.equalsIgnoreCase("T")) {
						status = true;
					}
					a.setJoint(status);
					as.updateAccount(a);
					System.out.println("Account's Joint Status is changed.");
					break;
				default:
					System.out.println("There no option for your input, Please try again.");
					break;
				}
			}
			
		}



		private static void giveOptionsToModifyUsers(User u) {
			System.out.println("Enter user id to modify the user information.");
			int choice = ensureIntegerInput();
			User user1 = us.getUserById(choice);
			System.out.println(user1);
			boolean flag = true;
			while(flag) {
				
				System.out.println("What do you want to change?");
				System.out.println();
				System.out.println("Enter 0 to go back");
				System.out.println("Enter 1 to change user First Name.");
				System.out.println("Enter 2 to change user Last Name.");
				System.out.println("Enter 3 to change user Password.");
				System.out.println("Enter 4 to change user Employee Status.");
				
				int choice1 = ensureIntegerInput();
				switch(choice1) {
				case 0:
					System.out.println("Thank you for checking out.");
					flag = false;
					break;
//				case 1:
//					System.out.println("Please Enter the id for user.");
//					int id = ensureIntegerInput();
//					u.setId(id);
//					us.updateUser(u);
//					System.out.println("User's ID is changed.");
//					break;
				case 1:
					System.out.println("Please Enter the user First Name");
					String fname = scan.nextLine();
					user1.setFname(fname);
					us.updateUser(user1);
					System.out.println("User's First Name is changed.");
					break;
				case 2:
					System.out.println("Please Enter the user Last Name");
					String lname = scan.nextLine();
					user1.setLname(lname);
					us.updateUser(user1);
					System.out.println("User's Last Name is changed.");
					break;
				case 3:
					System.out.println("Please Enter the user Password");
					String pass = scan.nextLine();
					user1.setPassword(pass);
					us.updateUser(user1);
					System.out.println("User's Password is changed.");
					break;
//				case 5:
//					System.out.println("Please Enter the id for Account.");
//					int id1 = ensureIntegerInput();
//					u.setAccountId(id1);
//					us.updateUser(u);
//					System.out.println("User's Account Id is changed.");
//					break;
				case 4:
					System.out.println("Please Enter the user's Employee Statue T for \"True\" or F for \"False\"");
					boolean status = false;
					String estatus = scan.nextLine();
					if(estatus.equalsIgnoreCase("T")) {
						status = true;
					}
					user1.setEmployee(status);
					us.updateUser(user1);
					System.out.println("User's Employee Status is changed.");
					break;	
				default:
					System.out.println("There no option for your input, Please try again.");
					break;
				}
			}
			
		}

		

		private static int ensureIntegerInput() {
			int choice = 0;
			String ch = scan.nextLine();
			try {
				
				choice = Integer.parseInt(ch.split(" ")[0]);
			}catch(NumberFormatException e) {
				System.out.println("Error in changing input to Integer");
				e.printStackTrace();
				return -1;
			}
			return choice;
		}



		private static void deposit(User u) {
			System.out.println("How much do you want to deposit?");
			double amount = ensureDoubleInput();
			Account a = us.getUserAccount(u);
			as.updateBalanceOfAccount(a, amount);
			System.out.println("Congratulation you successfully deposited $" +amount );
		}



		private static void withdraw(User u) {
			System.out.println("How much do you want to withdraw?");
			double amount = ensureDoubleInput();
			Account a = us.getUserAccount(u);
			if(a.getBalance() < amount) {
				System.out.println("You can't withdraw the amount more than your balance.");
			}else {
				as.updateBalanceOfAccount(a, (-1*amount));
			}
			
			System.out.println("Congratulation you successfully withdrew $" +amount );
		}



		private static void transfer(User u) {
			System.out.println("How much do you want to transfer?");
			double amount = ensureDoubleInput();
			System.out.println("Enter the pin number of the account that you would like to transfer.");
			int pinNumber = ensureIntegerInput();
			Account anotherAccount = as.getAccountBYPinNumber(pinNumber);
			Account userAccount = us.getUserAccount(u);
			
			if(userAccount.getBalance() < amount) {
				System.out.println("You can't transfer the amount more than your balance.");
			}else {
				as.updateBalanceOfAccount(userAccount, (-1*amount));
				as.updateBalanceOfAccount(anotherAccount, amount);
			}
			System.out.println("Congratulation you successfully transfered $" +amount + " to another account with account number " + anotherAccount.getAccountNumber() );
		}



		private static double ensureDoubleInput() {
			String amt = scan.nextLine();
			double amount = 0.0;
			try {
				amount = Double.parseDouble(amt);
			}catch(NumberFormatException e) {
				System.out.println("Couldn't convert the transfer amount");
				e.printStackTrace();
			}
			return amount;
		}



		private static void printTransactionOption() {
			System.out.println();
			System.out.println("Enter 0 to exit");
			System.out.println("Enter 1 to deposit.");
			System.out.println("Enter 2 to widthdraw.");
			System.out.println("Enter 3 to transfer.");
			System.out.println("Enter 4 to seeYourTransactions.");
			System.out.println("Enter 5 to look into Users");
			System.out.println();
		}

		private static User createUser() {
			System.out.println("Enter your first name: ");

			String fname = scan.nextLine();
	
			System.out.println("Enter your last name: ");
			String lname = scan.nextLine();
			
			String password = conformPassword();
			System.out.println("Enter \"Yes\" if you are employee of the bank: ");
			String employee1 = scan.nextLine();
			
			boolean employee = false;
			if(employee1.equalsIgnoreCase("yes")) {
				employee = true;
			}
			System.out.println("Enter \"Yes\" if you are admin of the bank: ");
			String admin1 = scan.nextLine();
		
			boolean admin = false;
			if(admin1.equalsIgnoreCase("yes")) {
				admin = true;
			}
			System.out.println("Now Let's Create your account.");
			Account account = createAccount();
			int pin = account.getPinNumber();
			as.addAccount(account);
			Account acc = as.getAccountBYPinNumber(pin);
			return new User(0, fname, lname,  password,acc.getId(), employee, false, false);
		}


		 private static Account createAccount() {
			System.out.println();
			System.out.println("Which type of account you want to create?");
			System.out.println("Enter \"Saving\" for saving and \"Checking\" for checking");
			String accountType = scan.nextLine();
			
			System.out.println("Enter your Pin Number");
			
			int pin = ensureIntegerInput();
			
			
			System.out.println("Will it be a joint Account?");
			System.out.println("Enter \'Yes\' for yes and \'No\' for no ");
			String check = scan.nextLine();
			
			boolean isJoint = false;
			if(check.equalsIgnoreCase("Yes")) {
				isJoint = true;
			}
			return new Account(0, accountType,0,0,0,pin,isJoint);
		}



		private static String conformPassword() {
			String password;
			do {
				 password = checkPassword();
				
				if(us.getAllUsersPassword().contains(password)) {
					System.out.println("This password is already taken, Please try again");
				}
			}while(us.getAllUsersPassword().contains(password));
			return password;
		}

		private static String checkPassword() {
			String password1;
			String password2;
			do {
				System.out.println("Enter your password: ");
				 password1 = scan.nextLine();
				 //scan.nextLine();
				System.out.println("Re-Enter your password: ");
				 password2 = scan.nextLine();
				 //scan.nextLine();
				 
				 if(!password1.equals(password2)) {
					 System.out.println("Password doesn't match. Please Try Again");
				 }
			}while(!password1.equals(password2));
				
			return  password1;
		}

		private static void giveOptions() {
			System.out.println();
			System.out.println("Options: ");
			System.out.println("If you are New User, Please Enter 1");
			System.out.println("If you are Existing User, Please Enter 2");
			System.out.println("If you want to exit, Please Enter 3");
			System.out.println();
			
		}
		
		
		
		
		
		
		
	

}

package com.revature;


import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
					showUsersAccountInfo(user);
					break;
				case 5:
					modifyDetailsAboutUsers(user);
					break;
				case 6:
					seeDetailsAboutUsers(user);
					break;
				default:
					System.out.println("You entered something that is not in the options.");
					break;
				}
			}
		}

		private static void seeDetailsAboutUsers(User user) {
			
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



		private static void showUsersAccountInfo(User u) {
			Account a = us.getUserAccount(u);
			System.out.println(a);
		}



		private static void modifyDetailsAboutUsers(User u) {
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
				System.out.println("Enter 7 to change Account Status.");
				
				int choice1 = ensureIntegerInput();
				switch(choice1) {
				case 0:
					System.out.println("Thank you for checking out.");
					flag = false;
					break;
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
				case 7:
					int status1;
					do {
						System.out.println("Please Enter 1 to cancel the account");
						System.out.println("Please Enter 3 to approve the account");
						status1 = ensureIntegerInput();
						if(!(status1 == 1 || status1 == 3)) {
							System.out.println("Please Enter either 1 or 3 only. Please try again.");
						}
					}while(!(status1 == 1 || status1 == 3));
					
					
					a.setStatus(status1);
					as.updateAccount(a);
					System.out.println("Account's status is changed");
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
			Account a = us.getUserAccount(u);
			if(a.getStatus() == 1) {
				System.out.println("Your Account was canceled. You can't deposit. Contact Admin or Employee for more Information.");
			}
			else if(a.getStatus() == 2) {
				System.out.println("Your Account is still in pending. You can't deposit. Contact Admin or Employee for more Information.");
			}else {
				
				System.out.println("How much do you want to deposit?");
				double amount = ensureDoubleInput();
				if(amount <= 0) {
					System.out.println("You can't deposit negative amount or Zero amount.");
				}else {
					
					as.updateBalanceOfAccount(a, amount);
					Account a1 = us.getUserAccount(u);
					System.out.println("Congratulation you successfully deposited $" +amount + " and your current balance is $" + a1.getBalance() );
				}
				
			}
		}



		private static void withdraw(User u) {
			Account a = us.getUserAccount(u);
			if(a.getStatus() == 1) {
				System.out.println("Your Account was canceled. You can't withdraw. Contact Admin or Employee for more Information.");
			}
			else if(a.getStatus() == 2) {
				System.out.println("Your Account is still in pending. You can't withdraw. Contact Admin or Employee for more Information.");
			}else {
				
				System.out.println("How much do you want to withdraw?");
				double amount = ensureDoubleInput();
				
				if(a.getBalance() < amount) {
					System.out.println("You can't withdraw the amount more than your balance.");
				}else {
					as.updateBalanceOfAccount(a, (-1*amount));
				}
				Account a1 = us.getUserAccount(u);
				System.out.println("Congratulation you successfully withdrew $" +amount + " and your current balance is $"+a1.getBalance() );
			}
		}



		private static void transfer(User u) {
		
			Account a = us.getUserAccount(u);
			if(a.getStatus() == 1) {
				System.out.println("Your Account was canceled. You can't transfer. Contact Admin or Employee for more Information.");
			}
			else if(a.getStatus() == 2) {
				System.out.println("Your Account is still in pending. You can't transfer. Contact Admin or Employee for more Information.");
			}else {
				
				System.out.println("How much do you want to transfer?");
				double amount = ensureDoubleInput();
				if(amount <= 0) {
					System.out.println("You can't transfer negative or Zero amount to transfer");
				}else {
					
					System.out.println("Enter the pin number of the account that you would like to transfer.");
					int pinNumber = ensureIntegerInput();
					Account anotherAccount = as.getAccountBYPinNumber(pinNumber);
					
					
					if(a.getBalance() < amount) {
						System.out.println("You can't transfer the amount more than your balance.");
					}else if(anotherAccount.getStatus() < 3) {
						System.out.println("The account you want to transfer is not yet approved. So you can't transfer.");
					}
					else {
						as.updateBalanceOfAccount(a, (-1*amount));
						as.updateBalanceOfAccount(anotherAccount, amount);
					}
					Account a1 = us.getUserAccount(u);
					System.out.println("Congratulation you successfully transfered $" +amount + " to another account with account number " + anotherAccount.getAccountNumber());
					System.out.println("And your current balance is $"+a1.getBalance());
				}
			}
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
			System.out.println("Enter 4 to seeYourAccountInformation.");
			System.out.println("Enter 5 to Modify User Info and Their Account Info");
			System.out.println("Enter 6 to Look into User Info and Their Account Info");
			System.out.println();
		}

		private static User createUser() {
			System.out.println("Enter your first name: ");

			String fname = scan.nextLine();
	
			System.out.println("Enter your last name: ");
			String lname = scan.nextLine();
			
			String password = conformPassword();
			boolean employee = false;
			String employee1;
			do {
				
				System.out.println("Enter \"Yes\" if you are employee of the bank: ");
				 employee1 = scan.nextLine();
				if(employee1.equalsIgnoreCase("yes")) {
					employee = true;
				}
				if(!(employee1.equalsIgnoreCase("yes") || employee1.equalsIgnoreCase("no"))) {
					System.out.println("Only allowed values are yes and no. Please try again");
				}
			}while(!(employee1.equalsIgnoreCase("yes") || employee1.equalsIgnoreCase("no")));
			
			
			
			boolean admin = false;
			String admin1;
			do {
				
				System.out.println("Enter \"Yes\" if you are admin of the bank: ");
				 admin1 = scan.nextLine();
				if(admin1.equalsIgnoreCase("yes")) {
					admin = true;
				}
				if(!(admin1.equalsIgnoreCase("yes") || admin1.equalsIgnoreCase("no"))) {
					System.out.println("Only allowed values are yes and no. Please try again");
				}
			}while(!(admin1.equalsIgnoreCase("yes") || admin1.equalsIgnoreCase("no")));
			
			System.out.println("Now Let's Create your account.");
			Account account = createAccount();
			int pin = account.getPinNumber();
			as.addAccount(account);
			Account acc = as.getAccountBYPinNumber(pin);
			return new User(0, fname, lname,  password,acc.getId(), employee, admin, false);
		}


		 private static Account createAccount() {
			System.out.println();
			String accountType;
			do {
				
				System.out.println("Which type of account you want to create?");
				System.out.println("Enter \"Saving\" for saving and \"Checking\" for checking");
				 accountType = scan.nextLine();
				 if(!(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("saving"))) {
					 System.out.println("Only allowed values are checking and saving. Please try again.");
				 }
			}while(!(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("saving")));
			
			int pin;
			do {
				
				System.out.println("Enter your Pin Number");
				
				 pin = ensureIntegerInput();
				 if(!(new Integer(pin).toString().length() <=10))
					 System.out.println("Pin Number can only be of at most 10 digits. Please try again.");
				 if(checkPinNumberINDB(pin)) {
					 System.out.println("Already Taken!! Choose different Pin Number.");
				 }
			}while(!(new Integer(pin).toString().length() <=10) && checkPinNumberINDB(pin));
			
			String check;
			do {
				
				System.out.println("Will it be a joint Account?");
				System.out.println("Enter \'Yes\' for yes and \'No\' for no ");
				 check = scan.nextLine();
				 if(!(check.equalsIgnoreCase("yes") || check.equalsIgnoreCase("no"))) {
					 System.out.println("Only allowed values are yes and no. Please try again.");
				 }
			}while(!(check.equalsIgnoreCase("yes") || check.equalsIgnoreCase("no")));
			
			boolean isJoint = false;
			if(check.equalsIgnoreCase("Yes")) {
				isJoint = true;
			}
			if(accountType.equalsIgnoreCase("checking")) {
				
				return new Account(0, "checking",0,0,0.02,pin,isJoint,2);
			}else {
				return new Account(0,"saving",0,0,0.05,pin,isJoint,2);
			}
		}






		private static boolean checkPinNumberINDB(int pin) {
			Set<Integer> pinNumbers = as.getAllPinNumbers();
			if(pinNumbers.contains(new Integer(pin))) {
				return true;
			}
			return false;
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
			int i = 0;
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
				 if(password1.length() >= 20) {
					 System.out.println("Your Password is too long. Password must be less than 20 characters.Please try again.");
				 }
			}while(!password1.equals(password2) || password2.length() >=20);
				
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

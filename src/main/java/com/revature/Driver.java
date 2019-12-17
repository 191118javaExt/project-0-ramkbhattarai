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
			
			while(check) {	 
				giveOptions(); // here we will give options to the user
				int input = ensureIntegerInput("Numbers");
				switch(input) {
				case 1:
					User u = createUser(getStringInput("First Name"),getStringInput("Last Name"),"Pass", getBooleanInput("Employee"), getBooleanInput("Admin") );
					boolean makesureUserIsSaved = us.addUser(u);
					if(!makesureUserIsSaved) {
						System.out.println("Your information is successfully saved. CONGRATULATIONS!!! And WELCOME!!!");
					}else {
						System.out.println("Woop's something went wrong while saving user. Please try again.");
					}
					System.out.println("Lets to create an Account");
					boolean flag = getBooleanInput("Ready");
					Account a = null;
					User u1 = us.getUserByFnameAndPassword(u.getFname(), u.getPassword());
					if(flag) {
						 a = createAccount(getStringInput("Account Type"), ensureIntegerInput("Pin Number"), getBooleanInput("Creating Joint Account"), u1.getId());
					}else {
						System.out.println("You can always open later.");
					}
					boolean makeSureAccountIsSaved = as.addAccount(a);
					if(!makeSureAccountIsSaved) {
						System.out.println("You successfully created account. CONGRATULATIONS!!!");
					}else {
						System.out.println("Coun't create an account. Please try again.");
					}
					break;
				case 2:
					User u2 = getUserFromDB(getStringInput("First Name"), getStringInput("Password"));
					if(u2 == null) {
						System.out.println("First Name and Password doesn't match. Please try again");
					}else {
						
						if(checkUserInDB(u2)) {
							
							logIn(u2);
						}
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
		
		private static  User getUserFromDB(String fname, String password) {
			return us.getUserByFnameAndPassword(fname, password);	
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
				int choice = ensureIntegerInput("Numbers");
				switch(choice) {
				case 0:
					System.out.println("Thank you for visiting.");
					System.exit(0);
					break;
				case 1:
					createAccountWithUser(user);
					break;
				case 2:
					deposit(user);
					break;
				case 3:
					withdraw(user);
					break;
				case 4: 
					transfer(user);
					break;
				case 5:
					showUsersInfo(user);
					break;
				case 6:
					modifyDetailsAboutUsers(user);
					break;
				case 7:
					seeDetailsAboutUsers(user);
					break;
				default:
					System.out.println("You entered something that is not in the options.");
					break;
				}
			}
		}
		
		
		
		
		
		
		 private static void createAccountWithUser(User user) {
			Account a = createAccount(getStringInput("Account Type"), ensureIntegerInput("Pin Number"), getBooleanInput("Creating Joint Account"), user.getId());
			boolean makeSureAccountIsSaved = as.addAccount(a);
			if(!makeSureAccountIsSaved) {
				System.out.println("You successfully created account. CONGRATULATIONS!!!");
			}else {
				System.out.println("Coun't create an account. Please try again.");
			}
		}



		private static Account createAccount(String accountType, int pin, boolean isJoint, int userId) {
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
		
		private static boolean getBooleanInput(String string) {
			boolean flag = false;
			String input;
			do {
				
				System.out.println("Are you "+ string +" ?");
				input = scan.nextLine();
				if(input.equalsIgnoreCase("yes")) {
					flag = true;
				}
				if(!(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no"))) {
					System.out.println("Only allowed values are yes and no. Please try again");
				}
			}while(!(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no")));
			return flag;
		}

		private static String conformPassword(String pword) {
			String password = pword;
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
				System.out.println("Re-Enter your password: ");
				 password2 = scan.nextLine(); 
				 if(!password1.equals(password2)) {
					 System.out.println("Password doesn't match. Please Try Again");
				 }
				 if(password1.length() >= 20) {
					 System.out.println("Your Password is too long. Password must be less than 20 characters.Please try again.");
				 }
			}while(!password1.equals(password2) || password2.length() >=20);
				
			return  password1;
		}

		private static User createUser(String fname, String lname, String pword, boolean employee, boolean admin) {
			String password =  conformPassword(pword);
			return new User(0, fname, lname,  password, employee, admin);	
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



		private static void showUsersInfo(User u) {
			System.out.println("About you");
			System.out.println(u);
			System.out.println();
			displayAccounts(u);
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
			System.out.println("Select account id to modify the account information.");
			int choice = ensureIntegerInput("Account Id");
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
				
				int choice1 = ensureIntegerInput("Numbers");
				switch(choice1) {
				case 0:
					System.out.println("Thank you for checking out.");
					flag = false;
					break;
				case 1:
					
					String accountType = getStringInput("Account Type");
					a.setAccountType(accountType);
					as.updateAccount(a);
					System.out.println("Account's Type  is changed.");
					break;
				case 2:
				
					int accountNumber = ensureIntegerInput("Account Number");
					a.setAccountNumber(accountNumber);
					as.updateAccount(a);
					System.out.println("Account's Account Number is changed.");
					break;
				case 3:
					
					double balance = ensureDoubleInput("Balance");
					a.setBalance(balance);
					as.updateAccount(a);
					System.out.println("Account's Balance is changed.");
					break;
				case 4:
					
					double interest = ensureDoubleInput("Interest Rate");
					a.setInterestRate(interest);
					as.updateAccount(a);
					System.out.println("Account's Interest Rate is changed.");
					break;
				case 5:
					
					int pinNumber = ensureIntegerInput("Pin Number");
					a.setPinNumber(pinNumber);
					as.updateAccount(a);
					System.out.println("Account's Pin Number is changed.");
					break;
				case 6:
					
					boolean status = getBooleanInput("The account's Joint Statue T for \"True\" or F for \"False\"");
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
						System.out.println("Please Know  1 cancel's the account and 3 approves the account");
						status1 = ensureIntegerInput("Number");
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
			int choice = ensureIntegerInput("User Id");
			User user1 = us.getUserById(choice);
			System.out.println(user1);
			boolean flag = true;
			while(flag) {
				System.out.println("Enter 0 to go back");
				System.out.println("Enter 1 to change user First Name.");
				System.out.println("Enter 2 to change user Last Name.");
				System.out.println("Enter 3 to change user Password.");
				System.out.println("Enter 4 to change user Employee Status.");
				
				int choice1 = ensureIntegerInput("Numbers");
				switch(choice1) {
				case 0:
					System.out.println("Thank you for checking out.");
					flag = false;
					break;
				case 1:
					String fname = getStringInput("First Name");
					user1.setFname(fname);
					us.updateUser(user1);
					System.out.println("User's First Name is changed.");
					break;
				case 2:
					
					String lname = getStringInput("Last Name");
					user1.setLname(lname);
					us.updateUser(user1);
					System.out.println("User's Last Name is changed.");
					break;
				case 3:
					
					String pass = getStringInput("Password");
					user1.setPassword(pass);
					us.updateUser(user1);
					System.out.println("User's Password is changed.");
					break;
				case 4:
				
					boolean status = getBooleanInput("The Employee Statue T for \"True\" or F for \"False\"");
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

		

		private static int ensureIntegerInput(String input) {
			System.out.println("Enter "+ input);
			int choice = 0;
			String ch = scan.nextLine();
			try {
				
				choice = Integer.parseInt(ch);
			}catch(NumberFormatException e) {
				System.out.println("Error in changing input to Integer");
				e.printStackTrace();
				
			}
			return choice;
		}



		private static void deposit(User u) {
			
			if(displayAccounts(u)) {
				int id = ensureIntegerInput("Account ID");
				double amount = ensureDoubleInput("Deposit Amount");
				Account a = as.getAccountById(id);
				depositBalanceInAccount(a, amount);
			}
		
		}



		private static void depositBalanceInAccount(Account a, double amount) {
			
			if(a.getStatus() == 1) {
				System.out.println("Your Account was canceled. You can't deposit. Contact Admin or Employee for more Information.");
			}
			else if(a.getStatus() == 2) {
				System.out.println("Your Account is still in pending. You can't deposit. Contact Admin or Employee for more Information.");
			}else {
			
				if(amount <= 0) {
					System.out.println("You can't deposit negative amount or Zero amount.");
				}else {
					
					as.updateBalanceOfAccount(a, amount);
					Account a1 = as.getAccountById(a.getId());
					System.out.println("Congratulation you successfully deposited $" +amount + " and your current balance is $" + a1.getBalance() );
				}
				
			}
			
		}



		private static void withdraw(User u) {
			if(displayAccounts(u)) {
				int id = ensureIntegerInput("Account ID");
				double amount = ensureDoubleInput("Withdraw Amount");
				Account a = as.getAccountById(id);
				withdrawBalanceFromAccount(a, amount);
			}	
		}

		private static boolean displayAccounts(User u) {
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

		private static void withdrawBalanceFromAccount(Account a, double amount) {
			if(a.getStatus() == 1) {
				System.out.println("Your Account was canceled. You can't withdraw. Contact Admin or Employee for more Information.");
			}
			else if(a.getStatus() == 2) {
				System.out.println("Your Account is still in pending. You can't withdraw. Contact Admin or Employee for more Information.");
			}else {
				
				if(a.getBalance() < amount) {
					System.out.println("You can't withdraw the amount more than your balance.");
				}else {
					as.updateBalanceOfAccount(a, (-1*amount));
				}
				Account a1 = as.getAccountById(a.getId());
				System.out.println("Congratulation you successfully withdrew $" +amount + " and your current balance is $"+a1.getBalance() );
			}
			
		}



		private static void transfer(User u) {
			if(displayAccounts(u)) {
				int id = ensureIntegerInput("Account ID");
				double amount = ensureDoubleInput("Transfer Amount");
				Account a = as.getAccountById(id);
				System.out.println("You should know the Account Number of Account that you would like to transfer");
				int no = ensureIntegerInput("Account Number");
				Account a1 = as.getAccountBYAccountNumber(no);
				transferBalanceFromAccount(a, amount, a1);
			}
		
			
		}



		private static void transferBalanceFromAccount(Account a, double amount, Account a1) {

			if(a.getStatus() == 1) {
				System.out.println("Your Account was canceled. You can't transfer. Contact Admin or Employee for more Information.");
			}
			else if(a.getStatus() == 2) {
				System.out.println("Your Account is still in pending. You can't transfer. Contact Admin or Employee for more Information.");
			}else {
				if(amount <= 0) {
					System.out.println("You can't transfer negative or Zero amount to transfer");
				}else {
					if(a.getBalance() < amount) {
						System.out.println("You can't transfer the amount more than your balance.");
					}else if(a1.getStatus() < 3) {
						System.out.println("The account you want to transfer is not yet approved. So you can't transfer.");
					}
					else {
						as.updateBalanceOfAccount(a, (-1*amount));
						as.updateBalanceOfAccount(a1, amount);
					}
					Account a2 = as.getAccountById(a.getId());
					System.out.println("Congratulation you successfully transfered $" +amount + " to another account with account number " + a1.getAccountNumber());
					System.out.println("And your current balance is $"+a2.getBalance());
				}
			}
			
		}



		private static double ensureDoubleInput(String string) {
			System.out.println("Enter "+ string);
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
			System.out.println("Enter 1 to create New Account.");
			System.out.println("Enter 2 to deposit.");
			System.out.println("Enter 3 to widthdraw.");
			System.out.println("Enter 4 to transfer.");
			System.out.println("Enter 5 to seeYourAccountInformation.");
			System.out.println("Enter 6 to Modify User Info and Their Account Info");
			System.out.println("Enter 7 to Look into User Info and Their Account Info");
			System.out.println();
		}
		
		private static String getStringInput(String label) {
			System.out.println("Enter " + label);
			return scan.nextLine();
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

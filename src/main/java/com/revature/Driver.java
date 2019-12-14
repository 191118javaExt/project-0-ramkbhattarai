package com.revature;


import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserService;



public class Driver {
	private static UserService us = new UserService();
	private static AccountServices as = new AccountServices();
				//Account a = new Account(0,"check",11111,100453.0,10.0,true);
				//User u = new User(0,"Shiva", "Bhattarai","RamKB",2,true, true,true);
				//as.addAccount(a);
				//us.addUser(u);
		
		private static Scanner scan = new Scanner(System.in);
		//private static User user;
		//private Account account;
		
		public static void start() {
	
			checkUser();
			
		}

		

		private static void checkUser() {
			boolean check = true;
			User u = null;
			while(check) {	 
				giveOptions(); // here we will give options to the user
				int input = 0;
				
				try {
					String in = scan.nextLine(); // take the choise of user
					
					 input = Integer.parseInt(in.split(" ")[0]);
					
				}catch(NumberFormatException e) {
					e.printStackTrace();
				}
				
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
			// now we have to give them the options to withdraw, transfer, deposit the money
			// when they give the options then run their functionality in the run methods.
			System.out.println();
			System.out.println("Welcome " + user.getFname() + " Happy to see you around");
			System.out.println("Choose your option: ");
			printTransactionOption();
			int choice = 0;
			String ch = scan.nextLine();
			try {
				
				 choice = Integer.parseInt(ch.split(" ")[0]);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			
			switch(choice) {
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
				//seeDetailsAboutUsers();
			default:
				System.out.println("You entered something that is not in the options.");
				break;
			}
		}

		private static void deposit(User u) {
			System.out.println("How much do you want to deposit?");
			String amt = scan.nextLine();
			double amount = 0.0;
			try {
				amount = Double.parseDouble(amt);
			}catch(NumberFormatException e) {
				System.out.println("Couldn't convert the deposit amount");
				e.printStackTrace();
			}
			Account a = us.getUserAccount(u);
			as.updateBalanceOfAccount(a, amount);
			System.out.println("Congratulation you successfully deposited $" +amount );
		}



		private static void withdraw(User u) {
			System.out.println("How much do you want to withdraw?");
			String amt = scan.nextLine();
			double amount = 0.0;
			try {
				amount = Double.parseDouble(amt);
			}catch(NumberFormatException e) {
				System.out.println("Couldn't convert the withdraw amount");
				e.printStackTrace();
			}
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
			String amt = scan.nextLine();
			double amount = 0.0;
			try {
				amount = Double.parseDouble(amt);
			}catch(NumberFormatException e) {
				System.out.println("Couldn't convert the transfer amount");
				e.printStackTrace();
			}
			
			System.out.println("Enter the pin number of the account that you would like to transfer.");
			String pin = scan.nextLine();
			int pinNumber = 0;
			try {
				pinNumber = Integer.parseInt(pin.split(" ")[0]);
			}catch(NumberFormatException e) {
				System.out.println("Couldn't convert the pin number");
				e.printStackTrace();
			}
			
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



		private static void printTransactionOption() {
			System.out.println();
			System.out.println("Enter 1 to deposit.");
			System.out.println("Enter 2 to widthdraw.");
			System.out.println("Enter 3 to transfer.");
			System.out.println("Enter 4 to seeYourTransactions.");
			System.out.println("Enter 5 to look into Users");
			System.out.println();
		}

		private static User createUser() {
			System.out.println("Enter your first name: ");
//			scan.nextLine();
			String fname = scan.nextLine();
			//scan.nextLine();
			//System.out.println();
			System.out.println("Enter your last name: ");
			String lname = scan.nextLine();
			//scan.nextLine();
			String password = conformPassword();
			System.out.println("Enter \"Yes\" if you are employee of the bank: ");
			String employee1 = scan.nextLine();
			//scan.nextLine();
			boolean employee = false;
			if(employee1.equalsIgnoreCase("yes")) {
				employee = true;
			}
			System.out.println("Enter \"Yes\" if you are admin of the bank: ");
			String admin1 = scan.nextLine();
			//scan.nextLine();
			boolean admin = false;
			if(admin1.equalsIgnoreCase("yes")) {
				admin = true;
			}
			System.out.println("Now Let's Create your account.");
			Account account = createAccount();
			int pin = account.getPinNumber();
			as.addAccount(account);
			Account acc = as.getAccountBYPinNumber(pin);
			return new User(0, fname, lname,  password,acc.getId(), employee, admin, false);
		}


		 private static Account createAccount() {
			System.out.println();
			System.out.println("Which type of account you want to create?");
			System.out.println("Enter \"Saving\" for saving and \"Checking\" for checking");
			String accountType = scan.nextLine();
			//scan.nextLine();
			System.out.println("Enter your Pin Number");
			String p = scan.nextLine();
			int pin = 0;
			try {
				pin = Integer.parseInt(p.split(" ")[0]);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			//scan.nextLine();
			System.out.println("Will it be a joint Account?");
			System.out.println("Enter \'Yes\' for yes and \'No\' for no ");
			String check = scan.nextLine();
			//scan.nextLine();
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

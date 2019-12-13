package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserService;



public class Driver {
		UserService us = new UserService();
		AccountServices as = new AccountServices();
				//Account a = new Account(0,"check",11111,100453.0,10.0,true);
				//User u = new User(0,"Shiva", "Bhattarai","RamKB",2,true, true,true);
				//as.addAccount(a);
				//us.addUser(u);
		
		private static Scanner scan = new Scanner(System.in);
		private static User user;
		private Account account;
		
		public static void start() {
		
			//user = new User();
			checkUser();
			
		}

		

		private static void checkUser() {
			boolean check = true;
			
			while(check) {	 
				giveOptions(); // here we will give options to the user
				int input = scan.nextInt(); // take the choise of user
				switch(input) {
				case 1:
					user = createUser();
					//Bank.addUser(user);
					break;
				case 2:
					if(checkUserInDB()) {
						logIn(user);
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

		private static boolean checkUserInDB() {
			do {
				System.out.println();
				System.out.println("Enter your first name: ");
				String fname = scan.nextLine();
				scan.next();
				System.out.println("Enter your password: ");
				String password = scan.nextLine();
				scan.next();
		
				System.out.println("Checking user in DataBase");
				if(true/* DataBase.exists(fname)*/) {
					if(true/*DataBase.get(fname).getPassword().equals(password)*/) {
						return true;
					}else {
						System.out.println("User Name and Password doesn't match. Please try again.");
						//return false;
					}
					
				}else {
					System.out.println("No such user exists. Please try again");
					//return false;
				}
			}while(false/*(Database.exists(fname) && DataBase.get(fname).getPassword().equals(password))*/);
			return true;
		}

		private static void logIn(User user) {
			// now we have to give them the options to withdraw, transfer, deposit the money
			// when they give the options then run their functionality in the run methods.
			System.out.println();
			System.out.println("Welcome" + user + "Happy to see you around");
			System.out.println("Choose your option: ");
			printTransactionOption();
			int choice = scan.nextInt();
			//scan.next();
			switch(choice) {
			case 1:
				//deposit();
				break;
			case 2:
				//withdraw();
				break;
			case 3: 
				//transfer();
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

		private static void printTransactionOption() {
			System.out.println();
			System.out.println("Enter 1 to deposit.");
			System.out.println("Enter 2 to widthdraw.");
			System.out.println("Enter 3 to transfer.");
			System.out.println("Enter 4 to seeYourTransactions.");
			System.out.println("Enter 5 to look into details of Users");
			System.out.println();
		}

		private static User createUser() {
			System.out.println();
			System.out.println("Enter your first name: ");
			String fname = scan.nextLine();
			scan.next();
			System.out.println("Enter your last name: ");
			String lname = scan.nextLine();
			scan.next();
			String password = conformPassword();
			System.out.println("Enter \"Yes\" if you are employee of the bank: ");
			String employee1 = scan.nextLine();
			scan.next();
			boolean employee = false;
			if(employee1.equalsIgnoreCase("yes")) {
				employee = true;
			}
			System.out.println("Enter \"Yes\" if you are admin of the bank: ");
			String admin1 = scan.nextLine();
			scan.next();
			boolean admin = false;
			if(admin1.equalsIgnoreCase("yes")) {
				admin = true;
			}
			return new User(0, fname, lname,  password,1234, employee, admin, false);
		}

		private static String conformPassword() {
			String password;
			do {
				 password = checkPassword();
				
				if(true/*Database.exists(password)*/) {
					return password;
				}
			}while(false/*!Database.exists(password)*/);
			return "";
		}

		private static String checkPassword() {
			String password1;
			String password2;
			do {
				System.out.println("Enter your password: ");
				 password1 = scan.nextLine();
				 scan.next();
				System.out.println("Re-Enter your password: ");
				 password2 = scan.nextLine();
				 scan.next();
			}
			while(!password1.equals(password2));	
			return  password1;
		}

		private static void giveOptions() {
			System.out.println();
			System.out.println("Options: ");
			System.out.println("Enter 1 to create account");
			System.out.println("Enter 2 to log in if you already have account");
			System.out.println("Enter 3 to exit");
			System.out.println();
			
		}
		
		
		
		
		
		
		
	

}

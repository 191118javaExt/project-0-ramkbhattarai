package com.revature;





import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;

import com.revature.services.AccountServices;
import com.revature.services.DepositServices;
import com.revature.services.InputServices;
import com.revature.services.DisplayServices;
import com.revature.services.TransferServices;
import com.revature.services.UserService;
import com.revature.services.WithdrawServices;



public class Driver {
	private static Logger log = Logger.getLogger(Driver.class);
	private static UserService us = new UserService();
	private static AccountServices as = new AccountServices();
	private static DisplayServices dis = new DisplayServices();
	private static WithdrawServices ws = new WithdrawServices();
	private static DepositServices ds = new DepositServices();
	private static TransferServices ts = new TransferServices();
	private static InputServices is = new InputServices();		
		
		
		public static void start() {
	
			checkUser();
			
		}

		

		private static void checkUser() {
			boolean check = true;
			
			while(check) {	 
				giveOptions(); // here we will give options to the user
				int input = is.ensureIntegerInput("Numbers");
				switch(input) {
				case 1:
					String fname = is.getStringInput("First Name");
					String lname = is.getStringInput("Last Name");
					boolean isEmployee = is.getBooleanInput("Employee");
					boolean isAdmin = is.getBooleanInput("Admin");
					String password = us.conformPassword("");
					User u = us.createUser(fname,lname,password,isEmployee, isAdmin);
					boolean makesureUserIsSaved = us.addUser(u);
					if(!makesureUserIsSaved) {
						System.out.println("Your information is successfully saved. CONGRATULATIONS!!! And WELCOME!!!");
						log.info("User " + u.getFname() + " " + u.getLname()+ "saved in database");
					}else {
						System.out.println("Woop's something went wrong while saving user. Please try again.");
					}
					System.out.println("Lets to create an Account");
					boolean flag = is.getBooleanInput("Ready");
					Account a = null;
					User u1 = us.getUserByFnameAndPassword(u.getFname(), u.getPassword());
					if(flag) {
						String accountType = is.getStringInput("Account Type");
						int pin = is.ensureIntegerInput("Pin Number");
						boolean isJoint = is.getBooleanInput("Creating Joint Account");
						 a = as.createAccount(accountType,pin , isJoint, u1.getId());
					}else {
						System.out.println("You can always open later.");
					}
					boolean makeSureAccountIsSaved = as.addAccount(a);
					if(!makeSureAccountIsSaved) {
						log.info("Account with account number = "+ a.getAccountNumber()+ " was saved");
						System.out.println("You successfully created account. CONGRATULATIONS!!!");
					}else {
						System.out.println("Coun't create an account. Please try again.");
					}
					break;
				case 2:
					String firstName = is.getStringInput("First Name");
					String password1 = is.getStringInput("Password");
					User u2 = us.getUserFromDB(firstName,password1 );
					if(u2 == null) {
						System.out.println("First Name and Password doesn't match. Please try again");
					}else {
						
						if(us.checkUserInDB(u2)) {
							
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
		

		private static void logIn(User user) {
			System.out.println();
			System.out.println("Welcome " + user.getFname() + " Happy to see you around");
			System.out.println("Choose your option: ");
			while(true) {
				printTransactionOption();
				int choice = is.ensureIntegerInput("Numbers");
				switch(choice) {
				case 0:
					System.out.println("Thank you for visiting.");
					System.exit(0);
					break;
				case 1:
					as.createAccountWithUser(user);
					break;
				case 2:
					ds.deposit(user);
					break;
				case 3:
					ws.withdraw(user);
					break;
				case 4: 
					ts.transfer(user);
					break;
				case 5:
					dis.showUsersInfo(user);
					break;
				case 6:
					dis.modifyDetailsAboutUsers(user);
					break;
				case 7:
					dis.seeDetailsAboutUsers(user);
					break;
				default:
					System.out.println("You entered something that is not in the options.");
					break;
				}
			}
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
		



		private static void giveOptions() {
			System.out.println();
			System.out.println("Options: ");
			System.out.println("If you are New User, Please Enter 1");
			System.out.println("If you are Existing User, Please Enter 2");
			System.out.println("If you want to exit, Please Enter 3");
			System.out.println();
			
		}
		

}

package com.revature.services;

import java.util.Scanner;

public class InputServices {
	
	Scanner scan = new Scanner(System.in);
	public String getStringInput(String label) {
		
		String output = "";
		do {
			System.out.println("Enter " + label);
			 output =  scan.nextLine();
			if(output.length() > 20) {
				System.out.println("Your " + label + " can only be 20 characters long");
			}
		}
		while(output.length() > 20);
		
		return output;
	}

	public boolean getBooleanInput(String string) {
		
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

	public String checkPassword() {
		
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

	public int ensureIntegerInput(String input) {
		
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

	public double ensureDoubleInput(String string) {
		
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

}

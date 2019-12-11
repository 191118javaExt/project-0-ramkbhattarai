package com.revature.models;

public class Account {
	private int id;
	private String accountType;
	private int accountNumber;
	private double balance;
	private double interestRate;
	
	private boolean isJoint;
	
	private static int startingAccountNumber = 1000000;
	
	public Account() {
		super();
		accountNumber = startingAccountNumber++;
	}
	
	public Account(int id, double balance, String accountType, boolean isJoint) {
		super();
		this.id = id;
		this.balance = balance;
		this.accountType = accountType;
		this.isJoint = isJoint;
	}

}

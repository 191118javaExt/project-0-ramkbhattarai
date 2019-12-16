package com.revature.models;

public class Transactions {
	private int id;
	private String userName;
	private int accountNumber;
	private double oldBalance;
	private double newBalance;
	
	public Transactions(int id, String userName, int accountNumber, double oldBalance, double newBalance) {
		super();
		this.id = id;
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(newBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(oldBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(newBalance) != Double.doubleToLongBits(other.newBalance))
			return false;
		if (Double.doubleToLongBits(oldBalance) != Double.doubleToLongBits(other.oldBalance))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", userName=" + userName + ", accountNumber=" + accountNumber
				+ ", oldBalance=" + oldBalance + ", newBalance=" + newBalance + "]";
	}
	
	
}

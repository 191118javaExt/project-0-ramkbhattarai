package com.revature.models;

public class Transactions {
	private int id;
	private String type;
	private int accountid;
	private double oldBalance;
	private double newBalance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
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
		result = prime * result + accountid;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(newBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(oldBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (accountid != other.accountid)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(newBalance) != Double.doubleToLongBits(other.newBalance))
			return false;
		if (Double.doubleToLongBits(oldBalance) != Double.doubleToLongBits(other.oldBalance))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", type=" + type + ", accountid=" + accountid + ", oldBalance=" + oldBalance
				+ ", newBalance=" + newBalance + "]";
	}
	public Transactions(int id, String type, int accountid, double oldBalance, double newBalance) {
		super();
		this.id = id;
		this.type = type;
		this.accountid = accountid;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
	}
	
	
	
}

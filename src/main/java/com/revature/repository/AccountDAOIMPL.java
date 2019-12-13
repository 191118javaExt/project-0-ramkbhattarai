package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOIMPL implements AccountDAO {

	private static Logger log = Logger.getLogger(UserDAOIMPL.class);
	@Override
	public List<Account> getAllAccounts() {
		
		List<Account> accountList = new ArrayList<>();
		
				
				try (Connection con = ConnectionUtil.getConnection()) {
						
					String sql = "SELECT * FROM accounts;";
					
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						int account_id = rs.getInt("account_id");
						String accountType = rs.getString("account_type");
					
						int accountNumber = rs.getInt("account_number");
						double balance = rs.getDouble("balance");
						double interestRate = rs.getDouble("interest_rate");
						int pin = rs.getInt("pin_number");
						boolean isJoint = rs.getBoolean("is_joint");
					Account	account = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint);
						accountList.add(account);
					}
					
					rs.close();
				} catch(SQLException e) {
					log.warn("Unable to retrieve all accounts", e);
				}
		
				
				return accountList;
	}

	@Override
	public List<Account> getAllAccountsOfUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountById(int id) {
		Account account = null;
				
				try (Connection con = ConnectionUtil.getConnection()) {
						
					String sql = "SELECT account_id FROM accounts WHERE account_id ="+ id+";";
					
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						int account_id = rs.getInt("account_id");
						String accountType = rs.getString("account_type");
					
						int accountNumber = rs.getInt("account_number");
						double balance = rs.getDouble("balance");
						double interestRate = rs.getDouble("interest_rate");
						int pin = rs.getInt("pin_number");
						boolean isJoint = rs.getBoolean("is_joint");
						account = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint);
						
					}
					
					rs.close();
				} catch(SQLException e) {
					log.warn("Unable to retrieve the account", e);
				}
				return account;
	}

	@Override
	public boolean addAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "INSERT INTO accounts "
					+ "(account_type, account_number, balance, interest_rate, is_joint,pin_number) " +
					"VALUES (?, ?, ?, ?, ?,?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, a.getAccountType());
			stm.setInt(2, a.getAccountNumber());
			stm.setDouble(3, a.getBalance());
		
			stm.setDouble(4, a.getInterestRate());
			stm.setBoolean(5, a.isJoint());
			stm.setInt(6, a.getPinNumber());
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			log.warn("Unable to add  account", e);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateAccount(Account a) {
		
		int id = a.getId();
		String type = a.getAccountType();
		int number = a.getAccountNumber();
		double balance = a.getBalance();
		double interest = a.getInterestRate();
		int pin = a.getPinNumber();
		boolean isJoint = a.isJoint();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE accounts SET"
			+ "account_id ="+ id+","
			+ " account_type ="+ type+","
			+ " account_number ="+ number+","
			+ " balance ="+ balance+","
			+ " interest_rate ="+ interest+","
			+ "pin_number ="+ pin+","
			+ " is_joint ="+ isJoint+","
			+ "FROM accounts"
			+ "WHERE account_id ="+ id+";"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the user's account", ex);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAccount(Account a) {
		int id = a.getId();
		try (Connection conn = ConnectionUtil.getConnection()) {
						
					String sql = "DELETE FROM accounts"
					+ "WHERE account_id ="+ id+";"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					log.warn("Unable to delete the account", e);
					return false;
				}
				
				return true;
	}

	@Override
	public Account getAccountIdBYPinNumber(int pinNumber) {
		Account account = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM accounts WHERE pin_number ="+ pinNumber+";";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
			
				int accountNumber = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				double interestRate = rs.getDouble("interest_rate");
				int pin = rs.getInt("pin_number");
				boolean isJoint = rs.getBoolean("is_joint");
				account = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the account by Pin Number", e);
		}
		return account;
	}

}

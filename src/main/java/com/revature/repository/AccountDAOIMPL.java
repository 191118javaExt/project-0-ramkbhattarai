package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOIMPL implements AccountDAO {

	private static Logger log = Logger.getLogger(AccountDAOIMPL.class);
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
						int status = rs.getInt("pending_status");
						int userId = rs.getInt("user_id");
					Account	account = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint,status,userId);
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
		List<Account> accountList = new ArrayList<>();
		
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM accounts where user_id = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, u.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
			
				int accountNumber = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				double interestRate = rs.getDouble("interest_rate");
				int pin = rs.getInt("pin_number");
				boolean isJoint = rs.getBoolean("is_joint");
				int status = rs.getInt("pending_status");
				int userId = rs.getInt("user_id");
			Account	account = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint,status,userId);
				accountList.add(account);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all accounts of a user", e);
		}

		
		return accountList;
	}

	@Override
	public Account getAccountById(int id) {
		Account account = null;
				
				try (Connection con = ConnectionUtil.getConnection()) {
						
					String sql = "SELECT * FROM accounts WHERE account_id = ? ;";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						int account_id = rs.getInt("account_id");
						String accountType = rs.getString("account_type");
					
						int accountNumber = rs.getInt("account_number");
						double balance = rs.getDouble("balance");
						double interestRate = rs.getDouble("interest_rate");
						int pin = rs.getInt("pin_number");
						boolean isJoint = rs.getBoolean("is_joint");
						int status = rs.getInt("pending_status");
						int userid = rs.getInt("user_id");
						account = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint, status, userid);
						
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
					+ "(account_type, account_number, balance, interest_rate, is_joint,pin_number, pending_status,user_id) " +
					"VALUES (?, ?, ?, ?, ?,?,?,?);";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, a.getAccountType());
			stm.setInt(2, a.getAccountNumber());
			stm.setDouble(3, a.getBalance());
		
			stm.setDouble(4, a.getInterestRate());
			stm.setBoolean(5, a.isJoint());
			stm.setInt(6, a.getPinNumber());
			stm.setInt(7, a.getStatus());
			stm.setInt(8, a.getUser_id());
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
		int status = a.getStatus();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.accounts SET account_type = ?, account_number = ?, balance = ?, interest_rate = ?, is_joint = ?, pin_number = ?, pending_status = ? WHERE account_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setString(1, type);
			stm.setInt(2, number);
			stm.setDouble(3, balance);
			stm.setDouble(4, interest);
			stm.setBoolean(5, isJoint);
			stm.setInt(6, pin);
			stm.setInt(7, status);
			stm.setInt(8, id);
			
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
						
					String sql = "DELETE FROM public.accounts WHERE account_id = ?;"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setInt(1, id);
					
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
	public Account getAccountBYAccountNumber(int accountNumber) {
		Account account = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM public.accounts WHERE account_number = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountNumber);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
			
				int account_Number = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				double interestRate = rs.getDouble("interest_rate");
				int pin = rs.getInt("pin_number");
				boolean isJoint = rs.getBoolean("is_joint");
				int status = rs.getInt("pending_status");
				int userid = rs.getInt("user_id");
				account = new Account(account_id, accountType, account_Number, balance, interestRate,pin, isJoint, status, userid);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the account by Pin Number", e);
		}
		return account;
	}

	@Override
	public boolean updateBalanceOfAccount(Account a, double amount) {
		int id = a.getId();
		String type = a.getAccountType();
		int number = a.getAccountNumber();
		double balance = a.getBalance() + amount;
		double interest = a.getInterestRate();
		int pin = a.getPinNumber();
		boolean isJoint = a.isJoint();
		int status = a.getStatus();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.accounts SET account_type = ?, account_number = ?, balance = ?, interest_rate = ?, is_joint = ?, pin_number = ?, pending_status = ? WHERE account_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, type);
			stm.setInt(2, number);
			stm.setDouble(3, balance);
			stm.setDouble(4, interest);
			stm.setBoolean(5, isJoint);
			stm.setInt(6, pin);
			stm.setInt(7, status);
			stm.setInt(8, id);
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			log.warn("Unable to update the account balance", e);
			return false;
		}
		return true;
	}

	@Override
	public Set<Integer> getAllPinNumbers() {
		Set<Integer> pinNumbers = new TreeSet<>();
		List<Account> accountList = getAllAccounts();
		for(Account a : accountList) {
			int pin = a.getPinNumber();
			pinNumbers.add(pin);
		}
		return pinNumbers;
	}

}

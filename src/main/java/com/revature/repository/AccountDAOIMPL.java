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
						
						boolean isJoint = rs.getBoolean("is_joint");
					Account	account = new Account(account_id, accountType, accountNumber, balance, interestRate, isJoint);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(Account a) {
		
		int id = a.getId();
		String type = a.getAccountType();
		int number = a.getAccountNumber();
		double balance = a.getBalance();
		double interest = a.getInterestRate();
		boolean isJoint = a.isJoint();
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE accounts SET"
			+ "account_id = id,"
			+ " account_type = type,"
			+ " account_number = number,"
			+ " balance = account_balance,"
			+ " interest_rate = interest,"
			+ " is_joint = isJoint,"
			+ "FROM accounts"
			+ "WHERE account_id = id;"; 
					
			
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
		// TODO Auto-generated method stub
		return false;
	}

}

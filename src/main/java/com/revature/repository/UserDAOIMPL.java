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

public class UserDAOIMPL implements UserDAO{
	
	private static Logger log = Logger.getLogger(UserDAOIMPL.class);

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM users;";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String password = rs.getString("password");
				int account_id = rs.getInt("account_id");
				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");
				boolean isLoggedIn = rs.getBoolean("is_logged_in");
				User u = new User(id, fname, lname, password, account_id, isEmployee,isAdmin,isLoggedIn);
				
				userList.add(u);
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve all users", e);
		}

		
		return userList;
	}
	
	@Override
	public Set<String> getAllUsersPassword(){
		Set<String> passwords = new TreeSet<>();
		List<User> userList = getAllUsers();
		for(User u : userList) {
			String password = u.getPassword();
			passwords.add(password);
		}
		return passwords;
	}

	@Override
	public User getUserById(int id) {
			
		User user = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT user_id FROM users WHERE user_id =" + id + ";";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String password = rs.getString("password");
				int account_id = rs.getInt("account_id");
				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");
				boolean isLoggedIn = rs.getBoolean("is_logged_in");
				user = new User(user_id, fname, lname, password, account_id, isEmployee,isAdmin,isLoggedIn);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the user", e);
		}
		return user;
	}

	@Override
	public boolean addUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
					
					
					String sql = "INSERT INTO users (fname, lname, password, account_id, is_employee, is_admin, is_logged_in) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?);";
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(1, u.getFname());
					stm.setString(2, u.getLname());
					stm.setString(3, u.getPassword());
					
					stm.setInt(4, u.getAccountId());
					stm.setBoolean(5, u.isEmployee());
					stm.setBoolean(6, u.isAdmin());
					stm.setBoolean(7, u.isLoggedIn());
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					log.warn("Unable to add user", e);
					return false;
				}
				
				return true;
	}

	@Override
	public boolean updateUser(User u) {
		int id = u.getId();
		String f_name =  u.getFname();
		String l_name =  u.getLname();
		String password1 =  u.getPassword();
		
		int accountId =  u.getAccountId();
		boolean isEmployee =  u.isEmployee();
		boolean isAdmin =  u.isAdmin();
		boolean isLoggedIn =  u.isLoggedIn();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE users SET"
			+ "fname =" + f_name +","
			+ " lname ="+ l_name +","
			+ " password ="+ password1 +","
			+ " account_id ="+ accountId +","
			+ " is_employee ="+ isEmployee +","
			+ " is_admin ="+ isAdmin +","
			+ " is_looged_in ="+ isLoggedIn +","
			+ "FROM users"
			+ "WHERE user_id =" + id+";"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			log.warn("Unable to update the user", ex);
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean deleteUser(User u) {
		int id = u.getId();
		try (Connection conn = ConnectionUtil.getConnection()) {
						
					String sql = "DELETE FROM users"
					+ "WHERE user_id ="+ id+ ";"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException e) {
					log.warn("Unable to delete the user", e);
					return false;
				}
				
				return true;	
	}

	@Override
	public Account getUserAccount(User u) {
		Account account = null;
		int accountId = u.getAccountId();
		
		try (Connection con = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT account_id FROM accounts WHERE account_id =" +accountId+ ";";
			
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
			log.warn("Unable to retrieve user's account", e);
		}
		
		return account;
	}

	@Override
	public boolean updateUserAccount(User u) {
		Account a = getUserAccount(u);
		
		int id = a.getId();
		String type = a.getAccountType();
		int number = a.getAccountNumber();
		double balance = a.getBalance();
		double interest = a.getInterestRate();
		boolean isJoint = a.isJoint();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE accounts SET"
			+ "account_id =" +id+","
			+ " account_type ="+ type+","
			+ " account_number ="+ number+","
			+ " balance =" +balance+","
			+ " interest_rate ="+ interest+","
			+ " is_joint ="+ isJoint+","
			+ "FROM accounts"
			+ "WHERE account_id ="+ id+";"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			
			
			if(!stm.execute()) {
				return false;
			}
		} catch(SQLException e) {
			log.warn("Unable to update the user's account", e);
			return false;
		}
		return true;
		
	}

}

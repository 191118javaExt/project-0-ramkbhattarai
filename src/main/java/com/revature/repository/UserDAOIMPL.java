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
	public User getUserById(int id) {
			
		User user = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT user_id FROM users WHERE user_id = id;";
			
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
					
					
					String sql = "INSERT INTO users (fname, lname, password, account_id, is_employee, is_admin, is_looged_in) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?);";
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(1, u.getFname());
					stm.setString(2, u.getLname());
					stm.setString(3, u.getPassword());
					
					stm.setInt(4, u.getAccountId());
					stm.setBoolean(5, u.isEmployee());
					stm.setBoolean(6, u.isAdmin());
					stm.setBoolean(6, u.isLoggedIn());
					
					if(!stm.execute()) {
						return false;
					}
				} catch(SQLException ex) {
					log.warn("Unable to retrieve all users", ex);
					return false;
				}
				
				return true;
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getUserAccountById(int account_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}

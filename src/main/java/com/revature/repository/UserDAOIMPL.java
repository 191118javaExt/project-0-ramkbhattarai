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
				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");
				User u = new User(id, fname, lname, password, isEmployee,isAdmin);
				
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
				
			String sql = "SELECT * FROM users WHERE user_id = ? ;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String password = rs.getString("password");
				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");
				user = new User(user_id, fname, lname, password, isEmployee,isAdmin);
				
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
					
					
					String sql = "INSERT INTO users (fname, lname, password, is_employee, is_admin) " +
							"VALUES (?, ?, ?, ?, ?);";
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(1, u.getFname());
					stm.setString(2, u.getLname());
					stm.setString(3, u.getPassword());
					stm.setBoolean(4, u.isEmployee());
					stm.setBoolean(5, u.isAdmin());
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
		
		
		boolean isEmployee =  u.isEmployee();
		boolean isAdmin =  u.isAdmin();
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			
			String sql = "UPDATE public.users SET fname = ?, lname = ?, password = ?, is_employee = ?, is_admin = ? WHERE user_id = ?;"; 
					
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, f_name);
			stm.setString(2, l_name);
			stm.setString(3, password1);
			
			stm.setBoolean(4, isEmployee);
			stm.setBoolean(5, isAdmin);
			
			stm.setInt(6, id);
			
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
					+ "WHERE user_id = ?;"; 
					
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setInt(1, id);
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
	public User getUserByFnameAndPassword(String name,String pass) {
		User user = null;
		
		try (Connection con = ConnectionUtil.getConnection()) {
				
			String sql = "SELECT * FROM public.users WHERE fname = ? AND password = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, name);
			stmt.setString(2, pass);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String password = rs.getString("password");
				
				boolean isEmployee = rs.getBoolean("is_employee");
				boolean isAdmin = rs.getBoolean("is_admin");
				
				user = new User(user_id, fname, lname, password, isEmployee,isAdmin);
				
			}
			
			rs.close();
		} catch(SQLException e) {
			log.warn("Unable to retrieve the user by using first name and password", e);
		}
		
		return user;
	}

}

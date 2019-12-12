package com.revature.repository;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface UserDAO {
	

	public abstract List<User> getAllUsers();
	
	public abstract User getUserById(int id);
	
	public abstract boolean addUser(User u);
	
	public abstract boolean updateUser(User u);
	
	public abstract boolean deleteUser(User u);
	
	public abstract Account getUserAccountById(int account_id);
	
	public abstract boolean updateUserAccount( Account a);
	

}

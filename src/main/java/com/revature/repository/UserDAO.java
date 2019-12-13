package com.revature.repository;

import java.util.List;
import java.util.Set;

import com.revature.models.Account;
import com.revature.models.User;

public interface UserDAO {
	

	public abstract List<User> getAllUsers();
	
	public abstract User getUserById(int id);
	
	public abstract boolean addUser(User u);
	
	public abstract boolean updateUser(User u);
	
	public abstract boolean deleteUser(User u);
	
	public abstract Account getUserAccount(User u);
	
	public abstract boolean updateUserAccount(User u);
	
	public abstract Set<String> getAllUsersPassword();
	
	public abstract User getUserByFnameAndPassword(String fname, String password);
}

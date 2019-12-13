package com.revature.services;

import java.util.List;
import java.util.Set;


import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOIMPL;

public class UserService {
		UserDAO  userDAO = new UserDAOIMPL();
		
		public  List<User> getAllUsers(){
			return userDAO.getAllUsers();
		}
		
		public  User getUserById(int id) {
			return userDAO.getUserById(id);
		}
		
		public  boolean addUser(User u) {
			return userDAO.addUser(u);
		}
		
		public  boolean updateUser(User u) {
			return userDAO.addUser(u);
		}
		
		public  boolean deleteUser(User u) {
			return userDAO.deleteUser(u);
		}
		
		public  Account getUserAccount(User u) {
			return userDAO.getUserAccount(u);
		}
		
		public  boolean updateUserAccount(User u) {
			return userDAO.updateUserAccount(u);
		}
		
		public Set<String> getAllUsersPassword(){
			return userDAO.getAllUsersPassword();
		}
}

package com.revature.services;

import java.util.List;
import java.util.Set;



import com.revature.models.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOIMPL;

public class UserService {
		UserDAO  userDAO = new UserDAOIMPL();
		InputServices is = new InputServices();
		
		
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
			return userDAO.updateUser(u);
		}
		
		public  boolean deleteUser(User u) {
			return userDAO.deleteUser(u);
		}
		
		public Set<String> getAllUsersPassword(){
			return userDAO.getAllUsersPassword();
		}
		
		public User getUserByFnameAndPassword(String name,String pass) {
			return userDAO.getUserByFnameAndPassword(name, pass);
		}

		//====================================================================
		
		
		public User createUser(String fname, String lname, String password, boolean isEmployee, boolean isAdmin) {
			
		 return new User(0, fname, lname,  password, isEmployee, isAdmin);
		}

		
		
		public String conformPassword(String pword) {
			String password = pword;
			do {
				 password = is.checkPassword();
				
				if(getAllUsersPassword().contains(password)) {
					System.out.println("This password is already taken, Please try again");
				}
			}while(getAllUsersPassword().contains(password));
			return password;
		}
		
		

		public User getUserFromDB(String fname, String password) {
			
			return getUserByFnameAndPassword(fname, password);
		}

		
		
		public boolean checkUserInDB(User u) {
			boolean flag = false;
			
			if(u != null) {
				
				flag = true;
				
			}else {
				System.out.println("No such user exists. Please try again");
				flag = false;
			}
		return flag;
		}
		
		
}

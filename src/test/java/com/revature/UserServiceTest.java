package com.revature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTest {
	UserService us = new UserService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateUser() {
		User u = new User(0,"ram","bhattarai","ramkb",true, true);
		assertEquals(u, us.createUser("ram", "bhattarai", "ramkb", true, true));
	}
	
	@Test
	public void testGetUserFromDB() {
		User u = new User(1,"ram","bhattarai","ram",false, true);
		assertEquals(u, us.getUserFromDB("ram", "ram"));
	}
	
	@Test
	public void testGetUserFromDBForWrongInput() {
		User u = new User(1,"ram","bhattarai","ram",false, true);
		assertNotEquals(u, us.getUserFromDB("ram", "ramkb"));
	}
	


}

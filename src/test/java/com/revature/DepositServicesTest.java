package com.revature;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import com.revature.models.Account;

import com.revature.services.DepositServices;

public class DepositServicesTest {

	private static final DepositServices ds = new DepositServices();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDepositBalanceInAccountWithCorrectInfo() {
		Account a = new Account(5, "saving", 100004,590, 0.05,560, false, 3, 12);
		double ans = ds.depositBalanceInAccount(a, 100);
		assertEquals(new Double(690), new Double(ans));
	}
	
	@Test
	public void testDepositBalanceInAccountInWithCorrectInfo1() {
		Account a = new Account(5, "saving", 100004,690, 0.05,560, false, 3, 12);
		double ans = ds.depositBalanceInAccount(a, -1);
		assertNotEquals(new Double(-1), new Double(ans));
	}
	
	@Test
	public void testDepositBalanceInAccountInWithCorrectInfo2() {
		Account a = new Account(5, "saving", 100004,690, 0.05,560, false, 3, 12);
		double ans = ds.depositBalanceInAccount(a, 0);
		assertEquals(new Double(0), new Double(ans));
	}
	

}

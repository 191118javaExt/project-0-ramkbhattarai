package com.revature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.services.WithdrawServices;

public class WithdrawServicesTest {

	private static final WithdrawServices ws= new WithdrawServices();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWithdrawBalanceFromAccountWithCorrectInput() {
		Account a = new Account(7, "saving", 100006,18000, 0.05,354, false, 3, 13);
		Double ans = ws.withdrawBalanceFromAccount(a, 1000);
		assertEquals(new Double(17000), ans);
	}
	
	@Test
	public void testWithdrawBalanceFromAccountWithInCorrectInput() {
		Account a = new Account(7, "saving", 100006,17000, 0.05,354, false, 3, 13);
		Double ans = ws.withdrawBalanceFromAccount(a, 1000);
		assertNotEquals(new Double(17000), ans);
	}

}

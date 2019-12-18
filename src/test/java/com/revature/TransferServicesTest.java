package com.revature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.services.TransferServices;

public class TransferServicesTest {
	private static final TransferServices ts = new TransferServices();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTransferBalanceFromAccountWithCorrentInput() {
		Account a = new Account(3, "saving", 100002,2000, 0.05,234, false, 3, 11);
		Account a1 = new Account(4, "checking", 100003,18000, 0.02,567, false, 3, 12);
		Double ans = ts.transferBalanceFromAccount(a1, 1000, a);
		assertEquals(new Double(17000), ans);
	}
	
	@Test
	public void testTransferBalanceFromAccountWithInCorrentInput() {
		Account a = new Account(3, "saving", 100002,3000, 500.05,234, false, 3, 11);
		Account a1 = new Account(4, "checking", 100003,17000, 0.02,567, false, 3, 12);
		Double ans = ts.transferBalanceFromAccount(a1, 1000, a);
		assertNotEquals(new Double(17000), ans);
	}

}

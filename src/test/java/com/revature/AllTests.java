package com.revature;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DepositServicesTest.class, TransferServicesTest.class, UserServiceTest.class,
		WithdrawServicesTest.class })
public class AllTests {

}

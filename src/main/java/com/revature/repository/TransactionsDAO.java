package com.revature.repository;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transactions;


public interface TransactionsDAO {
	
	public abstract List<Transactions> getAllTransactions();
	
	public abstract List<Transactions> getAllTransactionOfAccount(Account a);
	
	public abstract Transactions getTransactionsById(int id);
	

}

package com.revature.repository;

import java.util.List;

import com.revature.models.Transactions;
import com.revature.models.User;

public interface TransactionsDAO {
	
	public abstract List<Transactions> getAllTransactions();
	
	public abstract List<Transactions> getAllTransactionOfUser(User u);
	
	public abstract Transactions getTransactionsById(int id);
	

}

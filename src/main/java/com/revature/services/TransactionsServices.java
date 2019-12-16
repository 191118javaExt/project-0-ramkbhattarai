package com.revature.services;

import java.util.List;

import com.revature.models.Transactions;
import com.revature.models.User;
import com.revature.repository.TransactionsDAO;
import com.revature.repository.TransactionsDAOIMPL;

public class TransactionsServices {
	TransactionsDAO  tdao = new TransactionsDAOIMPL();
	
	public  List<Transactions> getAllTransactions(){
		return tdao.getAllTransactions();
	}
	
	public  List<Transactions> getAllTransactionOfUser(User u){
		return tdao.getAllTransactionOfUser(u);
	}
	
	public  Transactions getTransactionsById(int id) {
		return tdao.getTransactionsById(id);
	}
}

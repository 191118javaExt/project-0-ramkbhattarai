package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.Transactions;
import com.revature.utils.ConnectionUtil;


public class TransactionsDAOIMPL implements TransactionsDAO{
	private static Logger log = Logger.getLogger(TransactionsDAOIMPL.class);
	@Override
	public List<Transactions> getAllTransactions() {
//		List<Transactions> tList = new ArrayList<>();
//		
//		
//		try (Connection con = ConnectionUtil.getConnection()) {
//				
//			String sql = "SELECT * FROM transactions;";
//			
//			Statement stmt = con.createStatement();
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				int id = rs.getInt("id");
//				String accountType = rs.getString("account_type");
//			
//				int accountNumber = rs.getInt("account_number");
//				double balance = rs.getDouble("balance");
//				double interestRate = rs.getDouble("interest_rate");
//				int pin = rs.getInt("pin_number");
//				boolean isJoint = rs.getBoolean("is_joint");
//				int status = rs.getInt("pending_status");
//				int userId = rs.getInt("user_id");
//				//Transactions	t = new Account(account_id, accountType, accountNumber, balance, interestRate,pin, isJoint,status,userId);
//				//tList.add(t);
//			}
//			
//			rs.close();
//		} catch(SQLException e) {
//			log.warn("Unable to retrieve all accounts", e);
//		}
//
//		
//		return tList;
		return null;
	}


	@Override
	public Transactions getTransactionsById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getAllTransactionOfAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

}

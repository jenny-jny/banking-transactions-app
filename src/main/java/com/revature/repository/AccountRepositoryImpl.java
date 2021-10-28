package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class AccountRepositoryImpl implements AccountRepository{

//	private static final Logger LOGGY = LogManager.getLogger(AccountRepositoryImpl.class);
//	private Connection conn;
	
	@Override
	public List<Account> getAllAccounts() {
		
		List<Account> accounts = new ArrayList<>();
		
		final String SQL = "select * from accounts";
		
		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			
			stmt = conn.createStatement();
			stmt.execute(SQL);
			rst = stmt.getResultSet();
			
			//records contained in result set will be added to accounts list
			while(rst.next()) { 
				
				accounts.add(
						
						new Account(
						
								rst.getLong(1),
								rst.getDouble(2),
								rst.getString(3)
						
						)
				);
				
			}
			
		}catch(SQLException e){ //cannot get a connection to the database
			
			e.printStackTrace();
			
		}finally {
			
			try {
				
				stmt.close();
				rst.close();
				ConnectionClosers.closeConnection(conn);
				
			}catch(SQLException e) {
				
				e.printStackTrace();
	
			}
		
		}
		
//		LOGGY.debug("View all account information and balances: " + accounts);
		return accounts;
		
	}

	@Override
	public void insertAcccount(Account a) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		final String SQL = "insert into accounts values(?, ?, ?)";
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setLong(1, a.getAccountNumber());
			pstmt.setDouble(2, a.getBalance());
			pstmt.setString(3, a.getAccountType());

			pstmt.execute();

		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally {
			
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(pstmt);
			
		}
		
	}

	@Override
	public Account getAccountByAccountNumber(long accountNumber) {
		
		Account a = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		final String SQL = "select * from accounts where account_number = '" + accountNumber + "'";
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			
			stmt = conn.createStatement();
			stmt.execute(SQL);
			rst = stmt.getResultSet();
			
			rst.next(); //moves the cursor once
			
			a = new Account(
					
					rst.getLong(1),
					rst.getDouble(2),
					rst.getString(3)
					
					);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			ConnectionClosers.closeResultSet(rst);
			ConnectionClosers.closeConnection(conn);
			
		}
		
		return a;

	}

	@Override
	public void updateAccount(Account a) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		final String SQL = "update accounts set balance = ? where account_number = ?";

		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setDouble(1, a.getBalance());
			pstmt.setLong(2, a.getAccountNumber());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(pstmt);
			
		}
	
	}

	@Override
	public void deleteAccount(Account a) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		final String SQL = "delete from accounts where account_number = ?";
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setLong(0, a.getAccountNumber());
			pstmt.setDouble(1, a.getBalance());
			pstmt.setString(2, a.getAccountType());
			
			pstmt.execute();

		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally {
			
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(pstmt);
			
		}
	}

}

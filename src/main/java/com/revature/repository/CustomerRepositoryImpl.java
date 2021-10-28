package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Customer;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class CustomerRepositoryImpl implements CustomerRepository{

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<>();
		
		final String SQL = "select * from customers";
		
		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			
			stmt = conn.createStatement();
			stmt.execute(SQL);
			rst = stmt.getResultSet();
			
			//records contained in result set will be added to customers list
			while(rst.next()) { 
				
				customers.add(
						
						new Customer(
								
								//
								rst.getString(1),
								//
								rst.getString(2),
								rst.getLong(3),
								rst.getString(4),
								rst.getString(5)
						
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
		
		return customers;
		
	}

	@Override
	public void insertCustomer(Customer c) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		final String SQL = "insert into customers values(?, ?, ?, ?, ?)";
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			pstmt = conn.prepareStatement(SQL);
			
			//
			pstmt.setString(1, String.valueOf(c));
			//
			pstmt.setString(2, String.valueOf(c));
			pstmt.setLong(3, c.getAccountNumber());
			pstmt.setString(4, c.getFirstName());
			pstmt.setString(5, c.getLastName());

			pstmt.execute();

		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally {
			
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeStatement(pstmt);
			
		}
				
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		
		Customer c = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		final String SQL = "select * from customers where username = '" + username + "'";
		
		try {
			
			conn = ConnectionFactory.getConnectionViaProperties();
			stmt = conn.createStatement();
			stmt.execute(SQL);
			rst = stmt.getResultSet();
			
			rst.next(); //moves the cursor once
			
			c = new Customer(
					
					rst.getString(1),
					rst.getString(2),
					rst.getLong(3),
					rst.getString(4),
					rst.getString(5)
					
					);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			ConnectionClosers.closeConnection(conn);
			ConnectionClosers.closeResultSet(rst);
			
		}
		
		return c;
	}


	@Override
	public void update(Customer c) {
		
	}

	@Override
	public void deleteCustomer(Customer c) {
		// TODO Auto-generated method stub
		
	}

}

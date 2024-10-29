package com.java.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.carconnect.model.Customer;
import com.java.carconnect.util.DBConnUtil;
import com.java.carconnect.util.DBPropertyUtil;


public class CustomerService implements ICustomerService {
	
	String connectionString;
	Connection connection;
	
	@Override
	public Customer getCustomerById(int customerId) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "select * from Customer where customerId = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		
		ps.setInt(1, customerId);
		
		ResultSet rs = ps.executeQuery(); 
		
		Customer customer = null;
		
		if(rs.next())
		{
			customer = new Customer();
			
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setEmail(rs.getString("email"));
			customer.setPhoneNumber(rs.getString("phoneNumber"));
			customer.setAddress(rs.getString("address"));
			customer.setUserName(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			customer.setRegistrationDate(rs.getDate("registrationDate"));
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "select * from Customer where username = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		Customer customer = null;
		
		if(rs.next())
		{
			customer = new Customer();
			
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setEmail(rs.getString("email"));
			customer.setPhoneNumber(rs.getString("phoneNumber"));
			customer.setAddress(rs.getString("address"));
			customer.setUserName(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			customer.setRegistrationDate(rs.getDate("registrationDate"));
		}
		
		return customer;
	}

	@Override
	public String registerCustomer(Customer customer) throws ClassNotFoundException, SQLException {

		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "insert into Customer(customerId, firstName, lastName, "
				+ "email, phoneNumber,address, userName, password, registrationDate) "
				+ " values(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, customer.getCustomerId());
		ps.setString(2, customer.getFirstName());
		ps.setString(3, customer.getLastName());
		ps.setString(4, customer.getEmail());
		ps.setString(5, customer.getPhoneNumber());
		ps.setString(6, customer.getAddress());
		ps.setString(7, customer.getUserName());
		ps.setString(8, customer.getPassword());
		ps.setDate(9, customer.getRegistrationDate());
		ps.executeUpdate();
		
		return "Customer Registered Successfully.";
	}

	@Override
	public String updateCustomer(Customer customerData) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
			String command = "update Customer set firstName=?,lastName=?,email=?,phoneNumber=?,"
					+ "address=?,username=?,password=?,registrationDate=?"
					+ " where customerId=?";
			
			PreparedStatement ps = connection.prepareStatement(command);
			
			ps.setString(1, customerData.getFirstName());
			ps.setString(2, customerData.getLastName());
			ps.setString(3, customerData.getEmail());
			ps.setString(4, customerData.getPhoneNumber());
			ps.setString(5, customerData.getAddress());
			ps.setString(6, customerData.getUserName());
			ps.setString(7, customerData.getPassword());
			ps.setDate(8, customerData.getRegistrationDate());
			ps.setInt(9, customerData.getCustomerId());
			
			ps.executeUpdate();
			
			return "Customer Data Updated Successfully.";
	}

	@Override
	public String deleteCustomer(int customerId) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "delete from Customer where customerId = ?";
		
		PreparedStatement ps = connection.prepareStatement(command);
		
		ps.setInt(1, customerId);
		int rowAffected = ps.executeUpdate();
//		System.out.println(rowAffected);
		if(rowAffected==1) {
			return "Customer record deleted successfully.";
		}
		return "Unable to delete Customer Record.";
	}

	@Override
	public Boolean validateCustomer(String username, String password) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT COUNT(*) as count FROM Customer WHERE username = ? AND password = ?";
		
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int rowAffected = rs.getInt("count");
		
		if(rowAffected==1) {
			return true;
		}
		
		return false;

	}


}

package com.java.carconnect.dao;

import java.sql.SQLException;

import com.java.carconnect.model.Customer;


public interface ICustomerService {

	Customer getCustomerById(int customerId) throws ClassNotFoundException, SQLException;
	Customer getCustomerByUsername(String username) throws ClassNotFoundException, SQLException;
	String registerCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	String updateCustomer(Customer customerData) throws ClassNotFoundException, SQLException;
	String deleteCustomer(int customerId) throws ClassNotFoundException, SQLException;
	Boolean validateCustomer(String user, String password) throws ClassNotFoundException, SQLException;
}

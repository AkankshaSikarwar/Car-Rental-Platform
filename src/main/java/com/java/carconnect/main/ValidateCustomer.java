package com.java.carconnect.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.CustomerService;
import com.java.carconnect.dao.ICustomerService;
import com.java.carconnect.dao.IReservationService;
import com.java.carconnect.dao.ReservationService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Customer;
import com.java.carconnect.util.ExceptionUtil;

public class ValidateCustomer {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void validateCustomer() {
		
		Scanner sc = new Scanner(System.in);
		ICustomerService customerService = new CustomerService();
		try {
			
			String username = "";
			boolean isInvalidUsername = true;
			boolean isInvalidLogin = true;
			
			do {
				do {
					System.out.print("Enter Username : ");
					username = sc.next();
					
					Customer customer = customerService.getCustomerByUsername(username);
					
					if(customer==null) {
						isInvalidUsername = true;
						System.out.println(RED+"No matching Username Found. Please enter "
								+ "a valid Username."+RESET);
					}
					else {
						isInvalidUsername = false;
						
						System.out.print("Enter Password : ");
						String password = sc.next();
						
						Boolean login = customerService.validateCustomer(username, password);
						
						if(login) {
							System.out.println(GREEN+"Login Successful."+RESET);
							isInvalidLogin = false;
						}
						else {
							System.out.println(RED+"Login Failed. Username and password"
									+ " does not match."+RESET);
							
							isInvalidLogin = true;
						}
					}
					
				} while(isInvalidUsername);
			} while(isInvalidLogin);
			
			
			
		} catch(SQLException e) {
			if(ExceptionUtil.isConnectionIssue(e)){
				System.out.println(RED+"Unable to connect to database."+e.getMessage()+RESET);
			}
			else{
				System.out.println(RED+"SQL error occured. "+e.getMessage()+RESET);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println(RED+"Class not Found. "+e.getMessage()+RESET);
		}
			
	}
		
}


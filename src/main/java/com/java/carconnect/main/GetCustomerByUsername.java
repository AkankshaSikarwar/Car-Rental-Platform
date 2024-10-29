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

public class GetCustomerByUsername {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getCustomerByUsername() {
		
		Scanner sc = new Scanner(System.in);
		ICustomerService customerService = new CustomerService();
		try {
			
			String username = "";
			boolean isInvalidUsername = true;
			
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
					customer.show();
				}
				
			} while(isInvalidUsername);
			
			
		} catch(SQLException e) {
			if(ExceptionUtil.isConnectionIssue(e)){
				System.out.println(RED+"Unable to connect to database."+RESET);
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


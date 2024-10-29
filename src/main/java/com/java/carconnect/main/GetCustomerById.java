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

public class GetCustomerById {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getCustomerById() {
		
		Scanner sc = new Scanner(System.in);
		ICustomerService customerService = new CustomerService();
		try {
			boolean isInvalidInput = true;
			
			int customerId = 0;
			boolean isInvalidCustomerId = true;
			
			do {
				do {
					try {
						isInvalidInput = false;
						System.out.print("Enter Customer Id : ");
						customerId = sc.nextInt();
						
					} catch(InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException("Invalid Input Entered. Please"
									+ " enter an Integer value (e.g. 40).");
						} catch (InvalidInputException iie) {
							System.out.println(RED+iie.getMessage()+RESET);
						}
					}
				} while(isInvalidInput);
				
				Customer customer = customerService.getCustomerById(customerId);
				
				if(customer==null) {
					isInvalidCustomerId = true;
					System.out.println(RED+"No matching Customer Id Found. Please enter "
							+ "a valid Customer Id."+RESET);
				}
				else {
					isInvalidCustomerId = false;
					customer = customerService.getCustomerById(customerId);
					customer.show();
				}
				
			} while(isInvalidCustomerId);
			
			
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


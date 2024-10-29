package com.java.carconnect.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.CustomerService;
import com.java.carconnect.dao.ICustomerService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Customer;
import com.java.carconnect.util.ExceptionUtil;

public class UpdateCustomer {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void updateCustomer() {
		
		Scanner sc = new Scanner(System.in);
		ICustomerService customerService = new CustomerService();
		try {
			boolean isInvalidInput = true;
			
			int customerId = 0;
			boolean isInvalidCustomerId = true;
			Customer customer = new Customer();
			
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
				
				Customer isInvalidCustomer = customerService.getCustomerById(customerId);
//				isInvalidCustomer.show();
				if(isInvalidCustomer==null) {
					isInvalidCustomerId = true;
					System.out.println(RED+"Customer Id not found. Please enter "
							+ "a valid Customer Id."+RESET);
				}
				else {
					isInvalidCustomerId = false;
					customer.setCustomerId(customerId);
					
					System.out.print("Enter First Name : ");
					customer.setFirstName(sc.next());
					
					System.out.print("Enter Last Name : ");
					customer.setLastName(sc.next());
					
					String email = "";
					boolean isInvalidEmail = true;
					do {
						System.out.print("Enter Email (must contain @) : ");
						email = sc.next();
						
						if(email.contains("@")) {
							isInvalidEmail = false;
							customer.setEmail(email);
						}
						else {
							isInvalidEmail = true;
							System.out.println(RED+"Invalid email entered (must contain @)."+RESET);
						}
						
					} while(isInvalidEmail);
					
					System.out.print("Enter Phone Number : ");
					customer.setPhoneNumber(sc.next());
					sc.nextLine();
					
					System.out.print("Enter Address : ");
					customer.setAddress(sc.nextLine());
					
					String username = "";
					boolean isInvalidUsername = true;
					do {
						System.out.print("Enter Username : ");
						username = sc.next();
						Customer isValidCustomer = customerService.getCustomerByUsername(username);
						
						if(isValidCustomer!=null && isValidCustomer.getCustomerId()!=customerId) {
							isInvalidUsername = true;
							System.out.println(RED+"Username already exists. Please"
									+ " enter a unique Username"+RESET);
						}
						else {
							isInvalidUsername = false;
							customer.setUserName(username);
						}
						
					} while(isInvalidUsername);
					
					
					System.out.print("Enter Password : ");
					customer.setPassword(sc.next());
                    
					// registration date will remain same
					customer.setRegistrationDate(isInvalidCustomer.getRegistrationDate());
				}
				
			} while(isInvalidCustomerId);
			
			String message = customerService.updateCustomer(customer);
			System.out.println(GREEN+message+RESET);
			
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


package com.java.carconnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.AdminService;
import com.java.carconnect.dao.IAdminService;
import com.java.carconnect.exception.DatabaseConnectionException;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Admin;
import com.java.carconnect.util.ExceptionUtil;

public class GetAdminById {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getAdminById() {
		int adminId = 0;
		Scanner sc=new Scanner (System.in);
		
		try {
				IAdminService adminService = new AdminService();
				
				boolean isInvalidInput = true;
				boolean isInvalidAdminId = true;
			do {
					
				do {
					System.out.print("Enter the Admin ID : ");
					
						try {
							isInvalidInput = false;
							adminId=sc.nextInt();
						}catch(InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							try {
								throw new InvalidInputException(RED+"Invalid Input Format. Please enter"
										+ " an Integer value (For e.g. 01)"+RESET);
							} catch(InvalidInputException iie) {
								System.out.println(iie.getMessage());
							}
						}
				}while(isInvalidInput);
				// -->> to check for invalid input -> String in place of integer
				
				Admin admin = adminService.getAdminById(adminId);
				
				if(admin !=null) {
					isInvalidAdminId = false;
					admin.show();;
				}
				else {
					System.out.println(RED+"No matching Admin Id Found. Please enter a valid Admin Id"+RESET);
					isInvalidAdminId = true;
				}
				
			}while(isInvalidAdminId);	
			
		} catch (ClassNotFoundException e) {
	        System.err.println(RED+"Database driver class not found. "+e.getMessage()+RESET);
	    } catch (SQLException e) {
	    	if(ExceptionUtil.isConnectionIssue(e))
	    	{
	    		DatabaseConnectionException dce = new DatabaseConnectionException("Unable"
	    				+ " to establish a connection with the database.");
	    		System.out.println(RED+dce.getMessage()+RESET);
	    	}
	    	else
	    	{
	    		System.out.println(RED+e.getMessage()+RESET);
	    	}
	    }
		
		
	}
}

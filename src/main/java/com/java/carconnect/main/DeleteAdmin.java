package com.java.carconnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.AdminService;
import com.java.carconnect.dao.IAdminService;
import com.java.carconnect.exception.AdminNotFoundException;
import com.java.carconnect.exception.DatabaseConnectionException;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Admin;
import com.java.carconnect.util.ExceptionUtil;

public class DeleteAdmin {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void deleteAdmin() {
		
		int adminId = 0;
		IAdminService adminService = new AdminService();
		Scanner sc = new Scanner (System.in);
		try {
			
			boolean isInvalidAdminId = true;
			do { 
				boolean isInvalidInput = true;	
				do {
						try {
							isInvalidInput = false;
							System.out.print("Enter the Admin ID you want to delete : ");
							adminId = sc.nextInt();
						} catch(InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							try {
								throw new InvalidInputException(RED+"Invalid Input Format entered. "
										+ "Please enter a valid Admin Id (in Integer e.g. 20)"+RESET);
							} catch(InvalidInputException iie) {
								System.out.println(RED+iie.getMessage()+RESET);
							}
						}
					} while(isInvalidInput);
					
					Admin admin = adminService.getAdminById(adminId);
					
					if(admin!=null)
					{
						isInvalidAdminId = false;
						String message = adminService.deleteAdmin(adminId);
						System.out.println(GREEN+message+RESET);
					}
					else {
						isInvalidAdminId = true;
						try {
							throw new AdminNotFoundException(RED+"Admin Id does not exists. "
									+ "Please enter a valid Admin Id."+RESET);
						} catch(AdminNotFoundException e) {
							System.out.println(RED+e.getMessage()+RESET);
						}
					}
			}while(isInvalidAdminId);
			adminService.deleteAdmin(adminId);
				
		} catch (ClassNotFoundException e) {
	        System.out.println(RED+"Database driver class not found. "+e.getMessage()+RESET);
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
	    } catch (AdminNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package com.java.carconnect.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.AdminService;
import com.java.carconnect.dao.IAdminService;
import com.java.carconnect.exception.DatabaseConnectionException;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Admin;
import com.java.carconnect.util.ExceptionUtil;

public class UpdateAdmin {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void updateAdmin() 
	{
		IAdminService adminService = new AdminService();
		try {
	
			Admin admin = new Admin(); 
			Scanner sc=new Scanner(System.in);
			boolean isInvalidAdminId = true;
			boolean isInvalidInput = true;
			int adminId=0;
			do {
				do {
					try {
						isInvalidInput = false;
						System.out.print("Enter Admin Id : ");
						adminId = sc.nextInt();
					} catch(InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException(RED+"Invalid input format entered."
									+ " Please enter valid Admin Id in Integer e.g. 40"+RESET);
						} catch(InvalidInputException iie) {
							System.out.println(iie.getMessage());
						}
					}
				}while(isInvalidInput);
				
				Admin isValidAdmin = adminService.getAdminById(adminId);
				
				if(isValidAdmin==null) {
					isInvalidAdminId = true;
					System.out.println(RED+"No matching Admin Id found. Please enter a"
							+ " valid Admin Id."+RESET);
				}
				else {
					isInvalidAdminId = false;
					admin.setJoinDate(isValidAdmin.getJoinDate());
				}
			}while(isInvalidAdminId);
			admin.setAdminId(adminId);
			
			System.out.print("Enter First Name : ");
			admin.setFirstName(sc.next());
			
			System.out.print("Enter Last Name : ");
			admin.setLastName(sc.next());
			
			String email="";
			isInvalidInput = true;
			do {
				System.out.print("Enter Email (must contain @) : ");
				email = sc.next();
				if(email.contains("@")) {
					isInvalidInput = false;
				}
				else {
					isInvalidInput = true;
					System.out.println(RED+"Invalid email Id (must contain @)."+RESET);
				}
			} while(isInvalidInput);
			admin.setEmail(email);
			
			System.out.print("Enter Phone Number : ");
			admin.setPhone(sc.next());
			
			isInvalidInput = true;
			String username = "";
			do {
				
				System.out.print("Enter Username : ");
				username = sc.next();
				Admin isValidAdmin = adminService.getAdminByUsername(username);
				
				if(isValidAdmin!=null && isValidAdmin.getAdminId()!=adminId) {
					isInvalidInput = true;
					System.out.println(RED+"Username already exists. Please enter a unique"
							+ " username."+RESET);
				}
				else {
					isInvalidInput = false;
				}
			}while(isInvalidInput);
			admin.setUsername(username);
			
			System.out.print("Enter Password : ");
			admin.setPassword(sc.next());
			
			String role = "";
			sc.nextLine();
			isInvalidInput = true;
			do {
				System.out.print("Enter Role (Super_admin / Fleet manager) : ");
				role = sc.nextLine();
				
				if(role.toLowerCase().equals("super_admin") || 
						role.toLowerCase().equals("fleet manager")) {
					isInvalidInput = false;
				}
				else {
					isInvalidInput = true;
					System.out.println(RED+"Invalid Role entered. Admin role must be "
							+ "Super_admin or fleet manager."+RESET);
				}
					
			} while(isInvalidInput);
			admin.setRole(role);
			
			
			String message = adminService.updateAdmin(admin);
			System.out.println(GREEN+message+RESET);
			
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
        }
		
	}
}

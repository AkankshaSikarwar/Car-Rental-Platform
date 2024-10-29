package com.java.carconnect.main;

import java.sql.SQLException;

import java.util.Scanner;

import com.java.carconnect.dao.AdminService;
import com.java.carconnect.dao.IAdminService;
import com.java.carconnect.exception.DatabaseConnectionException;
import com.java.carconnect.model.Admin;
import com.java.carconnect.util.ExceptionUtil;

public class GetAdminByUsername {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getAdminByUsername() {
		 String username;
		 Scanner sc=new Scanner (System.in);
		
		
		IAdminService  adminService = new AdminService();
		 try {
			 boolean isInvalidAdminId = true;
			 do {
				 	System.out.print("Enter the Username : ");
					username=sc.next();
					
					Admin admin = adminService.getAdminByUsername(username);
					
					if(admin !=null) {
						isInvalidAdminId = false;
						admin.show();
					}
					else {
						isInvalidAdminId = true;
						System.out.println(RED+"No matching username found. Please enter valid Username"+RESET);
					}
			 }while(isInvalidAdminId);
		} catch (ClassNotFoundException e) {
            System.err.println(RED+"Database driver class not found. "+e.getMessage()+RESET);
        } catch (SQLException e) {
        	if(ExceptionUtil.isConnectionIssue(e))
        	{
        		DatabaseConnectionException dce = new DatabaseConnectionException("Unable"
        				+ " to establish a connection with the database.");
        		System.err.println(RED+dce.getMessage()+RESET);
        	}
        	else
        	{
        		System.err.println(RED+e.getMessage()+RESET);
        	}
        }
		
	}
	}


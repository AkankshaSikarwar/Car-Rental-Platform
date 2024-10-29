package com.java.carconnect.main;

import java.sql.SQLException;
import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.IVehicleService;
import com.java.carconnect.dao.VehicleService;
import com.java.carconnect.exception.AdminNotFoundException;
import com.java.carconnect.exception.DatabaseConnectionException;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Vehicle;
import com.java.carconnect.util.ExceptionUtil;

public class AddVehicle {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void addVehicle() {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			boolean isInvalidInput = true;
			IVehicleService vehicleService = new VehicleService();
			
			boolean isInvalidVehicleId = true;
			int vehicleId = 0;
			Vehicle isValidVehicle = null;
			while(isInvalidVehicleId)
			{
				isInvalidInput = true;
				while(isInvalidInput)
				{
					try {
						isInvalidInput = false;
						System.out.print("Enter Vehicle Id : ");
						vehicleId = sc.nextInt();
					} catch (InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException(RED+"Invalid Input entered. Please enter an Integer"
									+ " value (e.g. 40)."+RESET);
						} catch (InvalidInputException iie) {
							System.out.println(RED+iie.getMessage()+RESET);
						}
					}
				}
				
				isValidVehicle = vehicleService.getVehicleById(vehicleId);
				
				if(isValidVehicle!=null) {
					isInvalidVehicleId = true;
					
					System.out.println(RED+"Vehicle Id already exists. Please enter a unique Vehicle"
							+ " Id."+RESET);
				}
				else
				{
					isInvalidVehicleId = false;
					sc.nextLine();
					
					Vehicle vehicle = new Vehicle();
					
					vehicle.setVehicleId(vehicleId);
					
					System.out.print("Enter Model : ");
					vehicle.setModel(sc.nextLine());
					
					System.out.print("Enter Make : ");
					vehicle.setMake(sc.nextLine());
					
					int year = 0;
					isInvalidInput = true;
					while(isInvalidInput)
					{
						try {
							isInvalidInput = false;
							System.out.print("Enter Year : ");
							year = sc.nextInt();
							
							if(year<=0 || year>Year.now().getValue())
							{
								isInvalidInput = true;
								throw new InvalidInputException("Year cannot be less than 0 or greater than "
										+ "current year.");
							}
						} catch (InvalidInputException e) {
							isInvalidInput = true;
							System.out.println(RED+e.getMessage()+RESET);
						} catch (InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							
							try {
								throw new InvalidInputException(RED+"Invalid Input entered. Please enter a valid"
										+ " year (e.g. 2002)."+RESET);
							} catch (InvalidInputException iie) {
								System.out.println(RED+iie.getMessage()+RESET);
							}
						}
					}
					sc.nextLine();
					vehicle.setYear(year);
					
					System.out.print("Enter Color : ");
					vehicle.setColor(sc.nextLine());
					
					String registrationNumber = "";
					boolean isInvalidRegistrationNumber = true;
					isValidVehicle = null;
					
					while(isInvalidRegistrationNumber)
					{
						System.out.print("Enter Registration Number : ");
						registrationNumber = sc.next();
						
						isValidVehicle = vehicleService.getVehicleByRegistrationNumber(registrationNumber);
						
						if(isValidVehicle!=null){
							isInvalidRegistrationNumber = true;
							System.out.println(RED+"Registration Number already exists. Please "
									+ "enter a unique Registration Number."+RESET);
						}
						else {
							isInvalidRegistrationNumber = false;
							vehicle.setRegistrationNumber(registrationNumber);
						}
					}
					
					boolean availability = false;
					isInvalidInput = true;
					while (isInvalidInput)
					{
						try {
							isInvalidInput = false;
							System.out.print("Enter Availability (true/false) : ");
							availability = sc.nextBoolean();
						} catch (InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							
							try {
								throw new InvalidInputException(RED+"Invalid Input. Please chose from "
										+ "true/false"+RESET);
							} catch (InvalidInputException iie) {
								System.out.println(RED+iie.getMessage()+RESET);
							}
						}
						
					}
					sc.nextLine();
					vehicle.setAvailability(availability);
					
					double dailyRate = 0;
					boolean isInvalidDailyRate = true;
					while(isInvalidDailyRate)
					{
						isInvalidInput = true;
						while(isInvalidInput)
						{
							try {
								isInvalidInput = false;
								System.out.print("Enter Daily Rate : ");
								dailyRate = sc.nextDouble();
							} catch (InputMismatchException e) {
								isInvalidInput = true;
								sc.nextLine();
								try {
									throw new InvalidInputException(RED+"Invalid Input entered."+RESET);
								} catch (InvalidInputException iie) {
									System.out.println(RED+iie.getMessage()+RESET);
								}
							}
						}
						
						if(dailyRate <= 0) {
							isInvalidDailyRate = true;
							System.out.println(RED+"Daily Rate cannot be Negative."+RESET);
						}
						else {
							isInvalidDailyRate = false;
							vehicle.setDailyRate(dailyRate);
						}
					}
					
					String message = vehicleService.addVehicle(vehicle);
					System.out.println(GREEN+message+RESET);
				}
			}
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

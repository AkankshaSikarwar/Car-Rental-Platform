package com.java.carconnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.IVehicleService;
import com.java.carconnect.dao.VehicleService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Vehicle;
import com.java.carconnect.util.ExceptionUtil;

public class GetVehicleById {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getVehicleById() {
		
		Scanner sc = new Scanner(System.in);
		IVehicleService vehicleService = new VehicleService();
		int vehicleId = 0;
		
		try {
			boolean isInvalidVehicle = true;
			do {
					boolean isInvalidInput = true;
					do {
						try {
							isInvalidInput = false;
							System.out.print("Enter Vehicle Id : ");
							vehicleId = sc.nextInt();
						} catch(InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							try {
								throw new InvalidInputException(RED+"Invalid input format "
										+ "entered. Please enter an Integer value."+RESET);
							} catch(InvalidInputException iie) {
								System.out.println(iie.getMessage());
							}
						}
				}while(isInvalidInput);
				
				Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
					
					if(vehicle!=null) {
						isInvalidVehicle = false;
						vehicle.show();
					}
					else {
						isInvalidVehicle = true;
						System.out.println(RED+"No Matching Vehicle Id found. Please"
								+ " enter a valid Vehicle Id."+RESET);
					}
				
			} while(isInvalidVehicle);
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

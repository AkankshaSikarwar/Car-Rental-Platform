package com.java.carconnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.java.carconnect.dao.IVehicleService;
import com.java.carconnect.dao.VehicleService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Vehicle;
import com.java.carconnect.util.ExceptionUtil;

public class GetAvailableVehicles {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getAvailableVehicles() {
		
		IVehicleService vehicleService = new VehicleService();
		
		try {
			List<Vehicle> vehicleList = vehicleService.getAvailableVehicles();
			
			if(vehicleList.isEmpty()) {
				System.out.println(RED+"No Vehicles Available right now."+RESET);
			}
			else {
				for (Vehicle vehicle : vehicleList) {
					vehicle.show();
				}
			}
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

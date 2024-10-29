package com.java.carconnect.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.carconnect.model.Status;
import com.java.carconnect.model.Vehicle;

public interface IVehicleService {

	Vehicle getVehicleById(int vehicleId) throws ClassNotFoundException, SQLException;
	Vehicle getVehicleByRegistrationNumber(String RegistrationNumber) throws ClassNotFoundException, SQLException;
	List<Vehicle> getAvailableVehicles() throws ClassNotFoundException, SQLException;;
	String addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;;
	String updateVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;;
	String updateVehicleAvailability(int vehicleId,Boolean availability) throws ClassNotFoundException, SQLException;
	String removeVehicle(int vehicleId) throws ClassNotFoundException, SQLException;;
	
}

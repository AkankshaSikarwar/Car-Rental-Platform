package com.java.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.carconnect.model.Status;
import com.java.carconnect.model.Vehicle;
import com.java.carconnect.util.DBConnUtil;
import com.java.carconnect.util.DBPropertyUtil;

public class VehicleService implements IVehicleService {

	String connectionString;
	Connection connection;
	PreparedStatement ps;
	
	@Override
	public Vehicle getVehicleById(int vehicleId) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM Vehicle WHERE vehicleId = ?";
		
		ps = connection.prepareStatement(command);
		ps.setInt(1, vehicleId);
		
		ResultSet rs = ps.executeQuery();
		Vehicle vehicle = null;
		
		if(rs.next())
		{
			vehicle = new Vehicle();
		
			vehicle.setVehicleId(rs.getInt("vehicleId"));
			vehicle.setModel(rs.getString("model"));
			vehicle.setMake(rs.getString("make"));
			vehicle.setYear(rs.getInt("year"));
			vehicle.setColor(rs.getString("color"));
			vehicle.setRegistrationNumber(rs.getString("registrationNumber"));
			vehicle.setAvailability(rs.getBoolean("availability"));
			vehicle.setDailyRate(rs.getDouble("dailyRate"));
		}	
		return vehicle;
	}

	@Override
	public Vehicle getVehicleByRegistrationNumber(String registrationNumber) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM Vehicle WHERE registrationNumber = ?";
		
		ps = connection.prepareStatement(command);
		ps.setString(1, registrationNumber);
		
		ResultSet rs = ps.executeQuery();
		Vehicle vehicle = null;
		
		if(rs.next())
		{
			vehicle = new Vehicle();
		
			vehicle.setVehicleId(rs.getInt("vehicleId"));
			vehicle.setModel(rs.getString("model"));
			vehicle.setMake(rs.getString("make"));
			vehicle.setYear(rs.getInt("year"));
			vehicle.setColor(rs.getString("color"));
			vehicle.setRegistrationNumber(rs.getString("registrationNumber"));
			vehicle.setAvailability(rs.getBoolean("availability"));
			vehicle.setDailyRate(rs.getDouble("dailyRate"));
		}	
		return vehicle;
	}
	
	@Override
	public List<Vehicle> getAvailableVehicles() throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM vehicle WHERE availability = true";
		
		ps = connection.prepareStatement(command);
		
		ResultSet rs = ps.executeQuery();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		Vehicle vehicle = null;
		
		while(rs.next())
		{
			vehicle = new Vehicle();
			
			vehicle.setVehicleId(rs.getInt("vehicleId"));
			vehicle.setModel(rs.getString("model"));
			vehicle.setMake(rs.getString("make"));
			vehicle.setYear(rs.getInt("year"));
			vehicle.setColor(rs.getString("color"));
			vehicle.setRegistrationNumber(rs.getString("registrationNumber"));
			vehicle.setAvailability(rs.getBoolean("availability"));
			vehicle.setDailyRate(rs.getDouble("dailyRate"));
			
			vehicleList.add(vehicle);
			
		}
		return vehicleList;
	}

	@Override
	public String addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "INSERT INTO vehicle (vehicleId,model,make,year,color,registrationNumber,"
				+ "availability,dailyRate) VALUES(?,?,?,?,?,?,?,?)";
		
		ps = connection.prepareStatement(command);
		
		ps.setInt(1, vehicle.getVehicleId());
		ps.setString(2, vehicle.getModel());
		ps.setString(3, vehicle.getMake());
		ps.setInt(4, vehicle.getYear());
		ps.setString(5, vehicle.getColor());
		ps.setString(6, vehicle.getRegistrationNumber());
		ps.setBoolean(7, vehicle.getAvailability());
		ps.setDouble(8, vehicle.getDailyRate());
		
		int rowAffected = ps.executeUpdate();
		
		if(rowAffected==1)
		{
			return "Vehicle Added Successfully.";
		}
		
		return "Failed to add Vehicle.";
	}

	@Override
	public String updateVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		
		String command = "UPDATE vehicle SET model=?,make=?,year=?,color=?"
				+ ",registrationNumber=?,availability=?,dailyRate=? WHERE vehicleId=?";
		
		ps = connection.prepareStatement(command);
		
		
		ps.setString(1, vehicle.getModel());
		ps.setString(2, vehicle.getMake());
		ps.setInt(3, vehicle.getYear());
		ps.setString(4, vehicle.getColor());
		ps.setString(5, vehicle.getRegistrationNumber());
		ps.setBoolean(6, vehicle.getAvailability());
		ps.setDouble(7, vehicle.getDailyRate());
		ps.setInt(8, vehicle.getVehicleId());
		
		int rowAffected = ps.executeUpdate();
		
		if(rowAffected==1)
		{
			return "Vehicle Updated Successfully.";
		}
		
		return "Failed to Update Vehicle.";
	}

	@Override
	public String removeVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "DELETE FROM vehicle WHERE vehicleId = ?";
		
		ps = connection.prepareStatement(command);
		ps.setInt(1, vehicleId);
		
		int rowAffected = ps.executeUpdate();
		
		if(rowAffected>0) {
			return "Vehicle Deleted Successfully.";
		}
		return "Failed to delete Vehicle.";
	}

	@Override
	public String updateVehicleAvailability(int vehicleId,Boolean availability) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "UPDATE vehicle SET availability = ? WHERE vehicleId = ?";
		ps = connection.prepareStatement(command);
		
		
		ps.setBoolean(1, availability);
		ps.setInt(2, vehicleId);
		
		int rowCount = ps.executeUpdate();
		
		if(rowCount>0) {
			return "Vehicle Availability Updated Succesfully.";
		}
		return "Unable to update Vehicle Availability";
	}

}

package com.java.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.java.carconnect.model.Reservation;
import com.java.carconnect.model.Status;
import com.java.carconnect.model.Vehicle;
import com.java.carconnect.util.DBConnUtil;
import com.java.carconnect.util.DBPropertyUtil;

public class ReservationService implements IReservationService {

	String connectionString;
	Connection connection;
	PreparedStatement ps;
	
	@Override 
	public Reservation getReservationById(int reservationId) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "select * from Reservation where reservationId = ?";
		 ps = connection.prepareStatement(command);
		ps.setInt(1, reservationId);
		ResultSet rs = ps.executeQuery();
		
		Reservation reservation = null;
		if(rs.next())
		{
			reservation = new Reservation();
			
			reservation.setReservationId(rs.getInt("reservationId"));
			reservation.setCustomerId(rs.getInt("customerId"));
			reservation.setVehicleId(rs.getInt("vehicleId"));
			reservation.setStartDate(rs.getTimestamp("startDate"));
			reservation.setEndDate(rs.getTimestamp("endDate"));
			reservation.setTotalCost(rs.getDouble("totalCost"));
			reservation.setStatus(Status.valueOf(rs.getString("status")));
		}
		
		return reservation;
	}
	
	public List<Reservation> getReservationByVehicleId(int vehicleId) throws ClassNotFoundException, SQLException{
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM Reservation WHERE vehicleId = ?";
		 ps = connection.prepareStatement(command);
		ps.setInt(1, vehicleId);
		ResultSet rs = ps.executeQuery();
		List<Reservation> reservationList = new ArrayList<Reservation>();
		
		Reservation reservation = null;
		while(rs.next())
		{
			reservation = new Reservation();
			
			reservation.setReservationId(rs.getInt("reservationId"));
			reservation.setCustomerId(rs.getInt("customerId"));
			reservation.setVehicleId(rs.getInt("vehicleId"));
			reservation.setStartDate(rs.getTimestamp("startDate"));
			reservation.setEndDate(rs.getTimestamp("endDate"));
			reservation.setTotalCost(rs.getDouble("totalCost"));
			reservation.setStatus(Status.valueOf(rs.getString("status")));
			
			reservationList.add(reservation);
		}
		
		return reservationList;
	}

	@Override
	public List<Reservation> getReservationsByCustomerId(int customerId) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM Reservation WHERE customerId = ?";
		ps = connection.prepareStatement(command);
		ps.setInt(1, customerId);
		ResultSet rs = ps.executeQuery();
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		Reservation reservation = null;
		while(rs.next())
		{
			reservation = new Reservation();
			
			reservation.setReservationId(rs.getInt("reservationId"));
			reservation.setCustomerId(rs.getInt("customerId"));
			reservation.setVehicleId(rs.getInt("vehicleId"));
			reservation.setStartDate(rs.getTimestamp("startDate"));
			reservation.setEndDate(rs.getTimestamp("endDate"));
			reservation.setTotalCost(rs.getDouble("totalCost"));
			reservation.setStatus(Status.valueOf(rs.getString("status")));
			
			reservationList.add(reservation);
		}
		
		return reservationList;
	}

	@Override
	public String createReservation(Reservation reservation) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "INSERT INTO reservation (reservationId,customerId,vehicleId,"
				+ "startDate,endDate,totalCost,status) VALUES(?,?,?,?,?,?,?)";
		
		ps = connection.prepareStatement(command);
		
		ps.setInt(1, reservation.getReservationId());
		ps.setInt(2, reservation.getCustomerId());
		ps.setInt(3, reservation.getVehicleId());
		ps.setTimestamp(4, reservation.getStartDate());
		ps.setTimestamp(5, reservation.getEndDate());
		ps.setDouble(6, reservation.getTotalCost());
		ps.setString(7, String.valueOf(reservation.getStatus()));
		
		int rowsAffected = ps.executeUpdate();
		
		if(rowsAffected==1) {
			return "Reservation done successfully";
		}
		
		return "Reservation cannot be completed.";
	}
	

	@Override
	public String updateReservation(Reservation reservation) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "UPDATE reservation SET customerId=?,vehicleId=?,"
				+ "startDate=?,endDate=?,totalCost=?,status=? WHERE reservationId=?";
		
		ps = connection.prepareStatement(command);
		
		ps.setInt(1, reservation.getCustomerId());
		ps.setInt(2, reservation.getVehicleId());
		ps.setTimestamp(3, reservation.getStartDate());
		ps.setTimestamp(4, reservation.getEndDate());
		ps.setDouble(5, reservation.getTotalCost());
		ps.setString(6, String.valueOf(reservation.getStatus()));
		ps.setInt(7, reservation.getReservationId());
		
		int rowsAffected = ps.executeUpdate();
		
		if(rowsAffected==1) {
			return "Reservation record Updated successfully.";
		}
		return "Failed to update Reservation record.";
	}
//	@Override
//	public String updateReservation(Reservation reservation) throws ClassNotFoundException, SQLException {
//		String connStr = DBPropertyUtil.getConnectionString("db");
//        connection = DBConnUtil.getConnection(connStr);
//        String command = "Update Reservation set CustomerId = ?, vehicleId = ?, startDate = ?, endDate = ?, "
//        		+ "totalCost = ?, status = ? where reservationId = ? ";
//        ps = connection.prepareStatement(command);
//        
//        ps.setInt(1, reservation.getCustomerId());
//        ps.setInt(2, reservation.getVehicleId());
//        ps.setTimestamp(3, reservation.getStartDate());
//        ps.setTimestamp(4, reservation.getEndDate());
//        ps.setDouble(5, reservation.getTotalCost());
//        ps.setString(6, reservation.getStatus().name());
//        ps.setInt(7, reservation.getReservationId());
//        int rowsAffected = ps.executeUpdate();
//        if (rowsAffected > 0) {
//            return "Reservation record updated successfully.";
//        } else {
//            return "Failed to update Reservation record.";
//        }
//
//	}
	@Override
	public String updateReservationStatus(int reservationId, Status status) throws ClassNotFoundException, SQLException {
	connectionString = DBPropertyUtil.getConnectionString("db");
	connection = DBConnUtil.getConnection(connectionString);
	
	String command = "UPDATE reservation SET status = ? WHERE reservationId = ?";
	
	ps = connection.prepareStatement(command);
	
	ps.setString(1, String.valueOf(status));
	ps.setInt(2, reservationId);
	
	int rowAffected = ps.executeUpdate();
//	System.out.println(rowAffected);
	
	if(rowAffected==1) {
		return "Reservation Status Updated successfully.";
	}
	
	return "Unable to Update Reservation Status";
	}
	
	@Override
	public String cancelReservation(int reservationId) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "DELETE FROM reservation WHERE reservationId = ?";
		
		ps = connection.prepareStatement(command);
		ps.setInt(1, reservationId);
		
		int rowAffected = ps.executeUpdate();
		
		if(rowAffected>0) {
			return "Reservation cancelled successfully";
		}
		
		return "Unable to cancel Reservation";
	}


	@Override
	public String cancelReservationByCustomerId(int customerId) throws ClassNotFoundException, SQLException {
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "DELETE FROM reservation WHERE customerId = ?";
		
		ps = connection.prepareStatement(command);
		ps.setInt(1, customerId);
		
		int rowAffected = ps.executeUpdate();
		System.out.println(rowAffected);
		
		if(rowAffected>0) {
			return "Reservation cancelled successfully";
		}
		
		return "Unable to cancel Reservation";
	}

	@Override
	public double getVehicleCost(int vehicleId) throws SQLException,ClassNotFoundException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command="SELECT dailyRate FROM vehicle WHERE vehicleId = ?";
		ps = connection.prepareStatement(command);
		
		ps.setInt(1,vehicleId);
		double cost = 0;
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			cost=rs.getDouble("dailyRate");
		}
		return cost;
	}
	
	
	@Override
	public int getDateDiff(Timestamp startDate, Timestamp endDate) throws SQLException,ClassNotFoundException
	{
		String connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT dateDiff(?,?) AS date";
		ps = connection.prepareStatement(command);
		ps.setTimestamp(1,endDate);
		ps.setTimestamp(2, startDate);
		
		ResultSet rs = ps.executeQuery();
		int days=0;
		while(rs.next())
		{
			days = rs.getInt("date");
		}
		return days;
	}
	

	

}

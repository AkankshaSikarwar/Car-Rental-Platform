package com.java.carconnect.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.java.carconnect.model.Reservation;
import com.java.carconnect.model.Status;

public interface IReservationService {
	Reservation getReservationById(int reservationId) throws ClassNotFoundException, SQLException;
	public List<Reservation> getReservationByVehicleId(int vehicleId) throws ClassNotFoundException, SQLException;
	List<Reservation>  getReservationsByCustomerId(int customerId) throws ClassNotFoundException, SQLException;
	String createReservation(Reservation reservation) throws ClassNotFoundException, SQLException;
	String updateReservation(Reservation reservation) throws ClassNotFoundException, SQLException;
	String updateReservationStatus(int reservationId,Status status) throws ClassNotFoundException, SQLException;
	String cancelReservation(int reservationId) throws ClassNotFoundException, SQLException;
	String cancelReservationByCustomerId(int customerId) throws ClassNotFoundException, SQLException;
	public double getVehicleCost(int vehicleId) throws SQLException,ClassNotFoundException;
	public int getDateDiff(Timestamp startDate, Timestamp endDate) throws SQLException,ClassNotFoundException;
}

package com.java.carconnect.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Reservation {
	private int reservationId;
	private int customerId;
	private int vehicleId;
	private Timestamp startDate;
	private Timestamp endDate;
	private double totalCost;
	private Status status;
	
	public Reservation() {

	}

	public Reservation(int reservationId, int customerId, int vehicleId, Timestamp startDate, Timestamp endDate,
			double totalCost, Status status) {
		super();
		this.reservationId = reservationId;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
	}
	public double calculateTotalCost(int days,double cost)
	{
		this.totalCost = days*cost;
		this.setTotalCost(totalCost);
		return totalCost;
	} 
	
	public void show()
	{
		System.out.println();
		System.out.println("Reservation Id : "+this.reservationId);
		System.out.println("Customer Id : "+this.customerId);
		System.out.println("Vehicle Id : "+this.vehicleId);
		System.out.println("Start Date : "+this.startDate);
		System.out.println("End Date : "+this.endDate);
		System.out.println("Total Cost : "+this.totalCost);
		System.out.println("Status : "+this.status);
		System.out.println("-----------------------------------");
	}

	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp timestamp) {
		this.startDate = timestamp;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", customerId=" + customerId + ", vehicleId=" + vehicleId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", totalCost=" + totalCost + ", status="
				+ status + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(reservationId, customerId,vehicleId,totalCost,status, startDate,endDate);
	}

	@Override
	public boolean equals(Object obj) {
		Reservation reservation = (Reservation)obj;
		if (reservation.getReservationId() == reservationId && reservation.getCustomerId() == customerId 
				&& reservation.getVehicleId() == vehicleId && reservation.getTotalCost() == totalCost 
				&& reservation.getStatus() == status && reservation.getStartDate() == startDate
				&& reservation.getEndDate() == endDate )
			return true;
		return false;
		
	}
	
	
	
}

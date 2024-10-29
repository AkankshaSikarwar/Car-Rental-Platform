package com.java.carconnect.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;
import com.java.carconnect.model.Reservation;
import com.java.carconnect.model.Status;
import com.java.carconnect.model.Vehicle;


public class ReservationTest {

    @Test
    public void testDefaultConstructor() {
        Reservation reservation = new Reservation();
        assertNotNull(reservation);
    }

    @Test
    public void testParameterizedConstructor() {
    	Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.CONFIRMED; // Assuming Status is an enum
        Reservation reservation = new Reservation(1, 2, 3,startDate, endDate ,100,status);

        assertEquals(1, reservation.getReservationId());
        assertEquals(2, reservation.getCustomerId());
        assertEquals(3, reservation.getVehicleId());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(100, reservation.getTotalCost(),0.001);
        assertEquals(status, reservation.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        Reservation reservation = new Reservation();
        Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.COMPLETED; // Assuming Status is an enum

        reservation.setReservationId(2);
        reservation.setCustomerId(3);
        reservation.setVehicleId(4);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotalCost(200);
        reservation.setStatus(status);
        
        assertEquals(2, reservation.getReservationId());
        assertEquals(3, reservation.getCustomerId());
        assertEquals(4, reservation.getVehicleId());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(200, reservation.getTotalCost(),0.001);
        assertEquals(status, reservation.getStatus());
    }

    @Test
    public void testCalculateTotalCost() {
        Reservation reservation = new Reservation();
        reservation.calculateTotalCost(5, 50);
        assertEquals(250, reservation.getTotalCost(),0.001);
    }

    @Test
    public void testToString() {
        Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.CONFIRMED; // Assuming Status is an enum
        Reservation reservation = new Reservation(3, 4, 5, startDate, endDate, 300, status);
        reservation.setStatus(status);
        
        String expected = "Reservation [reservationId=3, customerId=4, vehicleId=5, startDate=" + startDate +", endDate=" + endDate + ", totalCost=300, status=" + status + "]";

        
        assertEquals(expected, reservation.toString());
    }
    
    @Test
    public void testHashCode() {
    	Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.CONFIRMED; 
        
        Reservation reservation1 = new Reservation(1, 2, 3, startDate ,endDate , 100, status);
        Reservation reservation2 = new Reservation(1, 2, 3 ,startDate ,endDate , 100,status);
        assertEquals(reservation1.hashCode(),reservation2.hashCode());

    }
    
    @Test
    public void testEquals() {
    	Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.CONFIRMED;
    	
        Reservation reservation1 = new Reservation(1, 2, 3, startDate ,endDate , 100, status);
        Reservation reservation2 = new Reservation(1, 2, 3 ,startDate ,endDate , 100,status);
        Reservation reservation3 = new Reservation(1, 2, 3 ,startDate ,endDate , 101,status);


        assertTrue(reservation1.equals(reservation2));
        assertFalse(reservation1.equals(reservation3));
    }
}
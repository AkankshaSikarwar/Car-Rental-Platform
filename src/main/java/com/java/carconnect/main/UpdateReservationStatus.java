package com.java.carconnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.dao.IReservationService;
import com.java.carconnect.dao.ReservationService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Reservation;
import com.java.carconnect.model.Status;
import com.java.carconnect.util.ExceptionUtil;

public class UpdateReservationStatus {
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void updateReservationStatus() {
		
		try {
			
			Scanner sc = new Scanner(System.in);
			int reservationId = 0;
			boolean isInvalidReservationId = true;
			IReservationService reservationService = new ReservationService();
			
			do {
				boolean isInvalidInput = true;
				do {
					try {
						isInvalidInput = false;
						System.out.print("Enter Reservation Id : ");
						reservationId = sc.nextInt();
					} catch(InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException("Invalid Input Format entered. "
									+ "Please enter an Integer value (e.g. 40).");
						} catch (InvalidInputException iie) {
							System.out.println(RED+iie.getMessage()+RESET);
						}
					}
				} while(isInvalidInput);
				
				Reservation reservation = reservationService.getReservationById(reservationId);
				
				if(reservation!=null){
					isInvalidReservationId = false;
					reservation.show();
					
					Status status;
					boolean isInvalidStatus = true;
					String statusStr = "";
					do {
							System.out.print("Enter Status to update (CONFIRMED/ PENDING"
									+ "/ COMPLETED) : ");
							statusStr = sc.next();
							
							if(statusStr.toUpperCase().equals("CONFIRMED") || 
							   statusStr.toUpperCase().equals("PENDING") ||
							   statusStr.toUpperCase().equals("COMPLETED") ) {
								
								statusStr = statusStr.toUpperCase();
								status = Status.valueOf(statusStr);
								isInvalidStatus = false;
								
								String message = reservationService.updateReservationStatus(reservationId, status);
								System.out.println(GREEN+message+RESET);
							}
							else {
								isInvalidStatus = true;
								System.out.println(RED+"Invalid Input format. Please enter valid"
										+ " Status (CONFIRMED/ PENDING/ COMPLETED)."+RESET);
							}
					} while(isInvalidStatus);
					
					
				}
				else {
					isInvalidReservationId = true;
					System.out.println(RED+"No such Reservation Id exists. Please enter a valid "
							+ "Reservation Id."+RESET);
				}
				
			}while(isInvalidReservationId);
			
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

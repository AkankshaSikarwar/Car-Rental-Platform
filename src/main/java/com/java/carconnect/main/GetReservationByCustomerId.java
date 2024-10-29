package com.java.carconnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.java.carconnect.dao.IReservationService;
import com.java.carconnect.dao.ReservationService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.model.Reservation;
import com.java.carconnect.util.ExceptionUtil;

public class GetReservationByCustomerId {
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void getReservationByCustomerId() {
		
		try {
			
			Scanner sc = new Scanner(System.in);
			int customerId = 0;
			boolean isInvalidCustomerId = true;
			IReservationService reservationService = new ReservationService();
			
			do {
				boolean isInvalidInput = true;
				do {
					try {
						isInvalidInput = false;
						System.out.print("Enter Customer Id : ");
						customerId = sc.nextInt();
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
				
				List<Reservation> reservationList = reservationService.getReservationsByCustomerId(customerId);
				
				
				if(!reservationList.isEmpty()){
					isInvalidCustomerId = false;
					for (Reservation reservation : reservationList) {
						reservation.show();
					}
				}
				else {
					isInvalidCustomerId = true;
					System.out.println(RED+"No Reservations found for this Customer Id."+RESET);
				}
				
			}while(isInvalidCustomerId);
			
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

package com.java.carconnect.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.main.CancelReservation;
import com.java.carconnect.main.CreateReservation;
import com.java.carconnect.main.GetAvailableVehicles;
import com.java.carconnect.main.GetReservationByCustomerId;
import com.java.carconnect.main.UpdateCustomer;

public class CustomerMenu {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    
	private UpdateCustomer updateCustomer = new UpdateCustomer();
	private GetAvailableVehicles getAvailableVehicles = new GetAvailableVehicles();
	private CreateReservation createReservation = new CreateReservation();
	private CancelReservation cancelReservation = new CancelReservation();
	private GetReservationByCustomerId getReservationByCustomerId = new GetReservationByCustomerId();
	
	public void runCustomerMenu()
	{ 
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while(flag)
		{
			System.out.println("\n***********Welcome to CarConnect Customer Menu***********\n");
			System.out.println("1 - Update Customer \n"
							+ "2 - Get Available Vehicles \n"
							+ "3 - Create Reservation \n"
							+ "4 - Cancel Reservation \n"
							+ "5 - Your Reservations \n"
							+ "6 - Logout");
			
			boolean isInvalidInput = true;
			int input=0;
			while(isInvalidInput)
			{
				try {
					isInvalidInput = false;
					System.out.println("Chose one option.");
					input = sc.nextInt();
				} catch (InputMismatchException e) {
					isInvalidInput = true;
					sc.nextLine();
					System.out.println(RED+"Invalid Input enterd. Please enter an Integer Value."+RESET);
				}
			}
			
			switch (input)
			{
				case 1:
					updateCustomer.updateCustomer();
					break;
				case 2:
					getAvailableVehicles.getAvailableVehicles();
					break;
				case 3:
					createReservation.createReservation();
					break;
				case 4: 
					cancelReservation.cancelReservation();
					break;
				case 5:
					getReservationByCustomerId.getReservationByCustomerId();
					break;
				case 6:
					flag = false;
					System.out.println(GREEN+"Logged out Successfully."+RESET);
					break;
				default: 
					System.out.println("Please chose appropriate option.");
					break;
			}
		}
	}
}

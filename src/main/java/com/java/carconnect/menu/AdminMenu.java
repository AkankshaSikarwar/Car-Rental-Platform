package com.java.carconnect.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.main.AddVehicle;
import com.java.carconnect.main.CancelReservation;
import com.java.carconnect.main.CreateReservation;
import com.java.carconnect.main.DeleteAdmin;
import com.java.carconnect.main.DeleteCustomer;
import com.java.carconnect.main.GetAdminById;
import com.java.carconnect.main.GetAdminByUsername;
import com.java.carconnect.main.GetAvailableVehicles;
import com.java.carconnect.main.GetCustomerById;
import com.java.carconnect.main.GetCustomerByUsername;
import com.java.carconnect.main.GetReservationByCustomerId;
import com.java.carconnect.main.GetReservationById;
import com.java.carconnect.main.GetVehicleById;
import com.java.carconnect.main.RegisterAdmin;
import com.java.carconnect.main.RegisterCustomer;
import com.java.carconnect.main.RemoveVehicle;
import com.java.carconnect.main.UpdateAdmin;
import com.java.carconnect.main.UpdateCustomer;
import com.java.carconnect.main.UpdateReservation;
import com.java.carconnect.main.UpdateReservationStatus;
import com.java.carconnect.main.UpdateVehicle;
import com.java.carconnect.main.UpdateVehicleAvailabilityById;

public class AdminMenu {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	private GetCustomerById getCustomerById = new GetCustomerById();
	private GetCustomerByUsername getCustomerByUsername = new GetCustomerByUsername();
	private RegisterCustomer registerCustomer = new RegisterCustomer();
	private UpdateCustomer updateCustomer = new UpdateCustomer();
	private DeleteCustomer deleteCustomer = new DeleteCustomer();
	 
	private GetAdminById getAdminById = new GetAdminById();
	private GetAdminByUsername getAdminByUsername = new GetAdminByUsername();
	private RegisterAdmin registerAdmin = new RegisterAdmin();
	private UpdateAdmin updateAdmin = new UpdateAdmin();
	private DeleteAdmin deleteAdmin = new DeleteAdmin();
	
	private GetVehicleById getVehicleById = new GetVehicleById();
	private GetAvailableVehicles getAvailableVehicles = new GetAvailableVehicles();
	private AddVehicle addVehicle = new AddVehicle();
	private UpdateVehicle updateVehicle = new UpdateVehicle();
	private RemoveVehicle removeVehicle = new RemoveVehicle();
	private UpdateVehicleAvailabilityById updateVehicleAvailabilityById = new UpdateVehicleAvailabilityById();
	
	private GetReservationById getReservationById = new GetReservationById();
	private GetReservationByCustomerId getReservationByCustomerId = new GetReservationByCustomerId();
	private CreateReservation createReservation = new CreateReservation();
	private UpdateReservation updateReservation = new UpdateReservation();
	private CancelReservation cancelReservation = new CancelReservation();
	private UpdateReservationStatus updateReservationStatus = new UpdateReservationStatus();
			
	public void runAdminMenu() {
		
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		
		while(flag) 
		{
			System.out.println("\n**********Welcome to CarConnect**********\n");
			System.out.println();
			
			System.out.println("1 - Customer Service \n"
							+ "2 - Vehicle Service \n"
							+ "3 - Reservation Service \n"
							+ "4 - Admin Service \n"
							+ "5 - Logout");
			
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
			
			switch(input)
			{
			case 1:
				boolean flag1 = true;
				while(flag1) 
				{
					System.out.println("\n****Welcome to Customer Service**** \n");
					System.out.println("1 - Get Customer By Id \n"
									+ "2 - Get Customer By Username \n"
									+ "3 - Register Customer \n"
									+ "4 - Update Customer \n"
									+ "5 - Delete Customer \n"
									+ "6 - Back to main menu");
					
					isInvalidInput = true;
					int input1=0;
					while(isInvalidInput)
					{
						try {
							isInvalidInput = false;
							System.out.println("Chose one option.");
							input1 = sc.nextInt();
						} catch (InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							System.out.println(RED+"Invalid Input enterd. Please enter an Integer Value."+RESET);
						}
					}
					
					switch(input1)
					{
						case 1:
							getCustomerById.getCustomerById(); 
							break;
						case 2:
							getCustomerByUsername.getCustomerByUsername();
							break;
						case 3:
							registerCustomer.registerCustomer();
							break;
						case 4:
							updateCustomer.updateCustomer();
							break;
						case 5:
							deleteCustomer.deleteCustomer();
							break;
						case 6:
							flag1 = false;
							break;
						default:
						System.out.println(RED+"Please chose appropriate option."+RESET);
					}
				}
				break;
			case 2:
				boolean flag2 = true;
				while(flag2)
				{
					System.out.println("\n****Welcome to Vehicle Service**** \n");
					System.out.println("1 - Get Vehicle By Id \n"
									+ "2 - Get Available Vehicles \n"
									+ "3 - Add Vehicle \n"
									+ "4 - Update Vehicle \n"
									+ "5 - Remove Vehicle \n"
									+ "6 - Update Vehicle Availablility.\n"
									+ "7 - Back to main menu");
					
					isInvalidInput = true;
					int input2=0;
					while(isInvalidInput)
					{
						try {
							isInvalidInput = false;
							System.out.println("Chose one option.");
							input2 = sc.nextInt();
						} catch (InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							System.out.println(RED+"Invalid Input enterd. Please enter an Integer Value."+RESET);
						}
					}
					switch (input2)
					{
						case 1:
							getVehicleById.getVehicleById();
							break;
						case 2:
							getAvailableVehicles.getAvailableVehicles();
							break;
						case 3:
							addVehicle.addVehicle();
							break;
						case 4:
							updateVehicle.updateVehicle();
							break;
						case 5:
							removeVehicle.removeVehicle();
							break;
						case 6:
							updateVehicleAvailabilityById.updateVehicleAvailabilityById();
							break;
						case 7:
							flag2 = false;
							break;
						default:
							System.out.println(RED+"Please chose appropriate option."+RESET);
							break;
					}
				}
				break;
			case 3:
				boolean flag3 = true;
				while(flag3)
				{
					System.out.println("\n****Welcome to Reservation Service**** \n");
					System.out.println("1 - Get Reservation By Id \n"
									+ "2 - Get Reservation By Customer Id \n"
									+ "3 - Create Reservation \n"
									+ "4 - Update Reservation \n"
									+ "5 - Cancel Reservation \n"
									+ "6 - Update Reservation Status.\n"
									+ "7 - Back to main menu");
					isInvalidInput = true;
					int input3=0;
					while(isInvalidInput)
					{
						try {
							isInvalidInput = false;
							System.out.println("Chose one option.");
							input3 = sc.nextInt();
						} catch (InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							System.out.println(RED+"Invalid Input enterd. Please enter an Integer Value."+RESET);
						}
					}
					switch (input3)
					{
						case 1:
							getReservationById.getReservationById();
							break;
						case 2:
							getReservationByCustomerId.getReservationByCustomerId();
							break;
						case 3:
							createReservation.createReservation();
							break;
						case 4:
							updateReservation.updateReservation();
							break;
						case 5:
							cancelReservation.cancelReservation();
							break;
						case 6:
							updateReservationStatus.updateReservationStatus();
							break;
						case 7:
							flag3 = false;
							break;
						default:
							System.out.println(RED+"Please chose appropriate option."+RESET);
							break;
					}
				}
				break;
			case 4:
				boolean flag4 = true;
				
				while(flag4)
				{
					System.out.println("\n****Welcome to Admin Service**** \n");
					System.out.println("1 - Get Admin By Id \n"
							+ "2 - Get Admin By Username \n"
							+ "3 - Register Admin \n"
							+ "4 - Update Admin \n"
							+ "5 - Delete Admin \n"
							+ "6 - Back to main menu");
					
					isInvalidInput = true;
					int input4=0;
					while(isInvalidInput)
					{
						try {
							isInvalidInput = false;
							System.out.println("Chose one option.");
							input4 = sc.nextInt();
						} catch (InputMismatchException e) {
							isInvalidInput = true;
							sc.nextLine();
							System.out.println(RED+"Invalid Input enterd. Please enter an Integer Value."+RESET);
						}
					}
					
					switch (input4)
					{
						case 1:
							getAdminById.getAdminById();
							break;
						case 2:
							getAdminByUsername.getAdminByUsername();
							break;
						case 3:
							registerAdmin.registerAdmin();
							break;
						case 4:
							updateAdmin.updateAdmin();
							break;
						case 5:
							deleteAdmin.deleteAdmin();
							break;
						case 6:
							flag4 = false;
							break;
						default:
							System.out.println(RED+"Please chose appropriate option."+RESET);
							break;
					}
				}
				break;
			case 5:
				System.out.println(GREEN+"You are logged Out Successfully."+RESET);
				flag = false;
				break;
			default:
				System.out.println(RED+"Please chose appropriate option."+RESET);
				break;
			}
		}
		
	}
}

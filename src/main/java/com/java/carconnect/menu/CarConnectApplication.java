package com.java.carconnect.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.carconnect.main.ValidateCustomer;
import com.java.carconnect.main.RegisterAdmin;
import com.java.carconnect.main.RegisterCustomer;
import com.java.carconnect.main.ValidateAdmin;

public class CarConnectApplication {
	
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public static void main(String[] args) {
		
		RegisterCustomer registerCustomer = new RegisterCustomer();
		ValidateCustomer validateCustomer = new ValidateCustomer();
		
		RegisterAdmin registerAdmin = new RegisterAdmin();
		ValidateAdmin validateAdmin = new ValidateAdmin();
		
		AdminMenu adminMenu = new AdminMenu();
		CustomerMenu customerMenu = new CustomerMenu();
		
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while(flag)
		{
			System.out.println("\n************Welcome to CarConnect Platform************\n");
			
			System.out.println("1 - Admin \n"
							+ "2 - Customer \n"
							+ "3 - Exit");
			
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
				boolean flag1 = true;
				
				while(flag1)
				{
					System.out.println("****Welocme to Admin Menu****");
					System.out.println("1 - Login \n"
									+ "2 - Register. \n"
									+ "3 - Back to main menu.");
					
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
					
					switch (input1)
					{
						case 1:
							validateAdmin.validateAdmin();
							adminMenu.runAdminMenu();
							break;
						case 2:
							registerAdmin.registerAdmin();
							break;
						case 3:
							flag1 = false;
							break;
						default:
							System.out.println(RED+"Please chose appropriate option"+RESET);
					}
				}
				break;
			case 2:
				boolean flag2 = true;
				
				while(flag2)
				{
					System.out.println("****Welcome to Customer Menu****");
					System.out.println("1 - Login \n"
									+ "2 - Register.\n"
									+ "3 - Back to main menu.");
					
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
						validateCustomer.validateCustomer();
						customerMenu.runCustomerMenu();
						break;
					case 2:
						registerCustomer.registerCustomer();
						break;
					case 3:
						flag2 = false;
						break;
					default:
						System.out.println(RED+"Please chose appropriate option"+RESET);
					}
				}
				break;
			case 3:
				flag = false;
				break;
			default: 
				System.out.println(RED+"Please chose appropriate option."+RESET);
				break;
			}
		}
	}
}

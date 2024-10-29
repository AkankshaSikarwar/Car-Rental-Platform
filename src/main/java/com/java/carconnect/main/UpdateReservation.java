package com.java.carconnect.main;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.java.carconnect.dao.CustomerService;
import com.java.carconnect.dao.ICustomerService;
import com.java.carconnect.dao.IReservationService;
import com.java.carconnect.dao.IVehicleService;
import com.java.carconnect.dao.ReservationService;
import com.java.carconnect.dao.VehicleService;
import com.java.carconnect.exception.InvalidInputException;
import com.java.carconnect.exception.ReservationException;
import com.java.carconnect.exception.VehicleNotFoundException;
import com.java.carconnect.model.Customer;
import com.java.carconnect.model.Reservation;
import com.java.carconnect.model.Status;
import com.java.carconnect.model.Vehicle;
import com.java.carconnect.util.ExceptionUtil;

public class UpdateReservation {
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
	
	public void updateReservation() {
		try {
			
			Scanner sc = new Scanner(System.in);
			Reservation reservation = new Reservation();
			Reservation isValidReservation = null;
			boolean isInvalidReservationId = true;
			IReservationService reservationService = new ReservationService();
			
			int reservationId = 0;
			while(isInvalidReservationId)
			{
				boolean isInvalidInput = true;
				while(isInvalidInput)
				{ 
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
				}
				
				isValidReservation = reservationService.getReservationById(reservationId);
				
				if(isValidReservation==null){
					isInvalidReservationId = true;
					System.out.println(RED+"No Matching Reservation Id found."+RESET);
				}
				else {
					isInvalidReservationId = false;
					reservation.setReservationId(reservationId);
					
					int customerId = 0;
					boolean isInvalidCustomerId = true;
					isInvalidInput = true;
					
					while(isInvalidCustomerId)
					{
						ICustomerService customerService = new CustomerService();
						isInvalidInput = true;
						while(isInvalidInput)
						{
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
						}
						
						Customer customer = customerService.getCustomerById(customerId);
						
						if(customer==null) {
							isInvalidCustomerId = true;
							System.out.println(RED+"No matching Customer Id found. Please enter a valid"
									+ " Customer Id."+RESET);
						}
						else {
							isInvalidCustomerId = false;
							reservation.setCustomerId(customerId);
							
							boolean isInvalidVehicle = true;
							int vehicleId = 0;
							
							while(isInvalidVehicle)
							{
								try {
									boolean isInvalidVehicleId = true;
									isInvalidInput = true;
									while(isInvalidInput)
									{
										try {
											isInvalidInput = false;
											System.out.print("Enter Vehicle Id : ");
											vehicleId = sc.nextInt();
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
									}
									
									IVehicleService vehicleService = new VehicleService();
									Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
									
									if(vehicle==null) {
										isInvalidVehicle = true;
										throw new VehicleNotFoundException("No matching Vehicle Id found."
												+ "Please enter a valid Vehicle Id.");
									} 
									else if (!vehicle.getAvailability()) {
										sc.nextLine();
										isInvalidVehicle = true;
										System.out.println(RED+"This Vehicle is currently unavailable for"
												+ " Reservation."+RESET);
									}
									else {
										isInvalidVehicle = false; 
										sc.nextLine();
										boolean isReservationSlotUnavailable = true;
										while(isReservationSlotUnavailable)
										{
											Date startDate = null, endDate = null;
											boolean isInvalidDate = true;
											while(isInvalidDate)
											{
												System.out.print("Enter Start Date Time (yyyy-mm-dd HH:mm:ss) : ");
												String startDateStr = sc.nextLine();
												
												System.out.print("Enter End Date Time (yyyy-mm-dd HH:mm:ss) : ");
												String endDateStr = sc.nextLine();
												
												SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
												
												try {
													isInvalidDate = false;
													startDate = new Date(sdf.parse(startDateStr).getTime());
													endDate = new Date(sdf.parse(endDateStr).getTime());
													
													if(startDate.after(endDate)) {
														isInvalidDate = true;
														System.out.println(RED+"Start cannot be after End Date."+RESET);
													}
												} catch (ParseException e) {
													isInvalidDate = true;
													System.out.println(RED+"Invalid Date Time format entered."
															+ "Please enter correct Date Time format (yyyy-mm-dd HH:mm:ss)"+RESET);
												} 
											} // while end for date format 
											List<Reservation> reservationList = new ArrayList<Reservation>();
											reservationList = reservationService.getReservationByVehicleId(vehicleId);
											
//											System.out.println("Input -> Start Date : "+startDate+" End Date : "+endDate);
//											for (Reservation reservation2 : reservationList) {
//												reservation2.show();
//											}
											isReservationSlotUnavailable = false;
											for (Reservation reservationTemp : reservationList) {
												if(reservationTemp.getReservationId()==reservationId) {
													continue;
												}
												if(!(endDate.before(reservationTemp.getStartDate()) || 
														startDate.after(reservationTemp.getEndDate())))
												{
													if(reservationTemp.getStatus()==Status.CONFIRMED)
														//if(reservationTemp.getStatus().equals(Status.CONFIRMED))
													{
														try {
															isReservationSlotUnavailable = true;
															throw new ReservationException(RED+"Vehicle is already Reserved"
																	+ " for provided Time Slot."+RESET);
														} catch (ReservationException e) {
															System.out.println(RED+e.getMessage()+RESET);
														}
														
													}
												}
											} // for end reservation list 
												if (!isReservationSlotUnavailable) {
													isReservationSlotUnavailable = false;
													reservation.setVehicleId(vehicleId);
													reservation.setStartDate(new Timestamp(startDate.getTime()));
													reservation.setEndDate(new Timestamp(endDate.getTime()));
													
													double cost = reservationService.getVehicleCost(vehicleId);
													int days = reservationService.getDateDiff(new Timestamp(startDate.getTime()),new Timestamp(endDate.getTime()));
//													System.out.println("cost : "+cost+" days : "+days);
													double totalCost;
													if(days==0) {
														totalCost = cost;
													}
													else {
														totalCost = cost * days;
													}
													System.out.println("Total cost for entered time period and vehicle : "+totalCost);
													
													String continueReservation = "";
													
													
														isInvalidInput = true;
														while(isInvalidInput)
														{
															try {
																isInvalidInput = false;
																System.out.print("Would you like to make the Reservation (yes/no) : ");
																continueReservation = sc.next();
																
																if(continueReservation.equalsIgnoreCase("no")) {
																	isInvalidInput = false;
																	return;
																}
																else if(!continueReservation.equalsIgnoreCase("yes")){
																	isInvalidInput = true;
																	throw new InvalidInputException("Invalid Input enterd. Please enter yes/no.");
																}
																else {
																	isInvalidInput = false;
																	reservation.setTotalCost(totalCost);
																	
																	String paymentDone = "";
																	Status status = null;
																	isInvalidInput = true;
																	while(isInvalidInput)
																	{
																		try {
																				System.out.print("Have you made the payment? (yes/no) : ");
																				paymentDone = sc.next();
																					
																				if(paymentDone.equalsIgnoreCase("no")) {
																					isInvalidInput = false;
																					status = status.PENDING;
																				} 
																				else if(paymentDone.equalsIgnoreCase("yes")) {
																					isInvalidInput = false;
																					status = status.CONFIRMED;
																				}
																				else {
																					isInvalidInput = true;
																					throw new InvalidInputException("Invalid Input enterd. Please enter yes/no.");
																				}
																			} catch (InvalidInputException e) {
																				System.out.println(RED+e.getMessage()+RESET);
																			}
																		}
																		reservation.setStatus(status);
																	
																		String message = reservationService.updateReservation(reservation);
																		System.out.println(GREEN+message+RESET);
																}
															} catch (InvalidInputException e) {
																System.out.println(RED+e.getMessage()+RESET);
															}
														}
													
												} // if end for reservation slot availability -> 4th loop
											
										} // while end for reservation slot availability -> 4th loop
									} // else end for vehicle id -> 3rd loop
									
								} catch (VehicleNotFoundException e) {
									System.out.println(RED+e.getMessage()+RESET);
								}
							} // while end for vehicle id -> 3rd loop
						} // else end for customer id -> 2nd loop
					} // while end for customer id -> 2nd loop
				} // else end for reservation id -> 1st loop
			} // while end for reservation Id -> 1st loop
			
		} catch(SQLException e) {
			if(ExceptionUtil.isConnectionIssue(e)){
				System.out.println(RED+"Unable to connect to database. "+e.getMessage()+RESET);
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

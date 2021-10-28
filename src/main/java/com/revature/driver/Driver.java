package com.revature.driver;

import java.util.Scanner;
// import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.Customer;
//import com.revature.model.Application;
//import com.revature.model.Employee;

import com.revature.repository.AccountRepositoryImpl;
import com.revature.repository.ApplicationRepositoryImpl;
import com.revature.repository.CustomerRepositoryImpl;
import com.revature.repository.EmployeeRepositoryImpl;

public class Driver {
	
	static Boolean run = true;

	private static Scanner scan = new Scanner(System.in);
//	private final Logger loggy = LogManager.getLogger(Driver.class);
	private static Logger loggy = LogManager.getLogger(Driver.class);
	
	static AccountRepositoryImpl accountRepositoryImpl = new AccountRepositoryImpl();
	static ApplicationRepositoryImpl applicationRepositoryImpl = new ApplicationRepositoryImpl();
	static CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl();
	static EmployeeRepositoryImpl employeeRepositoryImpl = new EmployeeRepositoryImpl();
	
	public static void main(String[] args) {
		
		while(run) {
			
			//fix the line breaks
			System.out.println("Welcome to the Bank!\n");
		
			System.out.println("Main menu. Please choose from the following options.");
			System.out.println("1. Customer \n2. Employee \n3. Exit \n");
		
			String input = scan.nextLine();
			
			switch(input) {
			
			// customer
			case "1":
			
				boolean runCustomer = true;
				
				while(runCustomer) {
					
					System.out.println("\nWelcome customer! Please choose from the following options.");
					System.out.println("1. Log in \n2. Apply for an account \n3. Exit to main menu\n");
					String customerInput = scan.nextLine();
					
					switch(customerInput) {

					case "1":

						System.out.println("\nPlease enter your username.");
						String username = scan.nextLine();

						System.out.println("Please enter your password.");
						String password = scan.nextLine();

						Customer getCustomer = customerRepositoryImpl.getCustomerByUsername(username);
						
						if(getCustomer != null) {

							if(Customer.logIn(getCustomer, username, password)) {
								
								boolean runCustomerLoggedIn = true;
								
								while(runCustomerLoggedIn) {
									//									
									Customer currentCustomer = customerRepositoryImpl.getCustomerByUsername(username);
									//								
									Account currentAccount = accountRepositoryImpl.getAccountByAccountNumber(currentCustomer.getAccountNumber());
									
									//
									System.out.println("\nWelcome, " + currentCustomer.getFirstName() + "! Your current balance is " + currentAccount.getBalance() + ".");
									System.out.println("\nWhat would you like to do today?");
									System.out.println("1. Deposit \n2. Withdraw \n3. Transfer \n4. Log out\n");
									
									String customerAccountInput = scan.nextLine();
									
									switch(customerAccountInput) {
									
									// deposit
									case "1":
										
										System.out.println("\nHow much would you like to deposit?\n");
										
										if(scan.hasNextDouble()) {
											
											double amountDeposit = scan.nextDouble();
											// scan.nextLine();
											// Customer?											
											Account.Deposit(currentAccount, amountDeposit);
											//									
											accountRepositoryImpl.updateAccount(currentAccount);		
											//
											loggy.debug("Customer " + username.toString() + " deposited $" + amountDeposit );
											
										}else {
											
											System.out.println("\nInvalid amount. Please try again.");
											
										}
										
										break;
										
									// withdraw
									case "2":
										
										System.out.println("How much would you like to withdraw today?");
										
										if(scan.hasNextDouble()) {
											
											double amountWithdraw = scan.nextDouble();
											// scan.nextLine();
											// Customer?											
											Account.Withdraw(currentAccount, amountWithdraw);
											//									
											accountRepositoryImpl.updateAccount(currentAccount);
											//
											loggy.debug("Customer " + username.toString() + " withdrew $" + amountWithdraw );

										}else {
											
											System.out.println("\nInvalid amount. Please try again.");
											
										}

										break;
										
									// transfer
									case "3":
										
										System.out.println("What is the username of the account being transferred to?");
										String otherUser = scan.nextLine();
										
										//
										if (customerRepositoryImpl.getCustomerByUsername(otherUser) != null) {
											
											Customer targetCustomer = customerRepositoryImpl.getCustomerByUsername(otherUser);
											Account targetAccount = accountRepositoryImpl.getAccountByAccountNumber(targetCustomer.getAccountNumber());
											
											System.out.println("How much would you like to transfer?");
											
											if(scan.hasNextDouble()) {
												
												double transfer = scan.nextDouble();
												scan.nextLine();
												
												Account.Transfer(currentAccount, targetAccount, transfer);
												accountRepositoryImpl.updateAccount(currentAccount);
												accountRepositoryImpl.updateAccount(targetAccount);
												
												loggy.debug("Customer " + username.toString() + " transfered $" + transfer + " to " + targetAccount.toString());
												
											} else {
												
												System.out.println("Invalid amount. Please try again.");
												
												scan.nextLine();
//												// more logic here? go back to scan.hasNextDouble() method?
												
											}
											
										} else {
											
											System.out.println("Username not found. Please try again.");
											
										}
										
										break;
										
									// log out
									case "4":
										
										// accountRepositoryImpl.updateAccount(currentAccount);

										runCustomerLoggedIn = false;
										
										break;
										
									default:
									
										// accountRepositoryImpl.updateAccount(currentAccount);
										
										System.out.println("Invalid option. Please try again. ");
										
										break;
									
									// end of switch(customerAccountInput) method
									}
									
								// end of while(runCustomerLoggedIn) method
								}
								
							// end of if(Customer.logIn(getCustomer, username, password) method
							} else {

								System.out.println("Invalid username and password combination. Please try again.");

							}

						// end of if(getCustomer != null) method
						} else {
							
							System.out.println("Username not found. Please try again.");
							
						}

						break;

					// apply for an account
					// add code
					case "2":

						break;

					// exit to main menu
					case "3":
						
						runCustomer = false;
						
						break;

					default:
						
						System.out.println("Invalid option. Please try again.");

						break;

					// end of switch(customerInput) method
					}
			
			// end of while(runCustomer) method
			}
				
				break;
			
			// employee
			// add code
			case "2":
			
				break;
		
			// exit main menu	
			case "3":
			
				run = false;
			
				break;
		
			default:
			
				System.out.println("Invalid option. Please try again.");
			
				break;
			
			// end of switch(input) method
			}
			
		// end of while(run) method
		}
		
		scan.close();

	// end of main method
	}
	
// end of class
}
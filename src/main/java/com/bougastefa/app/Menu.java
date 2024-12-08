package com.bougastefa.app;

import java.util.Scanner;

//Main menu class. Acts as the main interface for uesr interraction.
public class Menu {
  private Scanner scanner;
  private CustomerService customerService;
  private FlightService flightService;
  private BookingService bookingService;
  private RouteService routeService;

  // Constructor to create menu object with standard validation
  public Menu() {
    this(new ValidationUtils());
  }

  // Protected Constructor allowing custom validation injection
  // Main purpose is testing
  protected Menu(IValidationUtils validator) {
    this.scanner = new Scanner(System.in);
    initializeServices(validator);
  }

  // Instances for the different services for different operations with the
  // provided validator
  protected void initializeServices(IValidationUtils validator) {
    this.customerService = new CustomerService(validator);
    this.flightService = new FlightService(validator);
    this.bookingService = new BookingService(validator);
    this.routeService = new RouteService(validator);
  }

  /**
   * Displays and handles the main menu interface.
   * Provides a loop that:
   * 1. Shows available options
   * 2. Processes user selection
   * 3. Delegates to appropriate service
   * 4. Asks if user wants to continue
   * 5. Handles program exit
   */
  public void displayMenu() {
    while (true) {
      System.out.println("\nSelect an option:");
      System.out.println("1. Add a Customer");
      System.out.println("2. Add a Flight");
      System.out.println("3. Add a Booking");
      System.out.println("4. Add a Route");
      System.out.println("5. Exit");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          customerService.addCustomer(scanner);
          break;
        case 2:
          flightService.addFlight(scanner);
          break;
        case 3:
          bookingService.addBooking(scanner);
          break;
        case 4:
          routeService.addRoute(scanner);
          break;
        case 5:
          System.out.println("Exiting...");
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice, please try again.");
      }
      // Adding option to enter multiple entries in one session
      System.out.println("Do you wish to add another entry? (y/n)");
      String continueChoice = scanner.nextLine();
      if (!continueChoice.equalsIgnoreCase("y")) {
        break;
      }
    }
  }
}

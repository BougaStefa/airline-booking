package com.bougastefa.app;

import java.util.Scanner;

public class Menu {
  private Scanner scanner;
  private CustomerService customerService;
  private FlightService flightService;
  private BookingService bookingService;
  private RouteService routeService;

  public Menu() {
    this.scanner = new Scanner(System.in);
    ValidationUtils validator = new ValidationUtils();
    this.customerService = new CustomerService(validator);
    this.flightService = new FlightService(validator);
    this.bookingService = new BookingService(validator);
    this.routeService = new RouteService(validator);
  }

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

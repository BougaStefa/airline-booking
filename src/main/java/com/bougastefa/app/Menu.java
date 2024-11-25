package com.bougastefa.app;

import java.util.Scanner;

public class Menu {
  private Scanner scanner;

  public Menu() {
    this.scanner = new Scanner(System.in);
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
          addCustomer();
          break;
        case 2:
          addFlight();
          break;
        case 3:
          addBooking();
          break;
        case 4:
          addRoute();
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

  private String getValidatedInput(String prompt, InputValidationMethod validator) {
    String input;
    while (true) {
      System.out.println(prompt);
      input = scanner.nextLine();
      if (validator.isValid(input)) {
        break;
      } else {
        System.out.println("Invalid input, please try again.");
      }
    }
    return input;
  }

  @FunctionalInterface
  public interface InputValidationMethod {
    boolean isValid(String input);
  }

  private void addCustomer() {
    System.out.println("Enter Customer Details:");

    // Validating each column until a correct input is provided
    String customerId = getValidatedInput("Customer ID (GR followed by digits): ", ValidationUtils::isValidCustomerId);
    String forename = getValidatedInput("Forename: ", ValidationUtils::isValidName);
    String surname = getValidatedInput("Surname: ", ValidationUtils::isValidName);
    String street = getValidatedInput("Street: ", ValidationUtils::isValidAddress);
    String town = getValidatedInput("Town: ", ValidationUtils::isValidName);
    String postcode = getValidatedInput("Postcode: ", ValidationUtils::isValidPostcode);

    Customer customer = new Customer(customerId, forename, surname, street, town, postcode);
    CSVUtilities.exportToCsv(customer, "Customer.csv");
    System.out.println("Customer added to CSV file!");
  }

  private void addFlight() {
    System.out.println("Enter Flight Details:");

    String flightId = getValidatedInput("Flight ID: ", ValidationUtils::isValidFlightId);
    String departureDate = getValidatedInput("Departure Date: ", ValidationUtils::isValidDate);
    String arrivalDate = getValidatedInput("Arrival Date: ", ValidationUtils::isValidDate);
    String departureTime = getValidatedInput("Departure Time: ", ValidationUtils::isValidTime);
    String arrivalTime = getValidatedInput("Arrival Time: ", ValidationUtils::isValidTime);
    String capacity = getValidatedInput("Capacity: ", ValidationUtils::isValidPositiveInteger);
    InputValidationMethod routeIdLengthCheck = input -> ValidationUtils.isValidPositiveInteger(input, 50);
    String routeId = getValidatedInput("Route ID: ", routeIdLengthCheck);

    Flight flight = new Flight(flightId, departureDate, departureTime, routeId, arrivalDate,
        arrivalTime, capacity);
    CSVUtilities.exportToCsv(flight, "Flight.csv");
    System.out.println("Flight added to CSV file!");
  }

  private void addBooking() {
    System.out.println("Enter Booking Details:");

    InputValidationMethod bookingIdLengthCheck = input -> ValidationUtils.isValidPositiveInteger(input, 50)
        && ValidationUtils.isUniquePrimaryKey(input, "Booking.csv");
    String bookingId = getValidatedInput("Booking ID: ", bookingIdLengthCheck);
    String adultTicket = getValidatedInput("Adult Tickets: ", ValidationUtils::isValidPositiveInteger);
    String childTicket = getValidatedInput("Child Tickets: ", ValidationUtils::isValidPositiveInteger);
    String concessionTicket = getValidatedInput("Concession Tickets: ", ValidationUtils::isValidPositiveInteger);
    String customerId = getValidatedInput("Customer ID: ", ValidationUtils::isValidCustomerId);
    String flightId = getValidatedInput("Flight ID: ", ValidationUtils::isValidFlightId);

    Booking booking = new Booking(bookingId, customerId, adultTicket, childTicket, concessionTicket, flightId);
    CSVUtilities.exportToCsv(booking, "Booking.csv");
    System.out.println("Booking added to CSV file!");
  }

  private void addRoute() {
    System.out.println("Enter Route Details:");

    InputValidationMethod routeIdLengthCheck = input -> ValidationUtils.isValidPositiveInteger(input, 50)
        && ValidationUtils.isUniquePrimaryKey(input, "Route.csv");
    ;
    String routeId = getValidatedInput("Route ID: ", routeIdLengthCheck);
    String departFrom = getValidatedInput("Depart From: ", ValidationUtils::isValidAirport);
    String arriveAt = getValidatedInput("Arrive At: ", ValidationUtils::isValidAirport);
    InputValidationMethod isValidStop = input -> ValidationUtils.isValidAirport(input, 1);
    String midStopOne = getValidatedInput("Mid Stop One: ", isValidStop);
    String midStopTwo = getValidatedInput(("Mid Stop Two: "), isValidStop);

    Route route = new Route(routeId, departFrom, arriveAt, midStopOne, midStopTwo);
    System.out.println(routeId + departFrom + arriveAt + midStopTwo + midStopOne);
    CSVUtilities.exportToCsv(route, "Route.csv");
    System.out.println("Route added to CSV file!");
  }
}

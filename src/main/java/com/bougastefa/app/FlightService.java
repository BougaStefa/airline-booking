package com.bougastefa.app;

import java.util.Scanner;

// Recieves and handles the validity of Flight values before exporting to CSV
public class FlightService {
  private IValidationUtils validationUtils;

  public FlightService(IValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addFlight(Scanner scanner) {
    System.out.println("Enter Flight Details:");
    // Takes the user through providing each value, ensuring that valid values are
    // provided
    String flightId = validationUtils.getValidatedInput(scanner, "Flight ID: ", validationUtils::isValidFlightId);
    String departureDate = validationUtils.getValidatedInput(scanner, "Departure Date: ", validationUtils::isValidDate);
    String arrivalDate = validationUtils.getValidatedInput(scanner, "Arrival Date: ", validationUtils::isValidDate);
    String departureTime = validationUtils.getValidatedInput(scanner, "Departure Time: ", validationUtils::isValidTime);
    String arrivalTime = validationUtils.getValidatedInput(scanner, "Arrival Time: ", validationUtils::isValidTime);
    String capacity = validationUtils.getValidatedInput(scanner, "Capacity: ", validationUtils::isValidPositiveInteger);
    // Modifying the integer checker to account for length
    ValidationUtils.InputValidationMethod routeIdLengthCheck = input -> validationUtils.isValidPositiveInteger(input,
        50);
    String routeId = validationUtils.getValidatedInput(scanner, "Route ID: ", routeIdLengthCheck);
    // creates new flight instance and exports it to CSV
    Flight flight = new Flight(flightId, departureDate, departureTime, routeId, arrivalDate,
        arrivalTime, capacity);
    CSVUtilities.exportToCsv(flight, "Flight.csv");
    System.out.println("Flight added to CSV file!");
  }
}

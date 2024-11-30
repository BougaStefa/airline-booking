package com.bougastefa.app;

import java.util.Scanner;

public class FlightService {
  private ValidationUtils validationUtils;

  public FlightService(ValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addFlight(Scanner scanner) {
    System.out.println("Enter Flight Details:");

    String flightId = validationUtils.getValidatedInput(scanner, "Flight ID: ", ValidationUtils::isValidFlightId);
    String departureDate = validationUtils.getValidatedInput(scanner, "Departure Date: ", ValidationUtils::isValidDate);
    String arrivalDate = validationUtils.getValidatedInput(scanner, "Arrival Date: ", ValidationUtils::isValidDate);
    String departureTime = validationUtils.getValidatedInput(scanner, "Departure Time: ", ValidationUtils::isValidTime);
    String arrivalTime = validationUtils.getValidatedInput(scanner, "Arrival Time: ", ValidationUtils::isValidTime);
    String capacity = validationUtils.getValidatedInput(scanner, "Capacity: ", ValidationUtils::isValidPositiveInteger);
    ValidationUtils.InputValidationMethod routeIdLengthCheck = input -> ValidationUtils.isValidPositiveInteger(input,
        50);
    String routeId = validationUtils.getValidatedInput(scanner, "Route ID: ", routeIdLengthCheck);

    Flight flight = new Flight(flightId, departureDate, departureTime, routeId, arrivalDate,
        arrivalTime, capacity);
    CSVUtilities.exportToCsv(flight, "Flight.csv");
    System.out.println("Flight added to CSV file!");
  }
}

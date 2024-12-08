package com.bougastefa.app;

import java.util.Scanner;

// Recieves user inputs for route entries and handles their validity before exporting 
public class RouteService {
  private IValidationUtils validationUtils;

  public RouteService(IValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addRoute(Scanner scanner) {
    System.out.println("Enter Route Details:");
    // Takes the user through providing valid values for each required entry
    ValidationUtils.InputValidationMethod routeIdLengthCheck = input -> validationUtils.isValidPositiveInteger(input,
        50)
        && validationUtils.isUniquePrimaryKey(input, "Route.csv"); // Adding digit limit
    ;
    String routeId = validationUtils.getValidatedInput(scanner, "Route ID: ", routeIdLengthCheck);
    String departFrom = validationUtils.getValidatedInput(scanner, "Depart From: ", validationUtils::isValidAirport);
    String arriveAt = validationUtils.getValidatedInput(scanner, "Arrive At: ", validationUtils::isValidAirport);
    // Adjusting airport check to allow for empty responses as mid stops aren't
    // mandatory
    ValidationUtils.InputValidationMethod isValidStop = input -> validationUtils.isValidAirport(input, 1);
    String midStopOne = validationUtils.getValidatedInput(scanner, "Mid Stop One: ", isValidStop);
    String midStopTwo = validationUtils.getValidatedInput(scanner, ("Mid Stop Two: "), isValidStop);
    // Creates new object and writes to file
    Route route = new Route(routeId, departFrom, arriveAt, midStopOne, midStopTwo);
    CSVUtilities.exportToCsv(route, "Route.csv");
    System.out.println("Route added to CSV file!");
  }
}

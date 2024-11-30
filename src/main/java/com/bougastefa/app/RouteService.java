package com.bougastefa.app;

import java.util.Scanner;

public class RouteService {
  private ValidationUtils validationUtils;

  public RouteService(ValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addRoute(Scanner scanner) {
    System.out.println("Enter Route Details:");

    ValidationUtils.InputValidationMethod routeIdLengthCheck = input -> ValidationUtils.isValidPositiveInteger(input,
        50)
        && ValidationUtils.isUniquePrimaryKey(input, "Route.csv");
    ;
    String routeId = validationUtils.getValidatedInput(scanner, "Route ID: ", routeIdLengthCheck);
    String departFrom = validationUtils.getValidatedInput(scanner, "Depart From: ", ValidationUtils::isValidAirport);
    String arriveAt = validationUtils.getValidatedInput(scanner, "Arrive At: ", ValidationUtils::isValidAirport);
    ValidationUtils.InputValidationMethod isValidStop = input -> ValidationUtils.isValidAirport(input, 1);
    String midStopOne = validationUtils.getValidatedInput(scanner, "Mid Stop One: ", isValidStop);
    String midStopTwo = validationUtils.getValidatedInput(scanner, ("Mid Stop Two: "), isValidStop);

    Route route = new Route(routeId, departFrom, arriveAt, midStopOne, midStopTwo);
    System.out.println(routeId + departFrom + arriveAt + midStopTwo + midStopOne);
    CSVUtilities.exportToCsv(route, "Route.csv");
    System.out.println("Route added to CSV file!");
  }
}

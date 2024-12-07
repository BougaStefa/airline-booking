package com.bougastefa.app;

import java.util.Scanner;

public class BookingService {
  private IValidationUtils validationUtils;

  public BookingService(IValidationUtils validationUtils2) {
    this.validationUtils = validationUtils2;
  }

  public void addBooking(Scanner scanner) {
    System.out.println("Enter Booking Details:");

    ValidationUtils.InputValidationMethod bookingIdLengthCheck = input -> validationUtils.isValidPositiveInteger(input,
        50)
        && validationUtils.isUniquePrimaryKey(input, "Booking.csv");
    String bookingId = validationUtils.getValidatedInput(scanner, "Booking ID: ", bookingIdLengthCheck);
    String adultTicket = validationUtils.getValidatedInput(scanner, "Adult Tickets: ",
        validationUtils::isValidPositiveInteger);
    String childTicket = validationUtils.getValidatedInput(scanner, "Child Tickets: ",
        validationUtils::isValidPositiveInteger);
    String concessionTicket = validationUtils.getValidatedInput(scanner, "Concession Tickets: ",
        validationUtils::isValidPositiveInteger);
    String customerId = validationUtils.getValidatedInput(scanner, "Customer ID: ", validationUtils::isValidCustomerId);
    String flightId = validationUtils.getValidatedInput(scanner, "Flight ID: ", validationUtils::isValidFlightId);

    Booking booking = new Booking(bookingId, customerId, adultTicket, childTicket, concessionTicket, flightId);
    CSVUtilities.exportToCsv(booking, "Booking.csv");
    System.out.println("Booking added to CSV file!");
  }
}

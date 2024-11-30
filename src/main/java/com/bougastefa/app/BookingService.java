package com.bougastefa.app;

import java.util.Scanner;

public class BookingService {
  private ValidationUtils validationUtils;

  public BookingService(ValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addBooking(Scanner scanner) {
    System.out.println("Enter Booking Details:");

    ValidationUtils.InputValidationMethod bookingIdLengthCheck = input -> ValidationUtils.isValidPositiveInteger(input,
        50)
        && ValidationUtils.isUniquePrimaryKey(input, "Booking.csv");
    String bookingId = validationUtils.getValidatedInput(scanner, "Booking ID: ", bookingIdLengthCheck);
    String adultTicket = validationUtils.getValidatedInput(scanner, "Adult Tickets: ",
        ValidationUtils::isValidPositiveInteger);
    String childTicket = validationUtils.getValidatedInput(scanner, "Child Tickets: ",
        ValidationUtils::isValidPositiveInteger);
    String concessionTicket = validationUtils.getValidatedInput(scanner, "Concession Tickets: ",
        ValidationUtils::isValidPositiveInteger);
    String customerId = validationUtils.getValidatedInput(scanner, "Customer ID: ", ValidationUtils::isValidCustomerId);
    String flightId = validationUtils.getValidatedInput(scanner, "Flight ID: ", ValidationUtils::isValidFlightId);

    Booking booking = new Booking(bookingId, customerId, adultTicket, childTicket, concessionTicket, flightId);
    CSVUtilities.exportToCsv(booking, "Booking.csv");
    System.out.println("Booking added to CSV file!");
  }
}

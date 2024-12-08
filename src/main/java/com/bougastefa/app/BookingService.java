package com.bougastefa.app;

import java.util.Scanner;

// This class recieves and verifies booking details before handling the exporting of it to file
public class BookingService {
  private IValidationUtils validationUtils;

  public BookingService(IValidationUtils validationUtils2) {
    this.validationUtils = validationUtils2;
  }

  public void addBooking(Scanner scanner) {
    System.out.println("Enter Booking Details:");
    // Takes the uesr through providing values for each field, ensures all required
    // fields recieve a valid value
    ValidationUtils.InputValidationMethod bookingIdLengthCheck = input -> validationUtils.isValidPositiveInteger(input,
        50) // Modifying the number check to account for length
        && validationUtils.isUniquePrimaryKey(input, "Booking.csv"); // Checking file for PKs. Technically if there's no
                                                                     // file the PK is unique
    String bookingId = validationUtils.getValidatedInput(scanner, "Booking ID: ", bookingIdLengthCheck);
    // Section to handle edge case of 3x0 tickets. 0 valid for ticket value but a
    // booking must have at least one, logically
    String adultTicket, childTicket, concessionTicket;
    String confirm;
    do {
      adultTicket = validationUtils.getValidatedInput(scanner, "Adult Tickets: ",
          validationUtils::isValidPositiveInteger);
      childTicket = validationUtils.getValidatedInput(scanner, "Child Tickets: ",
          validationUtils::isValidPositiveInteger);
      concessionTicket = validationUtils.getValidatedInput(scanner, "Concession Tickets: ",
          validationUtils::isValidPositiveInteger);

      confirm = validationUtils.getValidatedTicketCombination(scanner, adultTicket, childTicket, concessionTicket);
    } while (confirm.equalsIgnoreCase("N"));
    String customerId = validationUtils.getValidatedInput(scanner, "Customer ID: ", validationUtils::isValidCustomerId);
    String flightId = validationUtils.getValidatedInput(scanner, "Flight ID: ", validationUtils::isValidFlightId);
    // Create new booking instance and export to CSV
    Booking booking = new Booking(bookingId, customerId, adultTicket, childTicket, concessionTicket, flightId);
    CSVUtilities.exportToCsv(booking, "Booking.csv");
    System.out.println("Booking added to CSV file!");
  }
}

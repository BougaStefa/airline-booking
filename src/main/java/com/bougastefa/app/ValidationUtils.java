package com.bougastefa.app;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;

// Implements all the input validation for each Service
public class ValidationUtils implements IValidationUtils {

  // Most fields are required, this check will be reused on most following methods
  @Override
  public boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  @Override
  // Combines PK and format in one
  public boolean isValidCustomerId(String customerId) {
    if (isNullOrEmpty(customerId)) {
      return false;
    }
    return isValidCustomerIdFormat(customerId) && isUniquePrimaryKey(customerId, "Customer.csv");
  }

  @Override
  public boolean isValidCustomerIdFormat(String customerId) {
    if (isNullOrEmpty(customerId)) {
      return false;
    }
    // GR followed by 2-48 numbers limiting it to 50
    return customerId.matches("GR\\d{1,48}");
  }

  @Override
  public boolean isValidName(String name) {
    if (isNullOrEmpty(name)) {
      return false;
    }
    // Letters, apostrophes and dashes all allowed
    return name.matches("[a-zA-Z\\s'-]{2,50}");
  }

  @Override
  public boolean isValidAddress(String address) {
    if (isNullOrEmpty(address)) {
      return false;
    }
    // At least a number and a letter, commas dashes forward slashes spaceand hashes
    // allowed
    return address.matches("[a-zA-Z0-9\\s,\\.\\-#/]{5,100}");
  }

  @Override
  public boolean isValidPostcode(String postcode) {
    if (isNullOrEmpty(postcode)) {
      return false;
    }
    // Any alphanumerical with allowance for spaces
    return postcode.matches("[a-zA-Z0-9\\s]{3,10}");
  }

  @Override
  public boolean isValidPositiveInteger(String number, int maxDigits) {
    if (isNullOrEmpty(number) || maxDigits < 1) {
      return false;
    }
    // Any positive integer up to a certain number of digits
    return number.matches("\\d{1," + maxDigits + "}$");
  }

  @Override
  public boolean isValidPositiveInteger(String number) {
    if (isNullOrEmpty(number)) {
      return false;
    }
    // any positive integer
    return number.matches("\\d+");
  }

  @Override
  public boolean isValidAlphanumerical(String value) {
    if (isNullOrEmpty(value)) {
      return false;
    }
    // Alphanumerical with upper limit, could be merged with another check
    // potentially
    return value.matches("[a-zA-Z0-9]{0,50}");
  }

  @Override
  public boolean isValidDate(String dateString) {
    if (isNullOrEmpty(dateString)) {
      return false;
    }
    try {
      // Valid date with the allowance of 100 years in the past or future as the lower
      // and upper limit
      LocalDate date = LocalDate.parse(dateString);
      LocalDate startDate = LocalDate.now().minusYears(100);
      LocalDate endDate = LocalDate.now().plusYears(100);
      return !date.isBefore(startDate) && !date.isAfter(endDate);
    } catch (DateTimeException e) {
      System.out.println("Invalid date format or date range. Expected: YYYY-MM-DD");
      return false;
    }
  }

  @Override
  public boolean isValidTime(String timeString) {
    if (isNullOrEmpty(timeString)) {
      return false;
    }
    // Setting accepted time format
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    try {
      // Checking if the provided time is a valid time format matching the one we set
      // above
      LocalTime.parse(timeString, timeFormatter);
      return true;
    } catch (DateTimeException e) {
      System.out.println("Invalid time format. Expected: HH:mm:ss");
      return false;
    }
  }

  @Override
  public boolean isValidAirport(String airport) {
    if (isNullOrEmpty(airport)) {
      return false;
    }
    // 3 capital letters only
    return airport.matches("[A-Z]{3}$");
  }

  @Override
  public boolean isValidAirport(String airport, int stop) {
    if (airport != null) {
      // same as above but if it's a stop we can return an empty one
      return airport.matches("[A-Z]{3}$") || airport.trim().isEmpty();
    }
    return false;
  }

  @Override
  public boolean isValidFlightId(String flightId) {
    if (isNullOrEmpty(flightId)) {
      return false;
    }
    // 2 capital letters followed by 3 numbers. Has to be unique within the CSV file
    return flightId.matches("^[A-Z]{2}\\d{3}$") && isUniquePrimaryKey(flightId, "Flight.csv");
  }

  @Override
  public boolean isUniquePrimaryKey(String key, String filename) {
    Set<String> keysSet = new HashSet<>();
    // Reading the column 0 from the CSV and saving to hash set
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        keysSet.add(parts[0]);
      }
    } catch (IOException e) {
      return true;
    }
    // Is unique if the set doesn't contain the key
    return !keysSet.contains(key);
  }

  @Override
  public boolean isValidTicketCombination(String adultTickets, String childTickets, String concessionTickets) {
    try {
      // Converting input to integer and checking that their sum is at least 1
      // Can't have a booking with no tickets
      int adults = Integer.parseInt(adultTickets);
      int children = Integer.parseInt(childTickets);
      int concessions = Integer.parseInt(concessionTickets);
      return (adults + children + concessions) > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public String getValidatedTicketCombination(Scanner scanner, String adultTickets, String childTickets,
      String concessionTickets) {
    boolean ticketsConfirmed = false;
    String confirm = "";
    // Ensuring we get any issues with 0 total tickets resolved by asking the user
    // to review
    do {
      // Show summary of tickets
      System.out.println("\nTicket Summary:");
      System.out.println("Adult Tickets: " + adultTickets);
      System.out.println("Child Tickets: " + childTickets);
      System.out.println("Concession Tickets: " + concessionTickets);

      if (!isValidTicketCombination(adultTickets, childTickets, concessionTickets)) {
        System.out.println("Error: At least one ticket must be booked.");
        return "N";
      }

      confirm = getValidatedInput(scanner,
          "Are these tickets correct? (Y to confirm, N to re-enter): ",
          input -> input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N"));

      ticketsConfirmed = confirm.equalsIgnoreCase("Y");

      if (!ticketsConfirmed) {
        System.out.println("\nPlease re-enter the ticket information.");
      }

    } while (!ticketsConfirmed);

    return confirm;
  }

  @Override
  public String getValidatedInput(Scanner scanner, String prompt, InputValidationMethod validator) {
    // This method handles all the user interraction, takes a prompt based on the
    // value require, accepts user input and uses the validator passed to it. Will
    // loop until a valid input has been provided
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

  // Interface for custom validation methods. Some need lambda functions
  @FunctionalInterface
  public interface InputValidationMethod {
    boolean isValid(String input);
  }
}

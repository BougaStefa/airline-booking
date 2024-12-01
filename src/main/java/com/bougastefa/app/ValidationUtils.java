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

public class ValidationUtils implements IValidationUtils {

  // Checking for empty inputs method to avoid repeating the statement
  @Override
  public boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  @Override
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
    return customerId.matches("GR\\d{1,48}");
  }

  @Override
  public boolean isValidName(String name) {
    if (isNullOrEmpty(name)) {
      return false;
    }
    return name.matches("[a-zA-Z\\s'-]{2,50}");
  }

  @Override
  public boolean isValidAddress(String address) {
    if (isNullOrEmpty(address)) {
      return false;
    }
    return address.matches("[a-zA-Z0-9\\s,\\.\\-#/]{5,100}");
  }

  @Override
  public boolean isValidPostcode(String postcode) {
    if (isNullOrEmpty(postcode)) {
      return false;
    }
    return postcode.matches("[a-zA-Z0-9\\s]{3,10}");
  }

  @Override
  public boolean isValidPositiveInteger(String number, int maxDigits) {
    if (isNullOrEmpty(number) || maxDigits < 1) {
      return false;
    }
    return number.matches("\\d{1," + maxDigits + "}$");
  }

  @Override
  public boolean isValidPositiveInteger(String number) {
    if (isNullOrEmpty(number)) {
      return false;
    }
    return number.matches("\\d+");
  }

  @Override
  public boolean isValidAlphanumerical(String value) {
    if (isNullOrEmpty(value)) {
      return false;
    }
    return value.matches("[a-zA-Z0-9]{0,50}");
  }

  @Override
  public boolean isValidDate(String dateString) {
    if (isNullOrEmpty(dateString)) {
      return false;
    }
    try {
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
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    try {
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
    return airport.matches("[A-Z]{3}$");
  }

  @Override
  public boolean isValidAirport(String airport, int stop) {
    if (airport != null) {
      return airport.matches("[A-Z]{3}$") || airport.trim().isEmpty();
    }
    return false;
  }

  @Override
  public boolean isValidFlightId(String flightId) {
    if (isNullOrEmpty(flightId)) {
      return false;
    }
    return flightId.matches("^[A-Z]{2}\\d{3}$") && isUniquePrimaryKey(flightId, "Flight.csv");
  }

  @Override
  public boolean isUniquePrimaryKey(String key, String filename) {
    Set<String> keysSet = new HashSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        keysSet.add(parts[0]);
      }
    } catch (IOException e) {
      return true;
    }
    return !keysSet.contains(key);
  }

  @Override
  public String getValidatedInput(Scanner scanner, String prompt, InputValidationMethod validator) {
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
}

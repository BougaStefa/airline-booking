package com.bougastefa.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;

public class ValidationUtils {

  // Checking for empty inputs method to avoid repeating the statement
  public static boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }

  public static boolean isValidCustomerId(String customerId) {
    if (isNullOrEmpty(customerId)) {
      return false;
    }
    // customerId has to be GR followed by a
    // positive integer
    return customerId.matches("GR\\d{1,48}") && isUniquePrimaryKey(customerId, "Customer.csv");
  }

  public static boolean isValidName(String name) {
    if (isNullOrEmpty(name)) {
      return false;
    }
    // names can have letters spaces and apostrophes, works for human and town names
    return name.matches("[a-zA-Z\\s'-]{2,50}");
  }

  public static boolean isValidAddress(String address) {
    if (isNullOrEmpty(address)) {
      return false;
    }
    // address can be alphanumeric or have commas, hashes, full stops, dashes or
    // forward slashes
    return address.matches("[a-zA-Z0-9\\s,\\.\\-#/]{5,100}");
  }

  public static boolean isValidPostcode(String postcode) {
    if (isNullOrEmpty(postcode)) {
      return false;
    }
    // postcodes can be alphanumeric with optional spaces
    return postcode.matches("[a-zA-Z0-9\\s]{3,10}");
  }

  public static boolean isValidPositiveInteger(String number, int maxDigits) {
    if (isNullOrEmpty(number) || maxDigits < 1) {
      return false;
    }

    return number.matches("\\d{1," + maxDigits + "}$");
  }

  public static boolean isValidPositiveInteger(String number) {
    if (isNullOrEmpty(number)) {
      return false;
    }
    return number.matches("\\d+");
  }

  public static boolean isValidAlphanumerical(String value) {
    if (isNullOrEmpty(value)) {
      return false;
    }
    return value.matches("[a-zA-Z0-9]{0,50}");
  }

  public static boolean isValidDate(String dateString) {
    if (isNullOrEmpty(dateString)) {
      return false;
    }
    try {
      LocalDate date = LocalDate.parse(dateString);
      LocalDate startDate = LocalDate.now().minusYears(100);
      LocalDate endDate = LocalDate.now().plusYears(100);
      return !date.isBefore(startDate) && !date.isAfter(endDate); // Only 100 years plus minus accepted
    } catch (DateTimeException e) {
      System.out.println("Invalid date format or date range. Expected: YYYY-MM-DD");
      return false;
    }
  }

  // For date and time, if the parsing fails it means the input was incorrect.
  public static boolean isValidTime(String timeString) {
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

  public static boolean isValidAirport(String airport) {
    // Arrivals and departures mustn't be empty
    if (isNullOrEmpty(airport)) {
      return false;
    }
    return airport.matches("[A-Z]{3}$");
  }

  // When validating stops they can be empty
  public static boolean isValidAirport(String airport, int stop) {
    if (airport != null) {
      return airport.matches("[A-Z]{3}$") || airport.trim().isEmpty();
    }
    return false;
  }

  public static boolean isValidFlightId(String flightId) {
    if (isNullOrEmpty(flightId)) {
      return false;
    }
    return flightId.matches("^[A-Z]{2}\\d{3}$") && isUniquePrimaryKey(flightId, "Flight.csv");
  }

  public static boolean isUniquePrimaryKey(String key, String filename) {
    Set<String> keysSet = new HashSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        keysSet.add(parts[0]);
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
    return !keysSet.contains(key);
  }
}

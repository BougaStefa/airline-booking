package com.bougastefa.app;

import java.util.Scanner;

public interface IValidationUtils {
  boolean isNullOrEmpty(String value);

  boolean isValidCustomerId(String customerId);

  boolean isValidCustomerIdFormat(String customerId);

  boolean isValidName(String name);

  boolean isValidAddress(String address);

  boolean isValidPostcode(String postcode);

  boolean isValidPositiveInteger(String number, int maxDigits);

  boolean isValidPositiveInteger(String number);

  boolean isValidAlphanumerical(String value);

  boolean isValidDate(String dateString);

  boolean isValidTime(String timeString);

  boolean isValidAirport(String airport);

  boolean isValidAirport(String airport, int stop);

  boolean isValidFlightId(String flightId);

  boolean isUniquePrimaryKey(String key, String filename);

  String getValidatedInput(Scanner scanner, String prompt, ValidationUtils.InputValidationMethod validator);
}
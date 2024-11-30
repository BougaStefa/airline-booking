package com.bougastefa.app;

import java.util.Scanner;

public class CustomerService {
  private ValidationUtils validationUtils;

  public CustomerService(ValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addCustomer(Scanner scanner) {
    System.out.println("Enter Customer Details:");

    // Validating each column until a correct input is provided
    String customerId = validationUtils.getValidatedInput(scanner, "Customer ID (GR followed by digits): ",
        ValidationUtils::isValidCustomerId);
    String forename = validationUtils.getValidatedInput(scanner, "Forename: ", ValidationUtils::isValidName);
    String surname = validationUtils.getValidatedInput(scanner, "Surname: ", ValidationUtils::isValidName);
    String street = validationUtils.getValidatedInput(scanner, "Street: ", ValidationUtils::isValidAddress);
    String town = validationUtils.getValidatedInput(scanner, "Town: ", ValidationUtils::isValidName);
    String postcode = validationUtils.getValidatedInput(scanner, "Postcode: ", ValidationUtils::isValidPostcode);

    Customer customer = new Customer(customerId, forename, surname, street, town, postcode);
    CSVUtilities.exportToCsv(customer, "Customer.csv");
    System.out.println("Customer added to CSV file!");
  }
}

package com.bougastefa.app;

import java.util.Scanner;

public class CustomerService {
  private ValidationUtils validationUtils;

  public CustomerService(ValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addCustomer(Scanner scanner) {
    System.out.println("Enter Customer Details:");

    String customerId = validationUtils.getValidatedInput(scanner, "Customer ID (GR followed by digits): ",
        validationUtils::isValidCustomerId);
    String forename = validationUtils.getValidatedInput(scanner, "Forename: ", validationUtils::isValidName);
    String surname = validationUtils.getValidatedInput(scanner, "Surname: ", validationUtils::isValidName);
    String street = validationUtils.getValidatedInput(scanner, "Street: ", validationUtils::isValidAddress);
    String town = validationUtils.getValidatedInput(scanner, "Town: ", validationUtils::isValidName);
    String postcode = validationUtils.getValidatedInput(scanner, "Postcode: ", validationUtils::isValidPostcode);

    Customer customer = new Customer(customerId, forename, surname, street, town, postcode);
    CSVUtilities.exportToCsv(customer, "Customer.csv");
    System.out.println("Customer added to CSV file!");
  }
}

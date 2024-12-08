package com.bougastefa.app;

import java.util.Scanner;

//Recieves and handles the validity of Customer values before exporting them to CSV
public class CustomerService {
  private IValidationUtils validationUtils;

  public CustomerService(IValidationUtils validationUtils) {
    this.validationUtils = validationUtils;
  }

  public void addCustomer(Scanner scanner) {
    System.out.println("Enter Customer Details:");
    // Takes the user through providing values for each field. Ensures all required
    // values are valid.
    String customerId = validationUtils.getValidatedInput(scanner, "Customer ID (GR followed by digits): ",
        validationUtils::isValidCustomerId);
    String forename = validationUtils.getValidatedInput(scanner, "Forename: ", validationUtils::isValidName);
    String surname = validationUtils.getValidatedInput(scanner, "Surname: ", validationUtils::isValidName);
    String street = validationUtils.getValidatedInput(scanner, "Street: ", validationUtils::isValidAddress);
    String town = validationUtils.getValidatedInput(scanner, "Town: ", validationUtils::isValidName);
    String postcode = validationUtils.getValidatedInput(scanner, "Postcode: ", validationUtils::isValidPostcode);
    // Create new customer instance and export to CSV
    Customer customer = new Customer(customerId, forename, surname, street, town, postcode);
    CSVUtilities.exportToCsv(customer, "Customer.csv");
    System.out.println("Customer added to CSV file!");
  }
}

package com.bougastefa.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {

  private ValidationUtils validationUtils;
  private CustomerService customerService;
  private Scanner scanner;

  @BeforeEach
  public void setUp() {
    validationUtils = mock(ValidationUtils.class);
    customerService = new CustomerService(validationUtils);
    scanner = mock(Scanner.class);
  }

  @Test
  public void testAddCustomer() {
    when(validationUtils.getValidatedInput(scanner, anyString(), any())).thenReturn("GR123", "John", "Doe",
        "123 Street", "Townsville", "12345");

    customerService.addCustomer(scanner);

    verify(validationUtils).getValidatedInput(scanner, "Customer ID (GR followed by digits): ",
        validationUtils::isValidCustomerId);
    verify(validationUtils).getValidatedInput(scanner, "Forename: ", validationUtils::isValidName);
    verify(validationUtils).getValidatedInput(scanner, "Surname: ", validationUtils::isValidName);
    verify(validationUtils).getValidatedInput(scanner, "Street: ", validationUtils::isValidAddress);
    verify(validationUtils).getValidatedInput(scanner, "Town: ", validationUtils::isValidName);
    verify(validationUtils).getValidatedInput(scanner, "Postcode: ", validationUtils::isValidPostcode);
  }
}

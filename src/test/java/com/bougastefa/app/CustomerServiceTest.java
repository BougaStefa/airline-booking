package com.bougastefa.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class CustomerServiceTest {
  private IValidationUtils validationUtils;
  private CustomerService customerService;

  @BeforeEach
  public void setUp() {
    validationUtils = mock(IValidationUtils.class);
    customerService = new CustomerService(validationUtils);
  }

  @Test
  public void testAddCustomer() {
    // Create a Scanner with simulated input
    String simulatedInput = "GR123\nJohn\nDoe\n123 Street\nTownsville\n12345\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    Scanner scanner = new Scanner(System.in);

    // Set up the mock behavior
    when(validationUtils.getValidatedInput(any(Scanner.class), anyString(), any())).thenReturn("GR123", "John", "Doe",
        "123 Street", "Townsville", "12345");

    // Execute the method
    customerService.addCustomer(scanner);

    // Verify the interactions
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Customer ID (GR followed by digits): "),
        any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Forename: "),
        any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Surname: "),
        any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Street: "),
        any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Town: "),
        any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Postcode: "),
        any());

    // Reset System.in
    System.setIn(System.in);
  }

  @Test
  public void testAddCustomer_ValidationFailure() {
    Scanner scanner = new Scanner(new ByteArrayInputStream("invalid\n".getBytes()));
    when(validationUtils.getValidatedInput(any(), anyString(), any()))
        .thenThrow(new IllegalArgumentException("Invalid input"));

    assertThrows(IllegalArgumentException.class, () -> {
      customerService.addCustomer(scanner);
    });
  }
}

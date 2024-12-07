package com.bougastefa.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class BookingServiceTest {
  private IValidationUtils validationUtils;
  private BookingService bookingService;

  @BeforeEach
  public void setUp() {
    validationUtils = mock(IValidationUtils.class);
    bookingService = new BookingService(validationUtils);
  }

  @Test
  public void testAddBooking() {
    // Create a Scanner with simulated input
    String simulatedInput = "1234\n2\n1\n1\nGR123\nFL456\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    Scanner scanner = new Scanner(System.in);

    // Set up the mock behavior
    when(validationUtils.getValidatedInput(any(Scanner.class), anyString(), any()))
        .thenReturn("B123", "2", "1", "1", "GR123", "FL456");

    // Execute the method
    bookingService.addBooking(scanner);

    // Verify the interactions
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Booking ID: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Adult Tickets: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Child Tickets: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Concession Tickets: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Customer ID: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Flight ID: "), any());

    // Reset System.in
    System.setIn(System.in);
  }
}

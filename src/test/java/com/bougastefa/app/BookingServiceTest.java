package com.bougastefa.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BookingServiceTest {
  private IValidationUtils validationUtils;
  private BookingService bookingService;
  private final String[] TEST_FILES = { "Booking.csv" };

  @BeforeEach
  public void setUp() {
    validationUtils = mock(IValidationUtils.class);
    bookingService = new BookingService(validationUtils);
  }

  @AfterEach
  void cleanup() {
    for (String file : TEST_FILES) {
      try {
        Files.deleteIfExists(Paths.get(file));
      } catch (IOException e) {
        System.out.println("Failed to delete test file: " + e.getMessage());
      }
    }
  }

  @Test
  public void testAddBooking() {
    // Create a Scanner with simulated input
    String simulatedInput = "1234\n2\n1\n1\nGR123\nFL123\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    Scanner scanner = new Scanner(System.in);

    // Set up the mock behavior
    when(validationUtils.getValidatedInput(any(Scanner.class), eq("Booking ID: "), any()))
        .thenReturn("1234");
    when(validationUtils.getValidatedInput(any(Scanner.class), eq("Adult Tickets: "), any()))
        .thenReturn("2");
    when(validationUtils.getValidatedInput(any(Scanner.class), eq("Child Tickets: "), any()))
        .thenReturn("1");
    when(validationUtils.getValidatedInput(any(Scanner.class), eq("Concession Tickets: "), any()))
        .thenReturn("1");
    when(validationUtils.getValidatedTicketCombination(any(Scanner.class), eq("2"), eq("1"), eq("1")))
        .thenReturn("Y");
    when(validationUtils.getValidatedInput(any(Scanner.class), eq("Customer ID: "), any()))
        .thenReturn("GR123");
    when(validationUtils.getValidatedInput(any(Scanner.class), eq("Flight ID: "), any()))
        .thenReturn("FL123");

    // Execute the method
    bookingService.addBooking(scanner);

    // Verify the interactions
    verify(validationUtils).getValidatedTicketCombination(any(), eq("2"), eq("1"), eq("1"));

    // Reset System.in
    System.setIn(System.in);
  }

  @Test
  public void testAddBooking_InvalidCustomerId() {
    Scanner scanner = new Scanner(new ByteArrayInputStream("1234\n2\n1\n1\ninvalid\n".getBytes()));

    when(validationUtils.getValidatedInput(any(), eq("Booking ID: "), any())).thenReturn("1234");
    when(validationUtils.getValidatedInput(any(), eq("Adult Tickets: "), any())).thenReturn("2");
    when(validationUtils.getValidatedInput(any(), eq("Child Tickets: "), any())).thenReturn("1");
    when(validationUtils.getValidatedInput(any(), eq("Concession Tickets: "), any())).thenReturn("1");
    when(validationUtils.getValidatedTicketCombination(any(), eq("2"), eq("1"), eq("1")))
        .thenReturn("Y");
    when(validationUtils.getValidatedInput(any(), eq("Customer ID: "), any()))
        .thenThrow(new IllegalArgumentException("Invalid customer ID"));

    assertThrows(IllegalArgumentException.class, () -> {
      bookingService.addBooking(scanner);
    });
  }

  @Test
  public void testAddBooking_InvalidFlightId() {
    Scanner scanner = new Scanner(new ByteArrayInputStream("1234\n2\n1\n1\nGR123\ninvalid\n".getBytes()));

    when(validationUtils.getValidatedInput(any(), eq("Booking ID: "), any())).thenReturn("1234");
    when(validationUtils.getValidatedInput(any(), eq("Adult Tickets: "), any())).thenReturn("2");
    when(validationUtils.getValidatedInput(any(), eq("Child Tickets: "), any())).thenReturn("1");
    when(validationUtils.getValidatedInput(any(), eq("Concession Tickets: "), any())).thenReturn("1");
    when(validationUtils.getValidatedTicketCombination(any(), eq("2"), eq("1"), eq("1")))
        .thenReturn("Y");
    when(validationUtils.getValidatedInput(any(), eq("Customer ID: "), any())).thenReturn("GR123");
    when(validationUtils.getValidatedInput(any(), eq("Flight ID: "), any()))
        .thenThrow(new IllegalArgumentException("Invalid flight ID"));

    assertThrows(IllegalArgumentException.class, () -> {
      bookingService.addBooking(scanner);
    });
  }
}

package com.bougastefa.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FlightServiceTest {
  private IValidationUtils validationUtils;
  private FlightService flightService;
  private final String testfile = "Flight.csv";

  @BeforeEach
  public void setUp() {
    validationUtils = mock(IValidationUtils.class);
    flightService = new FlightService(validationUtils);
  }

  @AfterEach
  void cleanup() {
    try {
      Files.deleteIfExists(Paths.get(testfile));
    } catch (IOException e) {
      System.out.println("Failed to delete test file: " + e.getMessage());
    }
  }

  @Test
  public void testAddFlight() {
    // Create a Scanner with simulated input
    String simulatedInput = "FL123\n2024-12-07\n2024-12-08\n20:40:00\n22:40:00\n200\n0101\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    Scanner scanner = new Scanner(System.in);

    // Set up the mock behavior
    when(validationUtils.getValidatedInput(any(Scanner.class), anyString(), any()))
        .thenReturn("FL123", "2024-12-07", "2024-12-08", "20:40:00", "22:40:00", "200", "R101");

    // Execute the method
    flightService.addFlight(scanner);

    // Verify the interactions
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Flight ID: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Departure Date: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Arrival Date: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Departure Time: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Arrival Time: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Capacity: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Route ID: "), any());

    // Reset System.in
    System.setIn(System.in);
  }

  @Test
  public void testAddFlight_InvalidDateFormat() {
    Scanner scanner = new Scanner(new ByteArrayInputStream("FL123\n2024/12/07\n".getBytes()));
    when(validationUtils.getValidatedInput(any(), eq("Flight ID: "), any())).thenReturn("FL123");
    when(validationUtils.getValidatedInput(any(), eq("Departure Date: "), any()))
        .thenThrow(new IllegalArgumentException("Invalid date format"));

    assertThrows(IllegalArgumentException.class, () -> {
      flightService.addFlight(scanner);
    });
  }
}

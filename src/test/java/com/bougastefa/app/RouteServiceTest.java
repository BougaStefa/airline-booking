package com.bougastefa.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class RouteServiceTest {
  private IValidationUtils validationUtils;
  private RouteService routeService;

  private final String testfile = "Route.csv";

  @BeforeEach
  public void setUp() {
    validationUtils = mock(IValidationUtils.class);
    routeService = new RouteService(validationUtils);
  }

  @AfterEach // Add this cleanup method
  void cleanup() {
    try {
      Files.deleteIfExists(Paths.get(testfile));

    } catch (IOException e) {
      System.out.println("Failed to delete test file: " + e.getMessage());
    }
  }

  @Test
  public void testAddRoute() {
    // Create a Scanner with simulated input
    String simulatedInput = "0123\nLAX\nJFK\nORD\nDFW\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    Scanner scanner = new Scanner(System.in);

    // Set up the mock behavior
    when(validationUtils.getValidatedInput(any(Scanner.class), anyString(), any()))
        .thenReturn("0123", "LAX", "JFK", "ORD", "DFW");

    // Execute the method
    routeService.addRoute(scanner);

    // Verify the interactions
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Route ID: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Depart From: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Arrive At: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Mid Stop One: "), any());
    verify(validationUtils).getValidatedInput(any(Scanner.class), eq("Mid Stop Two: "), any());

    // Reset System.in
    System.setIn(System.in);
  }

  @Test
  public void testAddRoute_InvalidAirportCode() {
    Scanner scanner = new Scanner(new ByteArrayInputStream("0101\nINVALID\n".getBytes()));
    when(validationUtils.getValidatedInput(any(), eq("Route ID: "), any())).thenReturn("0101");
    when(validationUtils.getValidatedInput(any(), eq("Depart From: "), any()))
        .thenThrow(new IllegalArgumentException("Invalid airport code"));

    assertThrows(IllegalArgumentException.class, () -> {
      routeService.addRoute(scanner);
    });
  }
}

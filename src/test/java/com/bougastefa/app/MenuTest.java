package com.bougastefa.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuTest {
  private IValidationUtils validationUtils;
  private Menu menu;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    validationUtils = mock(IValidationUtils.class);
    outputStreamCaptor.reset();
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  private void setupMenuWithInput(String input) {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    menu = new Menu(validationUtils);
  }

  @Test
  void testAddCustomerOption() {
    setupMenuWithInput("1\nn\n5\n");

    // Setup validation mock with exact prompt texts
    when(validationUtils.getValidatedInput(any(), eq("Customer ID (GR followed by digits): "), any()))
        .thenReturn("GR001");
    when(validationUtils.getValidatedInput(any(), eq("Forename: "), any())).thenReturn("John");
    when(validationUtils.getValidatedInput(any(), eq("Surname: "), any())).thenReturn("Doe");
    when(validationUtils.getValidatedInput(any(), eq("Street: "), any())).thenReturn("123 Main St");
    when(validationUtils.getValidatedInput(any(), eq("Town: "), any())).thenReturn("London");
    when(validationUtils.getValidatedInput(any(), eq("Postcode: "), any())).thenReturn("AB12 3CD");

    menu.displayMenu();

    // Verify with exact prompt texts
    verify(validationUtils).getValidatedInput(any(), eq("Customer ID (GR followed by digits): "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Forename: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Surname: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Street: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Town: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Postcode: "), any());
  }

  @Test
  void testAddRouteOption() {
    setupMenuWithInput("4\nn\n5\n");

    // Setup validation mock with exact prompt texts
    when(validationUtils.getValidatedInput(any(), eq("Route ID: "), any())).thenReturn("RT001");
    when(validationUtils.getValidatedInput(any(), eq("Depart From: "), any())).thenReturn("London");
    when(validationUtils.getValidatedInput(any(), eq("Arrive At: "), any())).thenReturn("Paris");
    when(validationUtils.getValidatedInput(any(), eq("Mid Stop One: "), any())).thenReturn("");
    when(validationUtils.getValidatedInput(any(), eq("Mid Stop Two: "), any())).thenReturn("");

    menu.displayMenu();

    // Verify with exact prompt texts
    verify(validationUtils).getValidatedInput(any(), eq("Route ID: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Depart From: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Arrive At: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Mid Stop One: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Mid Stop Two: "), any());
  }

  @Test
  void testAddFlightOption() {
    setupMenuWithInput("2\nn\n5\n");

    // Setup validation mock with exact prompt texts from FlightService
    when(validationUtils.getValidatedInput(any(), eq("Flight ID: "), any())).thenReturn("FL001");
    when(validationUtils.getValidatedInput(any(), eq("Departure Date: "), any())).thenReturn("2024-12-07");
    when(validationUtils.getValidatedInput(any(), eq("Arrival Date: "), any())).thenReturn("2024-12-07");
    when(validationUtils.getValidatedInput(any(), eq("Departure Time: "), any())).thenReturn("10:00");
    when(validationUtils.getValidatedInput(any(), eq("Arrival Time: "), any())).thenReturn("12:00");
    when(validationUtils.getValidatedInput(any(), eq("Capacity: "), any())).thenReturn("100");
    when(validationUtils.getValidatedInput(any(), eq("Route ID: "), any())).thenReturn("RT001");

    menu.displayMenu();

    // Verify with exact prompt texts
    verify(validationUtils).getValidatedInput(any(), eq("Flight ID: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Departure Date: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Arrival Date: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Departure Time: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Arrival Time: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Capacity: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Route ID: "), any());
  }

  @Test
  void testAddBookingOption() {
    setupMenuWithInput("3\nn\n5\n");

    when(validationUtils.getValidatedInput(any(), eq("Booking ID: "), any())).thenReturn("BK001");
    when(validationUtils.getValidatedInput(any(), eq("Adult Tickets: "), any())).thenReturn("1");
    when(validationUtils.getValidatedInput(any(), eq("Child Tickets: "), any())).thenReturn("0");
    when(validationUtils.getValidatedInput(any(), eq("Concession Tickets: "), any())).thenReturn("0");
    when(validationUtils.getValidatedTicketCombination(any(), eq("1"), eq("0"), eq("0"))).thenReturn("Y");
    when(validationUtils.getValidatedInput(any(), eq("Customer ID: "), any())).thenReturn("GR001");
    when(validationUtils.getValidatedInput(any(), eq("Flight ID: "), any())).thenReturn("FL001");

    menu.displayMenu();

    verify(validationUtils).getValidatedInput(any(), eq("Booking ID: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Adult Tickets: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Child Tickets: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Concession Tickets: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Customer ID: "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Flight ID: "), any());
  }

  @Test
  void testExitOption() {
    setupMenuWithInput("5\n");
    menu.displayMenu();
    assertTrue(outputStreamCaptor.toString().contains("Exiting"));
  }

  @Test
  void testInvalidOption() {
    setupMenuWithInput("7\n5\n");
    menu.displayMenu();
    assertTrue(outputStreamCaptor.toString().contains("Invalid choice"));
  }

  @Test
  void testMultipleEntriesInOneSession() {
    setupMenuWithInput("1\ny\n4\nn\n5\n");

    // Setup customer validation
    when(validationUtils.getValidatedInput(any(), eq("Customer ID (GR followed by digits): "), any()))
        .thenReturn("GR001");
    when(validationUtils.getValidatedInput(any(), eq("Forename: "), any())).thenReturn("John");
    when(validationUtils.getValidatedInput(any(), eq("Surname: "), any())).thenReturn("Doe");
    when(validationUtils.getValidatedInput(any(), eq("Street: "), any())).thenReturn("123 Main St");
    when(validationUtils.getValidatedInput(any(), eq("Town: "), any())).thenReturn("London");
    when(validationUtils.getValidatedInput(any(), eq("Postcode: "), any())).thenReturn("AB12 3CD");

    // Setup route validation
    when(validationUtils.getValidatedInput(any(), eq("Route ID: "), any())).thenReturn("RT001");
    when(validationUtils.getValidatedInput(any(), eq("Depart From: "), any())).thenReturn("London");
    when(validationUtils.getValidatedInput(any(), eq("Arrive At: "), any())).thenReturn("Paris");
    when(validationUtils.getValidatedInput(any(), eq("Mid Stop One: "), any())).thenReturn("");
    when(validationUtils.getValidatedInput(any(), eq("Mid Stop Two: "), any())).thenReturn("");

    menu.displayMenu();

    // Verify both customer and route validations were called
    verify(validationUtils).getValidatedInput(any(), eq("Customer ID (GR followed by digits): "), any());
    verify(validationUtils).getValidatedInput(any(), eq("Route ID: "), any());
  }
}

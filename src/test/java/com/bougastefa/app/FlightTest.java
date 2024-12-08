package com.bougastefa.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {
  // Constructing new objects and testing its getters
  @Test
  void testConstructorAndGetters() {
    String flightId = "FL111";
    String departureDate = "2024-11-24";
    String departureTime = "15:00:00";
    String routeId = "111";
    String arrivalDate = "2024-11-25";
    String arrivalTime = "12:00:00";
    String capacity = "300";

    Flight flight = new Flight(flightId, departureDate, departureTime, routeId, arrivalDate, arrivalTime, capacity);

    // Assertions
    assertEquals("FL111", flight.getFlightId(), "FlightId should match.");
    assertEquals("2024-11-24", flight.getDepartureDate(), "Departure date should match.");
    assertEquals("15:00:00", flight.getDepartureTime(), "Departure time should match.");
    assertEquals("111", flight.getRouteId(), "RouteId should match.");
    assertEquals("2024-11-25", flight.getArrivalDate(), "Arrival date should match.");
    assertEquals("12:00:00", flight.getArrivalTime(), "Arrival time should match.");
    assertEquals("300", flight.getCapacity(), "Capacity should match.");
  }

  // Altering object values to test the setters
  @Test
  void testSeetters() {
    Flight flight = new Flight("FL111", "2024-11-24", "15:00:00", "111", "2024-11-25", "12:00:00", "300");

    flight.setFlightId("FL222");
    flight.setDepartureDate("2025-11-11");
    flight.setDepartureTime("16:00:00");
    flight.setRouteId("222");
    flight.setArrivalDate("2025-12-12");
    flight.setArrivalTime("17:00:00");
    flight.setCapacity("666");

    // Assertions
    assertEquals("FL222", flight.getFlightId(), "FlightId should match after setting.");
    assertEquals("2025-11-11", flight.getDepartureDate(), "Departure date should match after setting");
    assertEquals("16:00:00", flight.getDepartureTime(), "Departure time should match after setting");
    assertEquals("222", flight.getRouteId(), "RouteId should match after setting");
    assertEquals("2025-12-12", flight.getArrivalDate(), "Arrival date should match after setting");
    assertEquals("17:00:00", flight.getArrivalTime(), "Arrival time should match after setting");
    assertEquals("666", flight.getCapacity(), "Capacity should match after setting");
  }

  // Ensure that even if empty input is provided no elements are null
  @Test
  void testDefaultValuesNotNull() {
    Flight flight = new Flight("", "", "", "", "", "", "");

    // Assertions
    assertNotNull(flight.getFlightId(), "Customer ID should not be null");
    assertNotNull(flight.getDepartureTime(), "Forename should not be null");
    assertNotNull(flight.getDepartureTime(), "Surname should not be null");
    assertNotNull(flight.getRouteId(), "Street should not be null");
    assertNotNull(flight.getArrivalDate(), "Town should not be null");
    assertNotNull(flight.getArrivalTime(), "Postcode should not be null");
    assertNotNull(flight.getCapacity(), "Capacity should not be null");
  }
}

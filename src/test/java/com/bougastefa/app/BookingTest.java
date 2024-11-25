package com.bougastefa.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

  @Test
  void testConstructorAndGetters() {
    String bookingId = "1111";
    String customerId = "GR123";
    String adultTicket = "1";
    String childTicket = "1";
    String concessionTicket = "1";
    String flightId = "FL123";

    Booking booking = new Booking(bookingId, customerId, adultTicket, childTicket, concessionTicket, flightId);

    // Assertions
    assertEquals(bookingId, booking.getBookingId(), "BookingId should match.");
    assertEquals(customerId, booking.getCustomerId(), "CustomerId should match.");
    assertEquals(adultTicket, booking.getAdultTicket(), "Adult ticket should match.");
    assertEquals(childTicket, booking.getChildTicket(), "Child ticket should match.");
    assertEquals(concessionTicket, booking.getConcessionTicket(), "Concession ticket should match.");
    assertEquals(flightId, booking.getFlightId(), "FlightId should match.");
  }

  @Test
  void testSetters() {
    Booking booking = new Booking("111", "GR123", "1", "1", "1", "FL123");

    booking.setBookingId("222");
    booking.setCustomerId("GR456");
    booking.setAdultTicket("2");
    booking.setChildTicket("2");
    booking.setConcessionTicket("2");
    booking.setFlightId("FL456");

    assertEquals("222", booking.getBookingId(), "BookingId should match after setting.");
    assertEquals("GR456", booking.getCustomerId(), "CustomerId should match after setting.");
    assertEquals("2", booking.getAdultTicket(), "Adult ticket should match after setting.");
    assertEquals("2", booking.getChildTicket(), "Child ticket should match after setting.");
    assertEquals("2", booking.getConcessionTicket(), "Concession ticket should match after setting.");
    assertEquals("FL456", booking.getFlightId(), "FlightId should match after setting.");
  }

  @Test
  void testDefaultValuesNotNull() {
    Booking booking = new Booking("", "", "", "", "", "");

    assertNotNull(booking.getBookingId(), "BookingId should not be null");
    assertNotNull(booking.getCustomerId(), "CustomerId should not be null");
    assertNotNull(booking.getAdultTicket(), "Adult ticket should not be null");
    assertNotNull(booking.getChildTicket(), "Child ticket should not be null");
    assertNotNull(booking.getConcessionTicket(), "Concession ticket should not be null");
    assertNotNull(booking.getFlightId(), "FlightId should not be null");
  }
}

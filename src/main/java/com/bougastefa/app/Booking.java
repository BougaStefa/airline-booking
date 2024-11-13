package com.bougastefa.app;

public class Booking {
  private String bookingId;
  private String customerId;
  private int adultTicket;
  private int childTicket;
  private int concessionTicket;
  private String flightId;

  // Constructor
  public Booking(String bookingId, String customerId, int adultTicket, int childTicket, int concessionTicket,
      String flightId) {
    this.bookingId = bookingId;
    this.customerId = customerId;
    this.adultTicket = adultTicket;
    this.childTicket = childTicket;
    this.concessionTicket = concessionTicket;
    this.flightId = flightId;
  }

  // Setters & Getters
  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void setAdultTicket(int adultTicket) {
    this.adultTicket = adultTicket;
  }

  public void setChildTicket(int childTicket) {
    this.childTicket = childTicket;
  }

  public void setConcessionTicket(int concessionTicket) {
    this.concessionTicket = concessionTicket;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getBookingId() {
    return bookingId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public int getAdultTicket() {
    return adultTicket;
  }

  public int getChildTicket() {
    return childTicket;
  }

  public int getConcessionTicket() {
    return concessionTicket;
  }

  public String getFlightId() {
    return flightId;
  }
  // !TODO: Add method to export a booking entry into a csv file.
}

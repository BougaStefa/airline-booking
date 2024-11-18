package com.bougastefa.app;

public class Booking {
  private String bookingId;
  private String customerId;
  private String adultTicket;
  private String childTicket;
  private String concessionTicket;
  private String flightId;

  // Constructor
  public Booking(String bookingId, String customerId, String adultTicket, String childTicket, String concessionTicket,
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

  public void setAdultTicket(String adultTicket) {
    this.adultTicket = adultTicket;
  }

  public void setChildTicket(String childTicket) {
    this.childTicket = childTicket;
  }

  public void setConcessionTicket(String concessionTicket) {
    this.concessionTicket = concessionTicket;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  @InputOrder(1)
  public String getBookingId() {
    return bookingId;
  }

  @InputOrder(5)
  public String getCustomerId() {
    return customerId;
  }

  @InputOrder(2)
  public String getAdultTicket() {
    return adultTicket;
  }

  @InputOrder(3)
  public String getChildTicket() {
    return childTicket;
  }

  @InputOrder(4)
  public String getConcessionTicket() {
    return concessionTicket;
  }

  @InputOrder(6)
  public String getFlightId() {
    return flightId;
  }
}

package com.bougastefa.app;

public class Flight {
  private String flightId;
  private String departureDate;
  private String departureTime;
  private String routeId;
  private String arrivalDate;
  private String arrivalTime;
  private String capacity;

  // Constructor
  public Flight(String flightId, String departureDate, String departureTime, String routeId,
      String arrivalDate, String arrivalTime, String capacity) {
    this.flightId = flightId;
    this.departureTime = departureTime;
    this.departureDate = departureDate;
    this.routeId = routeId;
    this.arrivalTime = arrivalTime;
    this.arrivalDate = arrivalDate;
    this.capacity = capacity;
  }

  // Getters & Setters
  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = departureDate;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  @InputOrder(1)
  public String getFlightId() {
    return flightId;
  }

  @InputOrder(2)
  public String getDepartureDate() {
    return departureDate;
  }

  @InputOrder(3)
  public String getDepartureTime() {
    return departureTime;
  }

  @InputOrder(7)
  public String getRouteId() {
    return routeId;
  }

  @InputOrder(4)
  public String getArrivalDate() {
    return arrivalDate;
  }

  @InputOrder(5)
  public String getArrivalTime() {
    return arrivalTime;
  }

  @InputOrder(6)
  public String getCapacity() {
    return capacity;
  }
}

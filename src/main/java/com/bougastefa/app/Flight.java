package com.bougastefa.app;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
  private String flightId;
  private LocalDate departureDate;
  private LocalTime departureTime;
  private String routeId;
  private LocalDate arrivalDate;
  private LocalTime arrivalTime;
  private String capacity;

  // Constructor
  public Flight(String flightId, LocalDate departureDate, LocalTime departureTime, String routeId,
      LocalDate arrivalDate, LocalTime arrivalTime, String capacity) {
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

  public void setDepartureTime(LocalTime departureTime) {
    this.departureTime = departureTime;
  }

  public void setDepartureDate(LocalDate departureDate) {
    this.departureDate = departureDate;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

  public void setArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public void setArrivalTime(LocalTime arrivalTime) {
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
  public LocalDate getDepartureDate() {
    return departureDate;
  }

  @InputOrder(3)
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  @InputOrder(7)
  public String getRouteId() {
    return routeId;
  }

  @InputOrder(4)
  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  @InputOrder(5)
  public LocalTime getArrivalTime() {
    return arrivalTime;
  }

  @InputOrder(6)
  public String getCapacity() {
    return capacity;
  }
}

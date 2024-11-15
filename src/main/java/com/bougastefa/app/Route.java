package com.bougastefa.app;

public class Route {
  private String routeId;
  private String departFrom;
  private String arriveAt;
  private String midStopOne;
  private String midStopTwo;

  // Constructor
  public Route(String routeId, String departFrom, String arriveAt, String midStopOne, String midStopTwo) {
    this.routeId = routeId;
    this.departFrom = departFrom;
    this.arriveAt = arriveAt;
    this.midStopOne = midStopOne;
    this.midStopTwo = midStopTwo;
  }

  // Getters & Setters
  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

  public void setDepartFrom(String departFrom) {
    this.departFrom = departFrom;
  }

  public void setArriveAt(String arriveAt) {
    this.arriveAt = arriveAt;
  }

  public void setMidStopOne(String midStopOne) {
    this.midStopOne = midStopOne;
  }

  public void setMidStopTwo(String midStopTwo) {
    this.midStopTwo = midStopTwo;
  }

  public String getRouteId() {
    return routeId;
  }

  public String getDepartFrom() {
    return departFrom;
  }

  public String getArriveAt() {
    return arriveAt;
  }

  public String getMidStopOne() {
    return midStopOne;
  }

  public String getMidStopTwo() {
    return midStopTwo;
  }
}

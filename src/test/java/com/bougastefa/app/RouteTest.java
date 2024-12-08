package com.bougastefa.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {
  // Constructing new objects and testing its getters
  @Test
  void testConstructorAndGetters() {
    String routeId = "111";
    String departFrom = "LAX";
    String arriveAt = "JFK";
    String midStopOne = "ORD";
    String midStopTwo = "DFW";

    Route route = new Route(routeId, departFrom, arriveAt, midStopOne, midStopTwo);

    // Asserts
    assertEquals("111", route.getRouteId(), "Route ID should match");
    assertEquals("LAX", route.getDepartFrom(), "Departure Location should match");
    assertEquals("JFK", route.getArriveAt(), "Arrival Location should match");
    assertEquals("ORD", route.getMidStopOne(), "First Mid Stop should match");
    assertEquals("DFW", route.getMidStopTwo(), "Second Mid Stop should match");
  }

  // Altering object values to test the setters
  @Test
  void testSetters() {
    Route route = new Route("111", "LAX", "JFK", "ORD", "DFW");

    route.setRouteId("222");
    route.setDepartFrom("JFK");
    route.setArriveAt("LAX");
    route.setMidStopOne("DFW");
    route.setMidStopTwo("ORD");

    // Asserts
    assertEquals("222", route.getRouteId(), "Route ID should match after setting.");
    assertEquals("JFK", route.getDepartFrom(), "Departure Location should match after setting.");
    assertEquals("LAX", route.getArriveAt(), "Arrival Location should match after setting.");
    assertEquals("DFW", route.getMidStopOne(), "First Mid Stop should match after setting.");
    assertEquals("ORD", route.getMidStopTwo(), "Second Mid Stop should match after setting.");
  }
}

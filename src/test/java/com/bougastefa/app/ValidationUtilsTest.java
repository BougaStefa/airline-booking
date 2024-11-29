package com.bougastefa.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidationUtilsTest {
  @Test
  void testIsNullOrEmpty() {
    assertTrue(ValidationUtils.isNullOrEmpty(null));
    assertTrue(ValidationUtils.isNullOrEmpty(""));
    assertTrue(ValidationUtils.isNullOrEmpty(" "));
    assertFalse(ValidationUtils.isNullOrEmpty("a"));
    assertFalse(ValidationUtils.isNullOrEmpty("ab"));
  }

  @Test
  void testIsValidCustomerId() {
    assertTrue(ValidationUtils.isValidCustomerId("GR123"));
    assertFalse(ValidationUtils.isValidCustomerId("GR"));
    assertFalse(ValidationUtils.isValidCustomerId("123"));
    assertFalse(ValidationUtils.isValidCustomerId(""));
    assertFalse(ValidationUtils.isValidCustomerId(null));
  }

  @Test
  void testIsValidName() {
    assertTrue(ValidationUtils.isValidName("John"));
    assertTrue(ValidationUtils.isValidName("John Doe"));
    assertTrue(ValidationUtils.isValidName("John-Doe"));
    assertTrue(ValidationUtils.isValidName("John Doe-Smith"));
    assertFalse(ValidationUtils.isValidName("J"));
    assertFalse(ValidationUtils.isValidName("J1"));
    assertFalse(ValidationUtils.isValidName("J1D"));
    assertFalse(ValidationUtils.isValidName("J1Doe"));
    assertFalse(ValidationUtils.isValidName(""));
    assertFalse(ValidationUtils.isValidName(null));
  }

  @Test
  void testIsValidAddress() {
    assertTrue(ValidationUtils.isValidAddress("123 Street"));
    assertTrue(ValidationUtils.isValidAddress("123 Street, Town"));
    assertTrue(ValidationUtils.isValidAddress("123 Street, Town, Postcode"));
    assertTrue(ValidationUtils.isValidAddress("123 Street, Town, Postcode, Country"));
    assertFalse(ValidationUtils.isValidAddress("12"));
    assertFalse(ValidationUtils.isValidAddress("12 S"));
    assertTrue(ValidationUtils.isValidAddress("12 St"));
    assertFalse(ValidationUtils.isValidAddress(""));
    assertFalse(ValidationUtils.isValidAddress(null));
  }

  @Test
  void testIsValidPostcode() {
    assertTrue(ValidationUtils.isValidPostcode("AB1 1AB"));
    assertTrue(ValidationUtils.isValidPostcode("AB11AB"));
    assertTrue(ValidationUtils.isValidPostcode("AB1 1AB"));
    assertTrue(ValidationUtils.isValidPostcode("AB1 1AB"));
    assertFalse(ValidationUtils.isValidPostcode("AB"));
    assertFalse(ValidationUtils.isValidPostcode(""));
    assertFalse(ValidationUtils.isValidPostcode(null));
  }

  @Test
  void testIsValidPositiveInteger() {
    assertTrue(ValidationUtils.isValidPositiveInteger("1"));
    assertTrue(ValidationUtils.isValidPositiveInteger("123"));
    assertTrue(ValidationUtils.isValidPositiveInteger("123", 3));
    assertTrue(ValidationUtils.isValidPositiveInteger("123", 4));
    assertTrue(ValidationUtils.isValidPositiveInteger("123", 5));
    assertFalse(ValidationUtils.isValidPositiveInteger("123", 2));
    assertFalse(ValidationUtils.isValidPositiveInteger("123", 1));
    assertFalse(ValidationUtils.isValidPositiveInteger("123", 0));
    assertFalse(ValidationUtils.isValidPositiveInteger("123", -1));
    assertFalse(ValidationUtils.isValidPositiveInteger(""));
    assertFalse(ValidationUtils.isValidPositiveInteger(null));
  }

  @Test
  void testIsValidAlphanumerical() {
    assertTrue(ValidationUtils.isValidAlphanumerical("a"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9k"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9k0"));
    assertTrue(ValidationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9k0l"));
    assertFalse(ValidationUtils.isValidAlphanumerical(""));
    assertFalse(ValidationUtils.isValidAlphanumerical(null));
  }

  @Test
  void testIsValidDate() {
    assertTrue(ValidationUtils.isValidDate("2021-01-01"));
    assertTrue(ValidationUtils.isValidDate("2021-01-31"));
    assertTrue(ValidationUtils.isValidDate("2021-02-28"));
    assertTrue(ValidationUtils.isValidDate("2021-03-31"));
    assertTrue(ValidationUtils.isValidDate("2021-04-30"));
    assertTrue(ValidationUtils.isValidDate("2021-05-31"));
    assertTrue(ValidationUtils.isValidDate("2021-06-30"));
    assertTrue(ValidationUtils.isValidDate("2021-07-31"));
    assertTrue(ValidationUtils.isValidDate("2021-08-31"));
    assertTrue(ValidationUtils.isValidDate("2021-09-30"));
    assertTrue(ValidationUtils.isValidDate("2021-10-31"));
    assertTrue(ValidationUtils.isValidDate("2021-11-30"));
    assertTrue(ValidationUtils.isValidDate("2021-12-31"));
    assertFalse(ValidationUtils.isValidDate("2021-00-01"));
    assertFalse(ValidationUtils.isValidDate("2021-13-01"));
    assertFalse(ValidationUtils.isValidDate("2021-01-00"));
    assertFalse(ValidationUtils.isValidDate("2021-01-32"));
    assertFalse(ValidationUtils.isValidDate("2021-02-29"));
    assertFalse(ValidationUtils.isValidDate("2021-04-31"));
    assertFalse(ValidationUtils.isValidDate("2021-06-31"));
    assertFalse(ValidationUtils.isValidDate("2021-09-31"));
    assertFalse(ValidationUtils.isValidDate("2021-11-31"));
    assertFalse(ValidationUtils.isValidDate("2222-01-01"));
    assertFalse(ValidationUtils.isValidDate("1821-01-01"));
  }

  @Test
  void testIsValidTime() {
    assertTrue(ValidationUtils.isValidTime("00:00:00"));
    assertTrue(ValidationUtils.isValidTime("23:59:00"));
    assertFalse(ValidationUtils.isValidTime("12:00"));
    assertFalse(ValidationUtils.isValidTime("24:00"));
    assertFalse(ValidationUtils.isValidTime("00:60"));
    assertFalse(ValidationUtils.isValidTime("00:0"));
    assertFalse(ValidationUtils.isValidTime("0"));
  }

  @Test
  void testIsValidAirport() {
    assertTrue(ValidationUtils.isValidAirport("LAX"));
    assertTrue(ValidationUtils.isValidAirport("JFK"));
    assertTrue(ValidationUtils.isValidAirport("ORD"));
    assertFalse(ValidationUtils.isValidAirport("LAXX"));
    assertFalse(ValidationUtils.isValidAirport("JFKK"));
    assertFalse(ValidationUtils.isValidAirport("ORDD"));
    assertFalse(ValidationUtils.isValidAirport("L"));
    assertFalse(ValidationUtils.isValidAirport("1"));
    assertFalse(ValidationUtils.isValidAirport(""));
    assertFalse(ValidationUtils.isValidAirport(null));
  }

  @Test
  void testIsValidAirportForStops() {
    assertTrue(ValidationUtils.isValidAirport("LAX", 1));
    assertTrue(ValidationUtils.isValidAirport("JFK", 1));
    assertTrue(ValidationUtils.isValidAirport("ORD", 1));
    assertFalse(ValidationUtils.isValidAirport("LAXX", 2));
    assertFalse(ValidationUtils.isValidAirport("JFKK", 1));
    assertFalse(ValidationUtils.isValidAirport("ORDD", 1));
    assertFalse(ValidationUtils.isValidAirport("L", 1));
    assertFalse(ValidationUtils.isValidAirport("1", 1));
    assertTrue(ValidationUtils.isValidAirport("", 1));
    assertFalse(ValidationUtils.isValidAirport(null, 1));
  }

  @Test
  void testIsValidFlightId() {
    assertTrue(ValidationUtils.isValidFlightId("GR123"));
    assertFalse(ValidationUtils.isValidFlightId("GR"));
    assertFalse(ValidationUtils.isValidFlightId("123"));
    assertFalse(ValidationUtils.isValidFlightId(""));
    assertFalse(ValidationUtils.isValidFlightId(null));
    assertFalse(ValidationUtils.isValidFlightId("GR1234"));
    assertFalse(ValidationUtils.isValidFlightId("GR12"));
  }

  @Test
  public void testIsUniquePrimaryKey_UniqueKey() throws IOException {
    String filename = "testfile.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write("key1,value1\n");
      writer.write("key2,value2\n");
    }
    assertTrue(ValidationUtils.isUniquePrimaryKey("key3", filename));
    Files.delete(Paths.get(filename));
  }

  @Test
  public void testIsUniquePrimaryKey_NonUniqueKey() throws IOException {
    String filename = "testfile.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write("key1,value1\n");
      writer.write("key2,value2\n");
    }
    assertFalse(ValidationUtils.isUniquePrimaryKey("key1", filename));
    Files.delete(Paths.get(filename));
  }

  @Test
  public void testIsUniquePrimaryKey_FileNotFound() {
    assertTrue(ValidationUtils.isUniquePrimaryKey("key1", "nonexistentfile.csv"));
  }
}

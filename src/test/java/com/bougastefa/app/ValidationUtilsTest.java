package com.bougastefa.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidationUtilsTest {
  private ValidationUtils validationUtils;
  private final String TEST_CSV_FILE = "Customer.csv";

  @BeforeEach
  void setUp() {
    validationUtils = new ValidationUtils();
  }

  @AfterEach
  void cleanup() {
    try {
      Files.deleteIfExists(Paths.get(TEST_CSV_FILE));
    } catch (IOException e) {
      System.out.println("Failed to delete test file: " + e.getMessage());
    }
  }

  @Test
  void testIsNullOrEmpty() {
    assertTrue(validationUtils.isNullOrEmpty(null));
    assertTrue(validationUtils.isNullOrEmpty(""));
    assertTrue(validationUtils.isNullOrEmpty(" "));
    assertFalse(validationUtils.isNullOrEmpty("a"));
    assertFalse(validationUtils.isNullOrEmpty("ab"));
  }

  @Test
  void testIsValidCustomerIdFormat() {
    assertTrue(validationUtils.isValidCustomerIdFormat("GR123"));
    assertFalse(validationUtils.isValidCustomerIdFormat("GR"));
    assertFalse(validationUtils.isValidCustomerIdFormat("123"));
    assertFalse(validationUtils.isValidCustomerIdFormat(""));
    assertFalse(validationUtils.isValidCustomerIdFormat(null));
  }

  @Test
  void testIsValidName() {
    assertTrue(validationUtils.isValidName("John"));
    assertTrue(validationUtils.isValidName("John Doe"));
    assertTrue(validationUtils.isValidName("John-Doe"));
    assertTrue(validationUtils.isValidName("John Doe-Smith"));
    assertFalse(validationUtils.isValidName("J"));
    assertFalse(validationUtils.isValidName("J1"));
    assertFalse(validationUtils.isValidName("J1D"));
    assertFalse(validationUtils.isValidName("J1Doe"));
    assertFalse(validationUtils.isValidName(""));
    assertFalse(validationUtils.isValidName(null));
  }

  @Test
  void testIsValidAddress() {
    assertTrue(validationUtils.isValidAddress("123 Street"));
    assertTrue(validationUtils.isValidAddress("123 Street, Town"));
    assertTrue(validationUtils.isValidAddress("123 Street, Town, Postcode"));
    assertTrue(validationUtils.isValidAddress("123 Street, Town, Postcode, Country"));
    assertFalse(validationUtils.isValidAddress("12"));
    assertFalse(validationUtils.isValidAddress("12 S"));
    assertTrue(validationUtils.isValidAddress("12 St"));
    assertFalse(validationUtils.isValidAddress(""));
    assertFalse(validationUtils.isValidAddress(null));
  }

  @Test
  void testIsValidPostcode() {
    assertTrue(validationUtils.isValidPostcode("AB1 1AB"));
    assertTrue(validationUtils.isValidPostcode("AB11AB"));
    assertTrue(validationUtils.isValidPostcode("AB1 1AB"));
    assertTrue(validationUtils.isValidPostcode("AB1 1AB"));
    assertFalse(validationUtils.isValidPostcode("AB"));
    assertFalse(validationUtils.isValidPostcode(""));
    assertFalse(validationUtils.isValidPostcode(null));
  }

  @Test
  void testIsValidPositiveInteger() {
    assertTrue(validationUtils.isValidPositiveInteger("1"));
    assertTrue(validationUtils.isValidPositiveInteger("123"));
    assertTrue(validationUtils.isValidPositiveInteger("123", 3));
    assertTrue(validationUtils.isValidPositiveInteger("123", 4));
    assertTrue(validationUtils.isValidPositiveInteger("123", 5));
    assertFalse(validationUtils.isValidPositiveInteger("123", 2));
    assertFalse(validationUtils.isValidPositiveInteger("123", 1));
    assertFalse(validationUtils.isValidPositiveInteger("123", 0));
    assertFalse(validationUtils.isValidPositiveInteger("123", -1));
    assertFalse(validationUtils.isValidPositiveInteger(""));
    assertFalse(validationUtils.isValidPositiveInteger(null));
  }

  @Test
  void testIsValidAlphanumerical() {
    assertTrue(validationUtils.isValidAlphanumerical("a"));
    assertTrue(validationUtils.isValidAlphanumerical("ab"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9k"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9k0"));
    assertTrue(validationUtils.isValidAlphanumerical("ab1c2d3e4f5g6h7i8j9k0l"));
    assertFalse(validationUtils.isValidAlphanumerical(""));
    assertFalse(validationUtils.isValidAlphanumerical(null));
  }

  @Test
  void testIsValidDate() {
    assertTrue(validationUtils.isValidDate("2021-01-01"));
    assertTrue(validationUtils.isValidDate("2021-01-31"));
    assertTrue(validationUtils.isValidDate("2021-02-28"));
    assertTrue(validationUtils.isValidDate("2021-03-31"));
    assertTrue(validationUtils.isValidDate("2021-04-30"));
    assertTrue(validationUtils.isValidDate("2021-05-31"));
    assertTrue(validationUtils.isValidDate("2021-06-30"));
    assertTrue(validationUtils.isValidDate("2021-07-31"));
    assertTrue(validationUtils.isValidDate("2021-08-31"));
    assertTrue(validationUtils.isValidDate("2021-09-30"));
    assertTrue(validationUtils.isValidDate("2021-10-31"));
    assertTrue(validationUtils.isValidDate("2021-11-30"));
    assertTrue(validationUtils.isValidDate("2021-12-31"));
    assertFalse(validationUtils.isValidDate("2021-00-01"));
    assertFalse(validationUtils.isValidDate("2021-13-01"));
    assertFalse(validationUtils.isValidDate("2021-01-00"));
    assertFalse(validationUtils.isValidDate("2021-01-32"));
    assertFalse(validationUtils.isValidDate("2021-02-29"));
    assertFalse(validationUtils.isValidDate("2021-04-31"));
    assertFalse(validationUtils.isValidDate("2021-06-31"));
    assertFalse(validationUtils.isValidDate("2021-09-31"));
    assertFalse(validationUtils.isValidDate("2021-11-31"));
    assertFalse(validationUtils.isValidDate("2222-01-01"));
    assertFalse(validationUtils.isValidDate("1821-01-01"));
  }

  @Test
  void testIsValidTime() {
    assertTrue(validationUtils.isValidTime("00:00:00"));
    assertTrue(validationUtils.isValidTime("23:59:00"));
    assertFalse(validationUtils.isValidTime("12:00"));
    assertFalse(validationUtils.isValidTime("24:00"));
    assertFalse(validationUtils.isValidTime("00:60"));
    assertFalse(validationUtils.isValidTime("00:0"));
    assertFalse(validationUtils.isValidTime("0"));
  }

  @Test
  void testIsValidAirport() {
    assertTrue(validationUtils.isValidAirport("LAX"));
    assertTrue(validationUtils.isValidAirport("JFK"));
    assertTrue(validationUtils.isValidAirport("ORD"));
    assertFalse(validationUtils.isValidAirport("LAXX"));
    assertFalse(validationUtils.isValidAirport("JFKK"));
    assertFalse(validationUtils.isValidAirport("ORDD"));
    assertFalse(validationUtils.isValidAirport("L"));
    assertFalse(validationUtils.isValidAirport("1"));
    assertFalse(validationUtils.isValidAirport(""));
    assertFalse(validationUtils.isValidAirport(null));
  }

  @Test
  void testIsValidAirportForStops() {
    assertTrue(validationUtils.isValidAirport("LAX", 1));
    assertTrue(validationUtils.isValidAirport("JFK", 1));
    assertTrue(validationUtils.isValidAirport("ORD", 1));
    assertFalse(validationUtils.isValidAirport("LAXX", 2));
    assertFalse(validationUtils.isValidAirport("JFKK", 1));
    assertFalse(validationUtils.isValidAirport("ORDD", 1));
    assertFalse(validationUtils.isValidAirport("L", 1));
    assertFalse(validationUtils.isValidAirport("1", 1));
    assertTrue(validationUtils.isValidAirport("", 1));
    assertFalse(validationUtils.isValidAirport(null, 1));
  }

  @Test
  void testIsValidFlightId() {
    assertTrue(validationUtils.isValidFlightId("GR123"));
    assertFalse(validationUtils.isValidFlightId("GR"));
    assertFalse(validationUtils.isValidFlightId("123"));
    assertFalse(validationUtils.isValidFlightId(""));
    assertFalse(validationUtils.isValidFlightId(null));
    assertFalse(validationUtils.isValidFlightId("GR1234"));
    assertFalse(validationUtils.isValidFlightId("GR12"));
  }

  @Test
  public void testIsUniquePrimaryKey_UniqueKey() throws IOException {
    String filename = "testfile.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write("key1,value1\n");
      writer.write("key2,value2\n");
    }
    assertTrue(validationUtils.isUniquePrimaryKey("key3", filename));
    Files.delete(Paths.get(filename));
  }

  @Test
  public void testIsUniquePrimaryKey_NonUniqueKey() throws IOException {
    String filename = "testfile.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write("key1,value1\n");
      writer.write("key2,value2\n");
    }
    assertFalse(validationUtils.isUniquePrimaryKey("key1", filename));
    Files.delete(Paths.get(filename));
  }

  @Test
  public void testIsUniquePrimaryKey_FileNotFound() {
    assertTrue(validationUtils.isUniquePrimaryKey("key1", "nonexistentfile.csv"));
  }
}

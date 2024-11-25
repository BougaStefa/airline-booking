package com.bougastefa.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
  @Test
  void testConstructorAndGetters() {
    String customerId = "GR123";
    String forename = "forename";
    String surname = "surname";
    String street = "12 Street";
    String town = "Town";
    String postcode = "PP1 1PP";

    Customer customer = new Customer(customerId, forename, surname, street, town, postcode);

    // Asserts
    assertEquals(customerId, customer.getCustomerId(), "Customer ID should match");
    assertEquals(forename, customer.getForename(), "Forename should match");
    assertEquals(surname, customer.getSurname(), "Surname should match");
    assertEquals(street, customer.getStreet(), "Street should match");
    assertEquals(town, customer.getTown(), "Town should match");
    assertEquals(postcode, customer.getPostcode(), "Postcode should match");
  }

  @Test
  void testSetters() {

    Customer customer = new Customer("GR111", "John", "Doe", "123 Streety Street", "Glasgow", "GL1 1GL");

    customer.setCustomerId("GR666");
    customer.setSurname("Sue");
    customer.setForename("Marry");
    customer.setStreet("666 Beast Street");
    customer.setTown("Athens");
    customer.setPostcode("AB1 1BB");

    // Asserts
    assertEquals("GR666", customer.getCustomerId(), "CustomerID should match after setting.");
    assertEquals("Sue", customer.getSurname(), "Surname should match after setting.");
    assertEquals("Marry", customer.getForename(), "Forename should match after setting.");
    assertEquals("666 Beast Street", customer.getStreet(), "Street should match after setting.");
    assertEquals("Athens", customer.getTown(), "Town shoudl match after setting.");
    assertEquals("AB1 1BB", customer.getPostcode(), "Postcode should match after setting.");
  }

  @Test
  void testDefaultValuesNotNull() {
    // Arrange & Act
    Customer customer = new Customer("", "", "", "", "", "");

    // Assert
    assertNotNull(customer.getCustomerId(), "Customer ID should not be null");
    assertNotNull(customer.getForename(), "Forename should not be null");
    assertNotNull(customer.getSurname(), "Surname should not be null");
    assertNotNull(customer.getStreet(), "Street should not be null");
    assertNotNull(customer.getTown(), "Town should not be null");
    assertNotNull(customer.getPostcode(), "Postcode should not be null");
  }
}

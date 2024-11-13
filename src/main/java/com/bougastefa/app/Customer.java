package com.bougastefa.app;

public class Customer {
  private String customerId;
  private String forename;
  private String surname;
  private String street;
  private String town;
  private String postcode;

  // Constructor
  public Customer(String customerId, String forename, String surname, String street, String town, String postcode) {
    this.customerId = customerId;
    this.forename = forename;
    this.surname = surname;
    this.street = street;
    this.town = town;
    this.postcode = postcode;
  }

  // Setters & Getters
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void setForename(String forename) {
    this.forename = forename;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getForename() {
    return forename;
  }

  public String getSurname() {
    return surname;
  }

  public String getStreet() {
    return street;
  }

  public String getTown() {
    return town;
  }

  public String postcode() {
    return postcode;
  }
  // TODO: Create methods to export to a CSV file
}

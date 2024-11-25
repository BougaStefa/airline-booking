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

  @InputOrder(1)
  public String getCustomerId() {
    return customerId;
  }

  @InputOrder(2)
  public String getForename() {
    return forename;
  }

  @InputOrder(3)
  public String getSurname() {
    return surname;
  }

  @InputOrder(4)
  public String getStreet() {
    return street;
  }

  @InputOrder(5)
  public String getTown() {
    return town;
  }

  @InputOrder(6)
  public String getPostcode() {
    return postcode;
  }
}

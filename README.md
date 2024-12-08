# Airline Booking System

A Java-based airline booking system developed as part of HND Software Development coursework.

## Project Overview

This project demonstrates the implementation of a booking system for airlines, showcasing object-oriented programming principles and file I/O operations. The system allows users to add new flights, routes, customers, and bookings, with input validation checks to ensure data integrity.

## Features

- Flight, Route, Customer and Booking detail processing and exporting.
- Input validation for user provided values.

## Technology Stack

- Java 22
- File-based data storage (CSV)
- JUnit for testing
- Maven for build automation
- Git for version control
- Mockito for mocking
- Custom validation framework

## Project Structure

- `src/main/java/com/bougastefa/app/`
  - `Menu.java` - Main interface handling user interactions
  - `*Service.java` - Manages * operations and validation
  - `ValidationUtils.java` - Handles input validation across the system
  - `*.java` - Entity classe
  - `*` Referring to Flight, Route, Customer, Booking
## Setup Instructions

1. Clone the repository
2. Ensure Java 22 or higher is installed
3. Install Maven and ensure it is added to the PATH
4. Run `mvn clean install` to build the project
5. Run `mvn exec:java` to start the application
## Usage

The system providies a CLI for user interaction. The user can perform the following operations:
1. Add a new Flight
2. Add a new Route
3. Add a new Customer
4. Book a new Booking
5. Exit

Each operation includes validation checks to ensure the data provided is valid.

## Data Storage
The application stores data in CSV files in the root directory of the project. The data is stored in the following files:
1. `Flight.csv` - Flight details
2. `Route.csv` - Route details
3. `Customer.csv` - Customer details
4. `Booking.csv` - Booking details

## Testing
The project inlcudes JUnit tests for the Service classes, which can be run using the following command: `mvn test`

## Educational Context

This project was developed as part of an HND Software Development distinction project, focusing on demonstrating:
- Object-Oriented Programming principles
- File I/O operations
- Unit testing and mocking

## Author

- BougaStefa

## Note

This is an educational project created solely for coursework purposes. 

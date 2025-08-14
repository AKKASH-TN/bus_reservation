# Bus Reservation System

A console-based Java application for booking and managing bus reservations.

## Features
- View available buses and seat availability by date
- Book tickets for a selected bus and date
- Cancel existing bookings by booking number
- Robust error handling and user-friendly prompts
- Modular design using OOPS concepts and DAO pattern

## Technologies Used
- Java
- JDBC
- MySQL

## Setup Instructions
1. **Clone the repository**
2. **Configure the database**
   - Create a MySQL database named `busresv`
   - Set up the required tables (`bus`, `booking`, etc.)
   - Update database credentials in `DbConnection.java` if needed
3. **Add MySQL JDBC Driver**
   - Place the MySQL Connector JAR in the `lib/` directory
4. **Compile and Run**
   - Compile: `javac -cp lib/mysql-connector-j-9.3.0.jar src/bus_reservation/*.java -d bin`
   - Run: `java -cp bin;lib/mysql-connector-j-9.3.0.jar bus_reservation.BusDemo`

## Example Usage
- View available seats for a date
- Book a ticket by entering passenger details
- Cancel a booking using the booking number

## Author
- AKKASH

## License
This project is for educational purposes.

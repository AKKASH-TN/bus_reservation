package bus_reservation;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
public class Booking {
	String passengerName;
	int busNo;
	Date date;
	int bookingNumber; // new field
	
	Booking(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name of passenger: ");
		passengerName = sc.next();
		System.out.print("\nEnter the Bus Number: ");
		busNo = sc.nextInt();

		// Show bus route info
		try {
			BusDAO busdao = new BusDAO();
			Bus bus = busdao.getBusByNo(busNo);
			if (bus != null) {
				System.out.println("Route: " + bus.getFromLocation() + " -> " + bus.getToLocation());
				System.out.println("Driver: " + bus.getDriverName());
				System.out.println("Duration: " + bus.getDuration());
			}
		} catch (Exception e) {
			System.out.println("Could not fetch bus info. Please try again.");
			e.printStackTrace();
		}

		System.out.print("\nEnter date dd-mm-yyyy: ");
		String dateinput = sc.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = dateFormat.parse(dateinput);
		} catch (ParseException e) {
			System.out.println("Invalid date format. Please use dd-MM-yyyy.");
			e.printStackTrace();
		}
		sc.close();
	}
	public boolean isAvailable() throws SQLException{
		try {
			BusDAO busdao = new BusDAO();
			BookingDAO bookingdao = new BookingDAO();
			int capacity = busdao.getCapacity(busNo);
			int booked = bookingdao.getBookedCount(busNo, date);
			return booked < capacity;
		} catch (Exception e) {
			System.out.println("Error checking seat availability.");
			e.printStackTrace();
			return false;
		}
	}
	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public int getBookingNumber() {
		return bookingNumber;
	}
}

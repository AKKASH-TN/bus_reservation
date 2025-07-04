package bus_reservation;
import java.util.Scanner;


public class BusDemo {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		BusDAO busdao = new BusDAO();
		try {
			busdao.displayBusInfo();
		} catch (Exception e) {
			System.out.println("Error displaying bus info. Please try again later.");
			e.printStackTrace();
			return;
		}
		Scanner sc = new Scanner(System.in);
		int userOpt = 1;
		while(userOpt == 1) {
			System.out.print("Enter date to view available seats (dd-MM-yyyy): ");
			String dateInput = sc.next();
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
			java.util.Date selectedDate = null;
			try {
				selectedDate = dateFormat.parse(dateInput);
			} catch (Exception e) {
				System.out.println("Invalid date format. Try again.");
				continue;
			}

			System.out.println("\nAvailable seats for each bus on " + dateInput + ":");
			java.util.List<Bus> buses = null;
			try {
				buses = busdao.getAllBuses();
			} catch (Exception e) {
				System.out.println("Error fetching bus list. Please try again later.");
				e.printStackTrace();
				continue;
			}
			BookingDAO bookingdao = new BookingDAO();
			for (Bus bus : buses) {
				int booked = 0;
				try {
					booked = bookingdao.getBookedCount(bus.getBusNo(), selectedDate);
				} catch (Exception e) {
					System.out.println("Error fetching bookings for bus " + bus.getBusNo());
					e.printStackTrace();
				}
				int available = bus.getCapacity() - booked;
				System.out.println("Bus No: " + bus.getBusNo() + ", Available Seats: " + available + ", Route: " + bus.getFromLocation() + " -> " + bus.getToLocation());
			}

			System.out.println("\nEnter 1 to Book, 2 to Cancel a Booking, 3 to Exit: ");
			try {
				userOpt = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Exiting.");
				e.printStackTrace();
				break;
			}
			if (userOpt == 1) {
				Booking booking = null;
				try {
					booking = new Booking();
					if(booking.isAvailable()) {
						bookingdao.addBooking(booking);
						System.out.println("Your booking is confirmed. Thank You!");
					} else {
						System.out.println("Sorry. Bus is not available at that time. Try another bus or date.");
					}
				} catch (Exception e) {
					System.out.println("Error during booking. Please try again.");
					e.printStackTrace();
				}
			} else if (userOpt == 2) {
				System.out.print("Enter your booking number to cancel: ");
				int bookingNumber = sc.nextInt();
				bookingdao.cancelBooking(bookingNumber);
			} else {
				break;
			}
		}
		sc.close();

	}

}

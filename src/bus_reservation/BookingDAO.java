package bus_reservation;
import java.util.Date;
import java.sql.*;
public class BookingDAO {

	public int getBookedCount(int busNo, Date date) {
		String query = "select count(passenger_name) from booking where bus_no =? and travel_date =?";
		try (
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query)
		) {
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			ps.setInt(1, busNo);
			ps.setDate(2, sqldate);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Error fetching booked count for bus no: " + busNo);
			e.printStackTrace();
		}
		return 0;
	}
	public int addBooking(Booking booking) {
		String sql = "INSERT INTO booking (passenger_name, bus_no, date) VALUES (?, ?, ?)";
		try (
			Connection con = DbConnection.getConnection();
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
		) {
			pst.setString(1, booking.passengerName);
			pst.setInt(2, booking.busNo);
			java.sql.Date sqlDate = new java.sql.Date(booking.date.getTime());
			pst.setDate(3, sqlDate);
			pst.executeUpdate();
			try (ResultSet rs = pst.getGeneratedKeys()) {
				if (rs.next()) {
					int bookingNumber = rs.getInt(1);
					booking.setBookingNumber(bookingNumber);
					return bookingNumber;
				}
			}
		} catch (Exception e) {
			System.out.println("Error adding booking for passenger: " + booking.passengerName);
			e.printStackTrace();
		}
		return -1;
	}
	public void displayBooking(int bookingNumber) {
		String sql = "SELECT * FROM booking WHERE booking_number = ?";
		try (
			Connection con = DbConnection.getConnection();
			PreparedStatement pst = con.prepareStatement(sql)
		) {
			pst.setInt(1, bookingNumber);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					System.out.println("Booking Number: " + rs.getInt("booking_number"));
					System.out.println("Passenger Name: " + rs.getString("passenger_name"));
					System.out.println("Bus No: " + rs.getInt("bus_no"));
					System.out.println("Date: " + rs.getDate("date"));
					// Optionally fetch and show bus route info
				} else {
					System.out.println("Booking not found.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error displaying booking for booking number: " + bookingNumber);
			e.printStackTrace();
		}
	}
	public boolean cancelBooking(int bookingNumber) {
	    String sql = "DELETE FROM booking WHERE booking_number = ?";
	    try (
	        Connection con = DbConnection.getConnection();
	        PreparedStatement pst = con.prepareStatement(sql)
	    ) {
	        pst.setInt(1, bookingNumber);
	        int affectedRows = pst.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("Booking cancelled successfully.");
	            return true;
	        } else {
	            System.out.println("No booking found with the given booking number.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error cancelling booking for booking number: " + bookingNumber);
	        e.printStackTrace();
	    }
	    return false;
	}
}

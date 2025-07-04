package bus_reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
    public void displayBusInfo() {
        String query = "SELECT * FROM bus";
        try (
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)
        ) {
            while (rs.next()) {
                System.out.println("Bus No: " + rs.getInt("bus_no"));
                boolean ac = rs.getBoolean("ac");
                System.out.println("Ac: " + (ac ? "yes" : "no"));
                System.out.println("Capacity: " + rs.getInt("capacity"));
                System.out.println("Driver: " + rs.getString("driver_name"));
                System.out.println("From: " + rs.getString("from_location"));
                System.out.println("To: " + rs.getString("to_location"));
                System.out.println("Duration: " + rs.getString("duration"));
                System.out.println("--------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error displaying bus info.");
            e.printStackTrace();
        }
    }

    public int getCapacity(int busNo) {
        String query = "SELECT capacity FROM bus WHERE bus_no = ?";
        try (
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setInt(1, busNo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("capacity");
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching bus capacity for bus no: " + busNo);
            e.printStackTrace();
        }
        return 0;
    }

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM bus";
        try (
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()) {
                int busNo = rs.getInt("bus_no");
                boolean ac = rs.getBoolean("ac");
                int capacity = rs.getInt("capacity");
                String driverName = rs.getString("driver_name");
                String from = rs.getString("from_location");
                String to = rs.getString("to_location");
                String duration = rs.getString("duration");
                Bus bus = new Bus(busNo, ac, capacity, driverName, from, to, duration);
                buses.add(bus);
            }
        } catch (Exception e) {
            System.out.println("Error fetching all buses.");
            e.printStackTrace();
        }
        return buses;
    }

    public Bus getBusByNo(int busNo) {
        String sql = "SELECT * FROM bus WHERE bus_no = ?";
        try (
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setInt(1, busNo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    boolean ac = rs.getBoolean("ac");
                    int capacity = rs.getInt("capacity");
                    String driverName = rs.getString("driver_name");
                    String from = rs.getString("from_location");
                    String to = rs.getString("to_location");
                    String duration = rs.getString("duration");
                    return new Bus(busNo, ac, capacity, driverName, from, to, duration);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching bus info for bus no: " + busNo);
            e.printStackTrace();
        }
        return null;
    }

    public void displayAllBuses() throws SQLException {
        List<Bus> buses = getAllBuses();
        for (Bus bus : buses) {
            String acStatus = bus.getAc() ? "yes" : "no";
            System.out.println("Bus No: " + bus.getBusNo() +
                ", AC: " + acStatus +
                ", Capacity: " + bus.getCapacity() +
                ", Driver: " + bus.getDriverName() +
                ", From: " + bus.getFromLocation() +
                ", To: " + bus.getToLocation() +
                ", Duration: " + bus.getDuration());
        }
    }
}

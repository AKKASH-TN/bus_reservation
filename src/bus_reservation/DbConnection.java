package bus_reservation;
import java.sql.*;

public class DbConnection {
	private static final String url = "jdbc:mysql://localhost:3306/busresv";
	private static final String userName = "root";
	private static final String passWord = "ashsql";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,passWord);
	}
}

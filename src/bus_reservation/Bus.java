package bus_reservation;

public class Bus {
	private int busNo;
	private boolean ac;
	private int capacity;
	private String driverName;
	private String fromLocation;
	private String toLocation;
	private String duration;

	public Bus(int no, boolean ac, int cap, String driverName, String from, String to, String duration){
	    this.busNo = no;
	    this.ac = ac;
	    this.capacity = cap;
	    this.driverName = driverName;
	    this.fromLocation = from;
	    this.toLocation = to;
	    this.duration = duration;
	}
	//accessors
	public int getBusNo(){
		return busNo;
	}
	public boolean getAc() {
		return ac;
	}
	public int getCapacity() {
		return capacity;
	}
	public String getDriverName() { return driverName; }
	public String getFromLocation() { return fromLocation; }
	public String getToLocation() { return toLocation; }
	public String getDuration() { return duration; }
	//getters
	public void setCapacity(int cap) {
		capacity = cap;
	}
	public void setAc(boolean val) {
		ac = val;
	}
	public void setDriverName(String driverName) { this.driverName = driverName; }
	public void setFromLocation(String fromLocation) { this.fromLocation = fromLocation; }
	public void setToLocation(String toLocation) { this.toLocation = toLocation; }
	public void setDuration(String duration) { this.duration = duration; }
	
	
}

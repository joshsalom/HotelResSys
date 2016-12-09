import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents a hotel reservation 
 */
public class Reservation implements Serializable
{
	private Room room;
	private String startDate;
	private String endDate;
	private User user;
	private Date start; 
	private Date end;
	
	public Reservation(Room r, String startDate, String endDate, User theUser)
	{
		this.room = r;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = theUser;
		start = convertDate(startDate);
		end = convertDate(endDate);
	}
	
	/**
	 * Sets the room of the reservation
	 * Precondition: Room is a valid room in the hotel
	 * Postcondition: Reservation is changed to the given room
	 * @param r Room that the reservation is changed to
	 */
	public void setRoom(Room r){
		room = r;
	}
	
	/**
	 * Returns the room of the reservation
	 * @return Room of the reservation
	 */
	public Room getRoom()
	{
		return room;
	}
	
	/**
	 * Returns the start date of the reservation in string format
	 * @return Start date of reservation in string format
	 */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	 * Returns the end date of the reservation in string format
	 * @return End date of reservation in string format
	 */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	 * Returns the user who reserved the room
	 * @return User who made the reservation
	 */
	public User getUser()
	{
		return user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return room.equals(other.room) && startDate.equals(other.startDate) && endDate.equals(other.endDate);
	}
	
	@Override
	public String toString(){
		return "Reservation:\n"
				+ "  Room number: " + room.getRoomNumber() + "\n"
				+ "  Room type: " + room.getRoomType() + "\n"
				+ "  Date: " + startDate + "-" + endDate + "\n";
	}
	
	/**
	 * Checks whether two reservations have a date conflict
	 * @param other The other reservation that is to be checked
	 * @return True if the two reservations overlap
	 */
	public boolean checkHasConflict(Reservation other){
		//special case: reservation start is same day as other reservation's end
		if((other.start.equals(this.end) && other.end.compareTo(this.end) > 0) || 
			(this.start.equals(other.end) && this.end.compareTo(other.end) > 0))
			return false;
		if(other.start.compareTo(this.start) >=0 && other.start.compareTo(this.end) <=0)
			return true;
		if(other.end.compareTo(this.start) >=0 && other.start.compareTo(this.end) <=0)
			return true;
		if(this.start.compareTo(other.start) >=0 && this.start.compareTo(other.end) <=0)
			return true;
		if(this.end.compareTo(other.start) >=0 && this.start.compareTo(other.end) <=0)
			return true;
		return false;
	}
	
	/**
	 * Converts a date in string format to a date format
	 * @param d Date in string format
	 * @return Date in date format
	 */
	private Date convertDate(String d){
		String[] strings = d.split("/", -1);
		int[] arr = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			arr[i] = Integer.parseInt(strings[i]);
		}
		GregorianCalendar cal = new GregorianCalendar(arr[2], arr[0] - 1, arr[1]);
		return cal.getTime();
	}
	
	/**
	 * Returns the start date of the reservation in Date format
	 * @return Start date of the reservation in Date format
	 */
	public Date getStartDay(){
		return start;
	}
	
	/**
	 * Returns the end date of the reservation in Date format
	 * @return End date of the reservation in Date format
	 */
	public Date getEndDay(){
		return end;
	}

}

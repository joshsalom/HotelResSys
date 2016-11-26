
public class Reservation
{
	private String roomNumber;
	private String startDate;
	private String endDate;
	private User user;
	
	public Reservation(String roomNumber, String startDate, String endDate, User theUser)
	{
		this.roomNumber = roomNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = theUser;
	}
	
	public String getRoomNumber()
	{
		return roomNumber;
	}
	
	public String getStartDate()
	{
		return startDate;
	}
	
	public String getEndDate()
	{
		return endDate;
	}
	
	public User getUser()
	{
		return user;
	}
}

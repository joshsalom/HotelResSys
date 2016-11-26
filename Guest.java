import java.util.ArrayList;

/**
 * A guest account
 * 
 * @author Alex Liu
 */
public class Guest extends User
{
	private ArrayList<Reservation> reservations;

	public Guest(int userID, String userName)
	{
		super(userID, userName);
		reservations = new ArrayList<Reservation>();
	}

	/**
	 * Gets all the reservations that the user has made
	 * 
	 * @return list of current reservations
	 */
	public ArrayList<Reservation> getReservations()
	{
		return this.reservations;
	}

	/**
	 * Adds a given reservation to the user's account
	 * 
	 * @param r
	 *            The reservation that is to be added
	 */
	public void addReservation(Reservation r)
	{
		reservations.add(r);
	}

	/**
	 * Removes a given reservation from the user's account
	 * 
	 * @param r
	 *            The reservation that is to be removed
	 */
	public void cancelReservation(Reservation r)
	{
		int index = reservations.indexOf(r);
		if (index > -1)
			reservations.remove(index);
	}

}
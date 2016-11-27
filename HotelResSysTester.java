import java.util.ArrayList;
import java.util.TreeMap;

public class HotelResSysTester
{
	public static void main(String args[])
	{
		// read from saved file
		// each line of the file is formatted as so:
		// user id, user name (example: John_Smith) (we input underscore between
		// names when saving)
		// list of each reservation detail under this user: room#, start time,
		// end time
		// room# such as "1" or "20" will be distinguishable from usernames, we
		// can force users to create names >3 letters

		// TEMPORARY DATA STRUCTURE INITIALIZATION
		TreeMap<String, ArrayList<Reservation>> roomMap = new TreeMap<String, ArrayList<Reservation>>();
		ArrayList<User> userList = new ArrayList<User>();

		HotelModel model = new HotelModel(roomMap, userList);
		
		AccountTypeFrame newATF = new AccountTypeFrame(model);
	}
}

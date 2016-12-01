import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class HotelResSysTester
{
	public static void main(String args[]) throws ClassNotFoundException, IOException
	{
		// read from saved file
		// each line of the file is formatted as so:
		// user id, user name (example: John_Smith) (we input underscore between
		// names when saving)
		// list of each reservation detail under this user: room#, start time,
		// end time
		// room# such as "1" or "20" will be distinguishable from usernames, we
		// can force users to create names >3 letters

		HotelModel model = null;

		//try to load hotel information through deserialization 
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("hotelInfo.data"));
			model = (HotelModel) in.readObject();
			in.close(); 
		}
		//first run - no users yet; create new hotelModel
		catch(FileNotFoundException e){ 
			ArrayList<User> userList = new ArrayList<User>();
			TreeMap<String, ArrayList<Reservation>> roomMap = new TreeMap<String, ArrayList<Reservation>>();
			model = new HotelModel(roomMap, userList);
			
		}
		AccountTypeFrame newATF = new AccountTypeFrame(model);
	
	}
}

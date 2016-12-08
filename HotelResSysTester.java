import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class HotelResSysTester
{
	public static void main(String args[]) throws ClassNotFoundException, IOException
	{
		
		HotelModel model = null;

		//try to load hotel information through deserialization 
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("hotelInfo.data"));
			model = (HotelModel) in.readObject();
			in.close(); 
			model.resetListeners();
		}
		//first run - no users yet; create new hotelModel
		catch(FileNotFoundException e){ 
			ArrayList<User> userList = new ArrayList<User>();
			TreeMap<Room, ArrayList<Reservation>> roomMap = new TreeMap<Room, ArrayList<Reservation>>();
			model = new HotelModel(roomMap, userList);
			model.addRooms();
			
		}
		AccountTypeFrame newATF = new AccountTypeFrame(model);
	
	}
}

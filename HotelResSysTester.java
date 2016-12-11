import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Starts and runs the hotel reservation system.
 * @author Joshua Salom
 */
public class HotelResSysTester {
	
	public static void main(String args[]) {

		ArrayList<User> userList = new ArrayList<User>();
		TreeMap<Room, ArrayList<Reservation>> roomMap = new TreeMap<Room, ArrayList<Reservation>>();
		HotelModel model = new HotelModel(roomMap, userList);
		model.addRooms();
		
		AccountTypeFrame newATF = new AccountTypeFrame(model);
	}
}
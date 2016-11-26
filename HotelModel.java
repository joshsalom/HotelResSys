import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.ChangeListener;

public class HotelModel
{
	private TreeMap<String, ArrayList<Reservation>> roomMap; //remember Res for start/end dates
	private ArrayList<User> userList;
	private ArrayList<ChangeListener> listeners;
	//TODO: might need to keep track of current user? we'll see
	
	public HotelModel(TreeMap<String, ArrayList<Reservation>> roomMap, ArrayList<User> userList)
	{
		this.roomMap = roomMap;
		this.userList = userList;
		listeners = new ArrayList<ChangeListener>();
	}
	
	public TreeMap<String, ArrayList<Reservation>> getRoomMap()
	{
		return this.roomMap;
	}
	
	public ArrayList<User> getUserList()
	{
		return this.userList;
	}
	
	public void attachListener(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void updateRoomMap()
	{
		//TODO
	}
	
	public void updateUserList()
	{
		//TODO
	}
}

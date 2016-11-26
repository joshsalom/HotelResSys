import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelModel
{
	private TreeMap<String, ArrayList<Reservation>> roomMap; //remember Res for start/end dates
	private ArrayList<User> userList;
	private ArrayList<ChangeListener> listeners;
	private String currentUserID;
	
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
	
	public String getCurrentUserID()
	{
		return this.currentUserID;
	}
	
	public void attachListener(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void updateRoomMap()
	{
		//TODO
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
	
	public void updateUserList(User newUser)
	{
		userList.add(newUser);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
	
	public void updateCurrentUserID(String newCurrentUserID)
	{
		this.currentUserID = newCurrentUserID;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
}

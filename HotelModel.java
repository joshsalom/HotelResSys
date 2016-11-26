import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelModel
{
	private TreeMap<String, ArrayList<Reservation>> roomMap; //remember Res for start/end dates
	private ArrayList<User> userList;
	private ArrayList<ChangeListener> listeners;
	private User currentUser;
	
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
	
	public User getCurrentUser()
	{
		return this.currentUser;
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
	
	public void updateCurrentUser(User newCurrentUser)
	{
		this.currentUser = newCurrentUser;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
}

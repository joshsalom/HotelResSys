import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelModel implements Serializable
{
	private TreeMap<String, ArrayList<Reservation>> roomMap; //remember Res for start/end dates
	private ArrayList<User> userList;
	private ArrayList<ChangeListener> listeners;
	private User currentUser;
	private String currentStart;
	private String currentEnd;
	private String currentRoomPref;
	
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
	
	public String getCurrentStart()
	{
		return this.currentStart;
	}
	
	public String getCurrentEnd()
	{
		return this.currentEnd;
	}
	
	public String getCurrentRoomPref()
	{
		return this.currentRoomPref;
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
	
	public void updateCurrentStart(String startDate)
	{
		this.currentStart = startDate;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
	
	public void updateCurrentEnd(String endDate)
	{
		this.currentEnd = endDate;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
	
	public void updateCurrentRoomPref(String roomPref)
	{
		this.currentRoomPref = roomPref;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
}

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelModel implements Serializable
{
	private TreeMap<Room, ArrayList<Reservation>> roomMap; //remember Res for start/end dates
	private ArrayList<User> userList;
	private ArrayList<ChangeListener> listeners;
	private User currentUser;
	private String currentStart;
	private String currentEnd;
	private RoomType currentRoomPref;
	GregorianCalendar calendar = new GregorianCalendar();
	
	public HotelModel(TreeMap<Room, ArrayList<Reservation>> roomMap, ArrayList<User> userList)
	{
		this.roomMap = roomMap;
		this.userList = userList;
		listeners = new ArrayList<ChangeListener>();
	}
	
	public TreeMap<Room, ArrayList<Reservation>> getRoomMap()
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
	
	public RoomType getCurrentRoomPref()
	{
		return this.currentRoomPref;
	}
	
	public void attachListener(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void updateRoomMap()
	{
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
	
	
	public void updateCurrentRoomPref(RoomType roomPref)
	{
		this.currentRoomPref = roomPref;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
	

	//used during the first run only
	public void addRooms(){
		for(int x = 1; x <= 10; x++)
			roomMap.put(new Room(x, RoomType.ECONOMY), new ArrayList<Reservation>());
		for(int x = 11; x <= 20; x++)
			roomMap.put(new Room(x, RoomType.LUXURY), new ArrayList<Reservation>());
			
	}
	
	public void storeHotelInformation() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hotelInfo.data"));
		out.writeObject(this);
		out.close(); 
		
	}
}

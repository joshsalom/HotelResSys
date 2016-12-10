import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * The model that stores information about users, hotel rooms, and hotel reservations.
 */
public class HotelModel implements Serializable
{
	private TreeMap<Room, ArrayList<Reservation>> roomMap; 
	private ArrayList<User> userList;
	private transient ArrayList<ChangeListener> listeners;
	private transient User currentUser;
	private String currentStart;
	private String currentEnd;
	private RoomType currentRoomPref;
	private int transcationID;
	GregorianCalendar calendar = new GregorianCalendar();
	
	public HotelModel(TreeMap<Room, ArrayList<Reservation>> roomMap, ArrayList<User> userList)
	{
		this.roomMap = roomMap;
		this.userList = userList;
		listeners = new ArrayList<ChangeListener>();
	}
	
	/**
	 * Adds a reservation to the model and then notifies listeners
	 * @param reservation The reservation that is to be added
	 * @param validRooms The ArrayList<Room> that are available on the given date 
	 * @param roomNumber The room number of the reservation
	 * @return True if the reservation is successfully added to a valid room
	 */
	public boolean addReservation(Reservation reservation, ArrayList<Room> validRooms, int roomNumber){
		boolean enteredValidRoom = true;
		for(Room r: validRooms)
			if(r.getRoomNumber() == roomNumber){
				reservation.setRoom(r);
				this.getRoomMap().get(r).add(reservation);
				Guest g = (Guest)this.getCurrentUser();
				g.addReservation(reservation);
				enteredValidRoom = false;
			}
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
		return enteredValidRoom;
	}
	
	/**
	 * Cancels the given reservation and updates listeners. 
	 * @param reservation The reservation that is to be canceled. 
	 */
	public void cancelReservation(Reservation reservation){
		roomMap.get(reservation.getRoom()).remove(reservation);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listen : listeners)
		{
			listen.stateChanged(event);
		}
	}
	
	/**
	 * Retrieves the mapping of hotel rooms and reservations
	 * @return Mapping of hotel rooms and ArrayList<Reservation>
	 */
	public TreeMap<Room, ArrayList<Reservation>> getRoomMap()
	{
		return this.roomMap;
	}
	
	/**
	 * Retrieves the ArrayList of users that are stored in the system.
	 * @return ArrayList of stored users
	 */
	public ArrayList<User> getUserList()
	{
		return userList;
	}
	
	/**
	 * Returns the current user
	 * @return Current user
	 */
	public User getCurrentUser()
	{
		return currentUser;
	}
	
	/**
	 * Returns the preferred start date of the user
	 * @return String of the preferred start date
	 */
	public String getCurrentStart()
	{
		return currentStart;
	}
	
	/**
	 * Returns the preferred end date of the user
	 * @return String of the preferred end date
	 */
	public String getCurrentEnd()
	{
		return currentEnd;
	}
	
	/**
	 * Returns the room preference of the user
	 * @return Preferred room type of user
	 */
	public RoomType getCurrentRoomPref()
	{
		return currentRoomPref;
	}
	
	/**
	 * Attaches a listener to the model
	 * @param c The ChangeListener that is to be added
	 */
	public void attachListener(ChangeListener c)
	{
		listeners.add(c);
	}
	
	/**
	 * Adds information of the new user
	 * Precondition: User cannot contain the same user ID as another user
	 * Postcondition: The user is added to the list of users
	 * @param newUser The user that is to be added
	 */
	public void updateUserList(User newUser)
	{
		userList.add(newUser);
	}
	
	/**
	 * Creates a new ArrayList of ChangeListeners and sets the list of listeners equal to it
	 */
	public void resetListeners(){
		listeners = new ArrayList<ChangeListener>();
	}
	
	/**
	 * Sets the current user with the given user
	 * @param newCurrentUser The current user 
	 */
	public void updateCurrentUser(User newCurrentUser)
	{
		this.currentUser = newCurrentUser;
	}
	
	/**
	 * Sets the preferred start date with the given string
	 * Precondition: The start date is in the format MM/DD/YYYY
	 * Postcondition: The start date is set to the given string
	 * @param startDate Preferred start date
	 */
	public void updateCurrentStart(String startDate)
	{
		this.currentStart = startDate;
	}
	
	/**
	 * Sets the preferred end date with the given string
	 * Precondition: The end date is in the format MM/DD/YYYY
	 * Postcondition: The end date is set to the given string
	 * @param endDate Preferred end date
	 */
	public void updateCurrentEnd(String endDate)
	{
		this.currentEnd = endDate;
	}
	
	/**
	 * Sets the room preference with the given room type
	 * @param roomPref Preferred room type
	 */
	public void updateCurrentRoomPref(RoomType roomPref)
	{
		this.currentRoomPref = roomPref;

	}
	

	/**
	 * Adds 10 economic and 10 luxurious rooms to the model and maps them to an empty ArrayList of reservations
	 */
	public void addRooms(){
		for(int x = 1; x <= 10; x++)
			roomMap.put(new Room(x, RoomType.ECONOMY), new ArrayList<Reservation>());
		for(int x = 11; x <= 20; x++)
			roomMap.put(new Room(x, RoomType.LUXURY), new ArrayList<Reservation>());
			
	}
	
	/**
	 * Serializes current hotel data to a file
	 * @throws IOException IO trouble
	 */
	public void storeHotelInformation() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hotelInfo.data"));
		out.writeObject(this);
		out.close(); 
		
	}

	/**
	 * Returns the transaction ID
	 * @return the transactionID
	 */
	public int getTranscationID() {
		return transcationID;
	}

	/**
	 * Sets the transaction ID
	 * @param transcationID The transcationID to be set
	 */
	public void setTranscationID(int transcationID) {
		this.transcationID = transcationID;
	}
}
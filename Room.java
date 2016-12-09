import java.io.Serializable;

/**
 * Represents a type of room - Economy or Luxury
 */
enum RoomType{
	ECONOMY, LUXURY;
}

/**
 * Represents a room in the hotel
 */
public class Room implements Comparable<Room>, Serializable {
	private RoomType roomType;
	private int roomNumber;
	private int roomPrice;
	
	public Room(int num, RoomType type){
		roomNumber = num;
		roomType = type;
		if(roomType == RoomType.ECONOMY)
			roomPrice = 80;
		else
			roomPrice = 200;
	}
	
	/**
	 * Returns the price of the room
	 * @return Price of the room
	 */
	public int getRoomPrice(){
		return roomPrice;
	}
	
	/**
	 * Returns the type of the room
	 * @return Room type
	 */
	public RoomType getRoomType(){
		return roomType;
	}
	
	/**
	 * Returns the room number
	 * @return Room number
	 */
	public int getRoomNumber(){
		return roomNumber;
	}

	@Override
	public int hashCode() { 
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return roomNumber == other.roomNumber && roomType == other.roomType;
	}

	//compare in order of room number
	@Override
	public int compareTo(Room other) {
		return roomNumber - other.roomNumber;
	}
	
	@Override
	public String toString(){
		return roomNumber + ": " + roomType;
	}
	
	
}

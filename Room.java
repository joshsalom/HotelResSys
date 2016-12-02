import java.io.Serializable;

enum RoomType{
	ECONOMY, LUXURY;
}

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
	
	public int getRoomPrice(){
		return roomPrice;
	}
	
	public RoomType getRoomType(){
		return roomType;
	}
	
	public int getRoomNumber(){
		return roomNumber;
	}

	@Override
	public int hashCode() { //generated by eclipse
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
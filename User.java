import java.io.Serializable;

/**
 * Represents a user account - either guest or manager
 * @author Alex Liu
 */
@SuppressWarnings("serial")
public abstract class User implements Serializable
{
	private String userID;
	private String userName;

	public User(String userID, String userName)
	{
		this.userID = userID;
		this.userName = userName;
	}

	/**
	 * Returns the username of a user
	 * 
	 * @return Username of user
	 */
	public String getName()
	{
		return this.userName;
	}

	/**
	 * Returns the user's ID
	 * 
	 * @return user ID
	 */
	public String getID()
	{
		return this.userID;
	}
	
	//two users equal if they have the same username 
	@Override
	public boolean equals(Object o) {
		if (this == o) // reflexive
			return true;
		if (o == null) // test for null
			return false;
		if (getClass() != o.getClass()) // symmetry
			return false;
		User other = (User) o;
		return other.userID.equals(userID);
	}

	@Override
	public String toString()
	{
		return "ID: " + userID + " , " + "Username: " + userName;
	}

	/**
	 * Returns true if the the user is a manager
	 * 
	 * @return whether the user is a manager
	 */
	public boolean isManager()
	{
		return false;
	}
	

}
/**
 * Represents a user account - either guest or manager
 * 
 * @author Alex Liu
 */
public class User
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
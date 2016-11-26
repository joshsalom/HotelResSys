/**
 * A manager account
 * 
 * @author Alex Liu
 */
public class Manager extends User
{

	public Manager(int userID, String username)
	{
		super(userID, username);

	}

	@Override
	public boolean isManager()
	{
		return true;
	}

}
/**
 * A manager account
 * 
 * @author Alex Liu
 */
@SuppressWarnings("serial")
public class Manager extends User
{

	public Manager(String userID, String username)
	{
		super(userID, username);

	}

	@Override
	public boolean isManager()
	{
		return true;
	}
	

}
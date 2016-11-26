import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AccountTypeFrame
{
	public static void main(String args[])
	{
		//read from saved file
		//each line of the file is formatted as so:
		//user id, user name (example: John_Smith) (we input underscore between names when saving)
		//list of each reservation detail under this user: room#, start time, end time
		//room# such as "1" or "20" will be distinguishable from usernames, we can force users to create names >3 letters
		
		
		
		JFrame frame = new JFrame();
		frame.setSize(400, 200);
		frame.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Select Account Type");
		frame.add(label, BorderLayout.NORTH);
		
		JButton guestButton = new JButton("Guest");
		frame.add(guestButton, BorderLayout.WEST);
		
		JButton managerButton = new JButton("Manager");
		frame.add(managerButton, BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

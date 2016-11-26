import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountTypeMainFrame
{
	public static void main(String args[])
	{
		// read from saved file
		// each line of the file is formatted as so:
		// user id, user name (example: John_Smith) (we input underscore between
		// names when saving)
		// list of each reservation detail under this user: room#, start time,
		// end time
		// room# such as "1" or "20" will be distinguishable from usernames, we
		// can force users to create names >3 letters

		// TEMPORARY DATA STRUCTURE INITIALIZATION
		TreeMap<String, ArrayList<Reservation>> roomMap = new TreeMap<String, ArrayList<Reservation>>();
		ArrayList<User> userList = new ArrayList<User>();

		HotelModel model = new HotelModel(roomMap, userList);

		JFrame frame = new JFrame();
		frame.setSize(400, 140);
		frame.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Welcome to the Hotel Reservation System!");

		JLabel label = new JLabel("Select Account Type");
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());

		JPanel welcomeLabelPanel = new JPanel();
		welcomeLabelPanel.add(welcomeLabel);

		welcomeLabelPanel.add(welcomeLabel);
		frame.add(welcomeLabelPanel, BorderLayout.NORTH);

		labelPanel.add(label);
		frame.add(labelPanel, BorderLayout.CENTER);

		JButton guestButton = new JButton("Guest");
		guestButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						GuestLoginFrame guestLoginFrame = new GuestLoginFrame(model);
						frame.dispose();
					}
			
				});

		JButton managerButton = new JButton("Manager");
		managerButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						ManagerLoginFrame managerLoginFrame = new ManagerLoginFrame(model);
						frame.dispose();
					}
			
				});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guestButton);
		buttonPanel.add(managerButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

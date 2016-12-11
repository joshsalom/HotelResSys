import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Frame that allows the user to select whether they are a guest or a manager.
 * @author Alex Liu, Harita Shroff, Joshua Salom
 */
public class AccountTypeFrame
{
	private HotelModel model;
	public AccountTypeFrame(HotelModel hotelModel)
	{
		this.model = hotelModel;
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
						ManagerResHandler managerResHandler = new ManagerResHandler(model);
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
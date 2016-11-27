import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuestResHandler
{
	private HotelModel model;
	public GuestResHandler(HotelModel hotelModel)
	{
		this.model = hotelModel;
		JFrame frame = new JFrame();
		frame.setSize(400, 140);
		frame.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Hello, " + model.getCurrentUser().getName() + " (" + model.getCurrentUser().getID() + ")!");

		JLabel label = new JLabel("As a Guest, what would you like to do?");
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());

		JPanel welcomeLabelPanel = new JPanel();
		welcomeLabelPanel.add(welcomeLabel);

		welcomeLabelPanel.add(welcomeLabel);
		frame.add(welcomeLabelPanel, BorderLayout.NORTH);

		labelPanel.add(label);
		frame.add(labelPanel, BorderLayout.CENTER);

		JButton guestButton = new JButton("Make Reservations");
		guestButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						GuestCheckFrame newGCF = new GuestCheckFrame(model);
						frame.dispose();
					}
			
				});

		JButton managerButton = new JButton("View/Cancel Reservations");
		managerButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						//TODO: Create GuestViewFrame, pass on model
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

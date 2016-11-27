import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuestCheckFrame
{
	private HotelModel model;
	public GuestCheckFrame(HotelModel hotelModel)
	{
		this.model = hotelModel;
		JFrame frame = new JFrame();
		frame.setSize(500, 300);
		frame.setLayout(new BorderLayout());
		
		JLabel welcomeLabel = new JLabel("Make Reservations (Guest: " + model.getCurrentUser().getID() + ")");
		JPanel welcomeLabelPanel = new JPanel();
		welcomeLabelPanel.add(welcomeLabel);
		frame.add(welcomeLabelPanel, BorderLayout.NORTH);
		
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.PAGE_AXIS));
		
		JLabel checkTitle = new JLabel("Please enter the dates in MM/DD/YYYY format");
		JPanel checkTitlePanel = new JPanel();
		checkTitlePanel.setLayout(new FlowLayout());
		checkTitlePanel.add(checkTitle);
		checkPanel.add(checkTitlePanel);
		
		JLabel inPrompt = new JLabel("Check In:  ");
		JTextField inTextField = new JTextField(12);
		JPanel inContent = new JPanel();
		inContent.add(inPrompt);
		inContent.add(inTextField);
		checkPanel.add(inContent);
		
		JLabel outPrompt = new JLabel("Check Out: ");
		JTextField outTextField = new JTextField(12);
		JPanel outContent = new JPanel();
		outContent.add(outPrompt);
		outContent.add(outTextField);
		checkPanel.add(outContent);
		
		JLabel blank = new JLabel(" ");
		checkPanel.add(blank);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		checkPanel.add(buttonPanel);
		
		JLabel roomTypeLabel = new JLabel("Economic Room ($80) or Luxury Room ($200): ");
		buttonPanel.add(roomTypeLabel);
		
		JButton econButton = new JButton("$80");
		buttonPanel.add(econButton);
		econButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						
						frame.dispose();
					}
			
				});
		
		JButton luxButton = new JButton("$200");
		buttonPanel.add(luxButton);
		luxButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						
						frame.dispose();
					}
			
				});
		
		
		frame.add(checkPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

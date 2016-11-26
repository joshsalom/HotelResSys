import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuestLoginFrame
{
	private HotelModel model;
	
	public GuestLoginFrame(HotelModel theModel)
	{
		this.model = theModel;
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		frame.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Guest: Sign In or Sign Up");
		JPanel welcomeLabelPanel = new JPanel();
		welcomeLabelPanel.add(welcomeLabel);
		frame.add(welcomeLabelPanel, BorderLayout.NORTH);
		
		JPanel signInPanel = new JPanel();
		signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.PAGE_AXIS));
		
		JLabel signInTitle = new JLabel("Sign In to Existing Guest Account");
		JPanel signInTitlePanel = new JPanel();
		signInTitlePanel.setLayout(new FlowLayout());
		signInTitlePanel.add(signInTitle);
		signInPanel.add(signInTitlePanel);
		
		JLabel signInPrompt = new JLabel("User ID: ");
		JTextField signInTextField = new JTextField(12);
		JPanel signInContent = new JPanel();
		signInContent.add(signInPrompt);
		signInContent.add(signInTextField);
		signInPanel.add(signInContent);
		
		JLabel signInPrompt2 = new JLabel(" ");
		JPanel signInContent2 = new JPanel();
		signInContent2.add(signInPrompt2);
		signInPanel.add(signInContent2);
		
		JButton signInButton = new JButton("Sign In");
		signInContent.add(signInButton);
		signInButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						String input = signInTextField.getText();
						ArrayList<User> userList = model.getUserList();
						for (User thisUser : userList)
						{
							if (!thisUser.isManager() && thisUser.getID().equals(input))
							{
								//TODO: Update current user; Create GuestResHandler and pass on model to this class; dispose this frame
							}
						}
						signInPrompt2.setText("Error: Guest account does not exist");
						
					}
			
				});
		
		
		frame.add(signInPanel, BorderLayout.CENTER);
		
		JPanel signUpPanel = new JPanel();
		signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.PAGE_AXIS));
		
		JLabel signUpTitle = new JLabel("Sign Up for New Guests");
		JPanel signUpTitlePanel = new JPanel();
		signUpTitlePanel.setLayout(new FlowLayout());
		signUpTitlePanel.add(signUpTitle);
		signUpPanel.add(signUpTitlePanel);
		
		JLabel signUpPrompt1 = new JLabel("New User ID: ");
		JTextField signUpTextField1 = new JTextField(12);
		JPanel signUpContent1 = new JPanel();
		signUpContent1.add(signUpPrompt1);
		signUpContent1.add(signUpTextField1);
		signUpPanel.add(signUpContent1);
		
		JLabel signUpPrompt2 = new JLabel("Full Name: ");
		JTextField signUpTextField2 = new JTextField(12);
		JPanel signUpContent2 = new JPanel();
		signUpContent2.add(signUpPrompt2);
		signUpContent2.add(signUpTextField2);
		signUpPanel.add(signUpContent2);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpPanel.add(signUpButton);
		signUpButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						String newUserID = signUpTextField1.getText();
						String newUserName = signUpTextField2.getText();
						//We can check if user exists, but we'll forget about it for now
						Guest newGuest = new Guest(newUserID, newUserName);
						model.updateUserList(newGuest);
						model.updateCurrentUserID(newUserID);
						//TODO: create GuestResHandler and pass on model; dispose this frame
						frame.dispose();
					}
			
				});
		
		JLabel blank = new JLabel(" ");
		signUpPanel.add(blank);
		
		frame.add(signUpPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

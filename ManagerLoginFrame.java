import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerLoginFrame
{
	private HotelModel model;
	
	public ManagerLoginFrame(HotelModel theModel)
	{
		this.model = theModel;
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		frame.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Manager: Sign In or Sign Up");
		JPanel welcomeLabelPanel = new JPanel();
		welcomeLabelPanel.add(welcomeLabel);
		frame.add(welcomeLabelPanel, BorderLayout.NORTH);
		
		JPanel signInPanel = new JPanel();
		signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.PAGE_AXIS));
		
		JLabel signInTitle = new JLabel("Sign In to Existing Manager Account");
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
						Manager manager = null;
						//check to see if guest with inputted user ID exists
						//if user does exist, then updates model, creates new GuestResHandler and closes frame
						for (User thisUser : userList)
						{
							if (thisUser.isManager() && thisUser.getID().equals(input))
							{
								manager = (Manager) thisUser;
								model.updateCurrentUser(manager);
								ManagerResHandler newMRH = new ManagerResHandler(model);
								//TODO: pass model onto ManagerResHandler
								frame.dispose();
							}
						}
						//displays error message if no such user exists
						if(manager == null)
							signInPrompt2.setText("Error: Manager account does not exist");
			
					}
				});
		
		
		frame.add(signInPanel, BorderLayout.CENTER);
		
		JPanel signUpPanel = new JPanel();
		signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.PAGE_AXIS));
		
		JLabel signUpTitle = new JLabel("Sign Up for New Managers");
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
						//check to see if user exists
						ArrayList<User> users = model.getUserList();
						Manager newManager = new Manager(newUserID, newUserName);
						//stores new manager information only if no manager or guest account with the same ID number exists
						if(!users.contains(newManager) && !users.contains(new Guest(newUserID, newUserName))){
							model.updateUserList(newManager);
							model.updateCurrentUser(newManager);
							//save new manager
							try {
								ManagerLoginFrame.this.storeHotelInformation();
							} catch (IOException e) {
								e.printStackTrace();
							}
							//TODO: Pass hotelModel into ManagerResHandler
							frame.dispose();
						}else//user exists: display error message
							JOptionPane.showMessageDialog(null, "This username is already taken: Please choose another one.");
					}
			
				});
		
		JLabel blank = new JLabel(" ");
		signUpPanel.add(blank);
		
		frame.add(signUpPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// will need to save hotel information after reservations are made
	public void storeHotelInformation() throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hotelInfo.data"));
		out.writeObject(model);
		out.close();
	}
}

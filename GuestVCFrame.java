import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuestVCFrame
{
	private HotelModel model;
	
	public GuestVCFrame(HotelModel hotelModel)
	{
		this.model = hotelModel;
		
		JFrame frame = new JFrame();
		frame.setSize(800, 300);
		frame.setLayout(new BorderLayout());
		
		JTextArea roomView = new JTextArea(10, 10);
		roomView.setText("Test");
		roomView.setEditable(false);
		frame.add(roomView, BorderLayout.CENTER);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
		JPanel roomInputPanel = new JPanel();
		roomInputPanel.setLayout(new FlowLayout());
		JLabel inputPrompt = new JLabel("Enter the reservation number to cancel: ");
		JTextField inputTextField = new JTextField(12);
		roomInputPanel.add(inputPrompt);
		roomInputPanel.add(inputTextField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton cancelButton = new JButton("Cancel Reservation");
		buttonPanel.add(cancelButton);
		
		JLabel blank = new JLabel(" ");
		
		contentPanel.add(blank);
		contentPanel.add(roomInputPanel);
		contentPanel.add(buttonPanel);
		
		frame.add(contentPanel, BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

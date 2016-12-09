import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuestViewFrame
{
	private HotelModel model;
	private JTextArea roomView;
	private ArrayList<Room> validRooms;
	
	public GuestViewFrame(HotelModel hotelModel)
	{
		this.model = hotelModel;
		validRooms = new ArrayList<Room>();
		JFrame frame = new JFrame();
		frame.setSize(800, 300);
		frame.setLayout(new BorderLayout());
		
		roomView = new JTextArea(10, 10);
		roomView.setText("Test");
		roomView.setEditable(false);
		JScrollPane scroller = new JScrollPane(roomView);
		updateRoomView();
		frame.add(scroller, BorderLayout.CENTER);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
		JPanel roomInputPanel = new JPanel();
		roomInputPanel.setLayout(new FlowLayout());
		JLabel inputPrompt = new JLabel("Enter the room number to reserve: ");
		JTextField inputTextField = new JTextField(12);
		roomInputPanel.add(inputPrompt);
		roomInputPanel.add(inputTextField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int roomNumber = 0;
				//make sure that the number entered is an integer
				try{
					roomNumber = Integer.parseInt(inputTextField.getText());
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Please enter a valid room number");
					return;
				}
				//check that number entered is an available room
				Reservation reservation = new Reservation(null, model.getCurrentStart(), model.getCurrentEnd(), model.getCurrentUser(), model.getTranscationID());
				if(model.addReservation(reservation, validRooms, roomNumber))
					JOptionPane.showMessageDialog(null, "Please enter a valid room number");
				else
					JOptionPane.showMessageDialog(null, "Reservation made");
						
			}
				
			
		});
		
		buttonPanel.add(confirmButton);
		
		JButton moreButton = new JButton("More Reservations?");
		moreButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				GuestCheckFrame guestCheckFrame = new GuestCheckFrame(model);
				frame.dispose();
			}
			
		});
		buttonPanel.add(moreButton);
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/* output receipt*/
				new printReceipt(model);
				frame.dispose();
			}
		});
		buttonPanel.add(doneButton);
		
		JLabel blank = new JLabel(" ");
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				GuestResHandler guestHandler = new GuestResHandler(model);
				frame.dispose();
				
			}
				
		});
		
		contentPanel.add(blank);
		contentPanel.add(roomInputPanel);
		contentPanel.add(buttonPanel);
		contentPanel.add(backButton);
		
		frame.add(contentPanel, BorderLayout.EAST);
		
		model.attachListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
			//	try {
				//	model.storeHotelInformation();
					updateRoomView();
					frame.repaint();
				/*} catch (IOException e1) {
					e1.printStackTrace();
				}*/
			}
			
		});
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void updateRoomView(){
		String rooms = "Available Rooms " + model.getCurrentStart() + "-" + model.getCurrentEnd() + "\n\n";
		Reservation reservation = new Reservation(null, model.getCurrentStart(), model.getCurrentEnd(), model.getCurrentUser(), model.getTranscationID());
		ArrayList<Reservation> reservations = null;
		validRooms = new ArrayList<Room>();
		boolean addRoom = true;
		for(Room r: model.getRoomMap().keySet()){
			//check to make sure room is correct preference
			if(r.getRoomType() != model.getCurrentRoomPref())
				addRoom = false;
			reservations = model.getRoomMap().get(r);
			//checks to see if reservation has a conflict with previous reservations
			for(Reservation res: reservations)
				if(reservation.checkHasConflict(res))
					addRoom = false;
			if(addRoom){
				validRooms.add(r);
				rooms += r.getRoomNumber() + "\n";
			}	
			addRoom = true;
		}	
		if(validRooms.isEmpty())
			rooms = "No available rooms for " + model.getCurrentStart() + "-" + model.getCurrentEnd();
		roomView.setText(rooms);
	}
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class GuestVCFrame
{
	private HotelModel model;
	private JTextArea roomView;
	private ArrayList<Reservation> reservations;
	
	public GuestVCFrame(HotelModel hotelModel)
	{
		this.model = hotelModel;
		reservations = new ArrayList<Reservation>();
		
		JFrame frame = new JFrame();
		frame.setSize(800, 300);
		frame.setLayout(new BorderLayout());
		
		roomView = new JTextArea(10, 10);
		updateRoomView();
		JScrollPane scroller = new JScrollPane(roomView);
		roomView.setEditable(false);
		frame.add(scroller, BorderLayout.CENTER);
		
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
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int reservationNumber = -1;
				//make sure that the number entered is an integer
				try{
					reservationNumber = Integer.parseInt(inputTextField.getText());
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Please enter a valid reservation number");
					return;
				}
				//check that number entered is a valid reservation number
				if(reservationNumber > - 1 && reservationNumber < reservations.size()){
					Reservation r = reservations.remove(reservationNumber);
					Guest guest = (Guest)model.getCurrentUser();
					guest.cancelReservation(r);
					//serialize data
					try {
						JOptionPane.showMessageDialog(null, "Reservation canceled");
						model.storeHotelInformation();
						updateRoomView();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}	
				else
					JOptionPane.showMessageDialog(null, "Please enter a valid room number");
			
			}
		});
		buttonPanel.add(cancelButton);
		
		JLabel blank = new JLabel(" ");
		
		contentPanel.add(blank);
		contentPanel.add(roomInputPanel);
		contentPanel.add(buttonPanel);
		
		frame.add(contentPanel, BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void updateRoomView() {
		Guest g = (Guest) model.getCurrentUser();
		reservations = g.getReservations();
		//order the reservations in terms of starting date first
		Comparator<Reservation> comparator = new Comparator<Reservation>(){

			@Override
			public int compare(Reservation o1, Reservation o2) {
				if(o1.getStartDate().compareTo(o2.getStartDate()) != 0)
					return o1.getStartDay().compareTo(o2.getStartDay());
				else if(o1.getStartDate().compareTo(o2.getStartDate()) != 0)
					return o1.getEndDay().compareTo(o2.getEndDay());
				return o1.getRoom().getRoomNumber() - o2.getRoom().getRoomNumber();
				
			}
			
		};
		Collections.sort(reservations, comparator);
		String s = "Current Reservations: \n\n";
		int count = 0;
		for(Reservation r: reservations)
			s += "Reservation Number:" + count++ + "\n" + r.toString() + "\n";
		roomView.setText(s);
		
	}
}

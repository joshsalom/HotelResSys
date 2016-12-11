import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Frame that allows the user to select the date for a reservation.
 * @author Alex Liu, Harita Shroff, Joshua Salom
 */
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
				String startDate = inTextField.getText();
				String endDate = outTextField.getText();
				if(checkValidDate(startDate, endDate)){
					model.updateCurrentStart(startDate);
					model.updateCurrentEnd(endDate);
					model.updateCurrentRoomPref(RoomType.ECONOMY);
					GuestViewFrame newGVF = new GuestViewFrame(model);
					frame.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Please enter a valid date");
			}

		});

		JButton luxButton = new JButton("$200");
		buttonPanel.add(luxButton);
		luxButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String startDate = inTextField.getText();
				String endDate = outTextField.getText();
				if(checkValidDate(startDate, endDate)){
					model.updateCurrentStart(startDate);
					model.updateCurrentEnd(endDate);
					model.updateCurrentRoomPref(RoomType.LUXURY);
					GuestViewFrame newGVF = new GuestViewFrame(model);
					frame.dispose();
				}	
				else
					JOptionPane.showMessageDialog(null, "Please enter a valid date");
			}

		});


		frame.add(checkPanel, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Checks to see whether the two dates are valid 
	 * @param startDate String that represents the starting date
	 * @param endDate String that represents the end date
	 * @return True if the two dates are valid
	 */
	public boolean checkValidDate(String startDate, String endDate){
		//check to see if date entered in format MM/DD/YYYY
		if (startDate.length() != 10 || endDate.length() != 10)
			return false;
		//check to make sure they used "/" to separate dates
		if(!startDate.substring(2, 3).equals("/") || !startDate.substring(5, 6).equals("/") 
				|| !endDate.substring(2, 3).equals("/") || !endDate.substring(5, 6).equals("/"))
			return false;
		// get month, day, year information
		String[] startDateInfo = startDate.split("/", -1);
		String[] endDateInfo = endDate.split("/", -1);
		String startMonth = startDateInfo[0];
		String startDay = startDateInfo[1];
		String startYear = startDateInfo[2];
		String endMonth = endDateInfo[0];
		String endDay = endDateInfo[1];
		String endYear = endDateInfo[2];
		// check to make sure input doesn't contain letters
		for (String s : startDateInfo)
			if (!s.matches("^-?\\d+$"))
				return false;
		for (String s : endDateInfo)
			if (!s.matches("^-?\\d+$"))
				return false;
		//parse into ints
		int sMonth = Integer.parseInt(startMonth);
		int sDay = Integer.parseInt(startDay);
		int sYear = Integer.parseInt(startYear);
		int eMonth = Integer.parseInt(endMonth);
		int eDay = Integer.parseInt(endDay);
		int eYear = Integer.parseInt(endYear);
		//check to make sure that month is 1-12 and day is 1-31
		if((sMonth < 1 || sMonth > 12) || (eMonth < 1 || eMonth > 12) 
				|| ((sDay < 1 || sDay > 31) || (eDay < 1 || eDay > 31)))
			return false;
		//check to make sure end date and start date are after current date
		GregorianCalendar cal = new GregorianCalendar();
		GregorianCalendar otherCal = new GregorianCalendar(sYear, sMonth - 1, sDay);
		GregorianCalendar thirdCal = new GregorianCalendar(eYear, eMonth - 1, eDay);
		if(cal.compareTo(otherCal) > 0 || cal.compareTo(thirdCal) >0)
			return false;
		// check to make sure end date is after the start date
		if(otherCal.getTime().compareTo(thirdCal.getTime()) >= 0)
			return false;
		//check that stay doesn't extend past 60 days
		if (daysBetween(otherCal.getTime(), thirdCal.getTime()) > 60)
			return false;
		return true;
	}

	/**
	 * Calculates the number of days between two given dates
	 * @param d1 First date
	 * @param d2 Second date
	 * @return Number of days between the two dates
	 */
	private int daysBetween(Date d1, Date d2){
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
}
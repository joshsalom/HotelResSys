import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * @author Harita
 *
 */
public class ManagerResHandler {

	public enum MONTHS {
		January, February, March, April, May, June, July, August, September, October, November, December;
	}
	private MONTHS[] monthArray = MONTHS.values();
	
	private HotelModel model;

	private ArrayList<JButton> daysButton = new ArrayList<JButton>();
	private int daysInMonth;
	
	public ManagerResHandler(HotelModel hotelModel) {
		this.model = hotelModel;
		daysInMonth = model.calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		// North Panel
		JLabel nlabel = new JLabel("Manager Interface");
		JPanel npanel = new JPanel();
		npanel.add(nlabel);

		// East Panel
		JTextPane roomDisplayPane = new JTextPane();
		roomDisplayPane.setPreferredSize(new Dimension(300, 150));
		roomDisplayPane.setEditable(false);
		
		JPanel epanel = new JPanel();
		epanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		JScrollPane dayScrollPane = new JScrollPane(roomDisplayPane);
		dayScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		epanel.add(dayScrollPane, constraints);
		JPanel buttonPanel = new JPanel();
		JButton viewButton = new JButton("VIEW");
		JButton saveButton = new JButton("SAVE");
		JButton quitButton = new JButton("QUIT");
		
		viewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Action for view
			}
		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Action for save
			}
		});
		
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		buttonPanel.add(viewButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(quitButton);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		epanel.add(buttonPanel, constraints);
		
		// West Panel		
		JPanel wpanel = new JPanel();
		wpanel.setLayout(new BorderLayout());
		
		JPanel mpanel = new JPanel();
		mpanel.setLayout(new GridLayout(0,7));
		
		JLabel mlabel = new JLabel();
		mlabel.setText(monthArray[model.calendar.get(Calendar.MONTH)] + " " + model.calendar.get(Calendar.YEAR));
		wpanel.add (mlabel, BorderLayout.NORTH);
		wpanel.add(new JLabel("       S             M             T             W             T              F             S"), BorderLayout.CENTER);
		wpanel.add(mpanel, BorderLayout.SOUTH);
		createButtons();
		for (int j = 1; j < dayOfWeek(1); j++) {
			JButton blank = new JButton();
			blank.setEnabled(false);
			mpanel.add(blank);
		}
		
		for (JButton day : daysButton) {
			mpanel.add(day);
		}
		
		
		// South Panel
		JPanel spanel = new JPanel();

		frame.add(npanel, BorderLayout.NORTH);
		frame.add(wpanel, BorderLayout.WEST);
		frame.add(epanel, BorderLayout.CENTER);
		frame.add(spanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);		
	}
	
	private void createButtons() {
		for (int i = 1; i <= daysInMonth; i++) {
			final int d = i;
			JButton day = new JButton(Integer.toString(d));
			day.setBackground(Color.WHITE);
	
			day.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//displayEvents(d);
					//showSelectedDates(d - 1);
					//saveButton.setEnabled(true);
					//viewButton.setEnabled(true);
					//quitButton.setEnabled(true);
				}
			});
			daysButton.add(day);
		}
	}
	
	public int dayOfWeek (int day) {
		model.calendar.set(Calendar.DAY_OF_MONTH, day);
		return model.calendar.get(Calendar.DAY_OF_WEEK);
	}
}
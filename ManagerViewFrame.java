import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Harita
 *
 */
public class ManagerViewFrame {

	private HotelModel model;
	
	public ManagerViewFrame(HotelModel hotelModel) {
		this.model = hotelModel;
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		// North Panel
		JLabel nlabel = new JLabel("Manager Interface");
		JPanel npanel = new JPanel();
		npanel.add(nlabel);

		// East Panel
		JTextPane roomDisplayPane = new JTextPane();
		roomDisplayPane.setPreferredSize(new Dimension(300, 128));
		roomDisplayPane.setEditable(false);
		
		JPanel epanel = new JPanel();
		JScrollPane dayScrollPane = new JScrollPane(roomDisplayPane);
		epanel.add(dayScrollPane);
		
		// West Panel		
		JPanel wpanel = new JPanel();
		wpanel.setLayout(new BorderLayout());
		 
		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.attachListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				if (target.getModel().getValueAt(row, column) != null && row != 0) {
					calendarPanel.setCalendarDay((int) target.getModel().getValueAt(row, column));
					calendarPanel.repaint();
				}

			}
		});
		
		JPanel calButtonPanel = new JPanel();
		calButtonPanel.setLayout(new BorderLayout());
		//buttons for advancing calendar by month
		JPanel topButtons = new JPanel();
		topButtons.add(new JLabel("Month:"));
		JButton leftMonthArrow = new JButton("<");
		leftMonthArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				calendarPanel.goPreviousMonth();
				calendarPanel.repaint();
			}
			
		});
		JButton rightMonthArrow = new JButton(">");
		rightMonthArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				calendarPanel.goNextMonth();
				calendarPanel.repaint();
			}
			
		});
		topButtons.add(leftMonthArrow);
		topButtons.add(rightMonthArrow);
		
		//panel for advancing calendar by year
		JPanel bottomButtons = new JPanel();
		bottomButtons.add(new JLabel("Year:"));
		JButton leftYearArrow = new JButton("<");
		leftYearArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				calendarPanel.goPreviousYear();
				calendarPanel.repaint();
			}
			
		});
		JButton rightYearArrow = new JButton(">");
		rightYearArrow.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				calendarPanel.goNextYear();
				calendarPanel.repaint();
			}
			
		});
		bottomButtons.add(leftYearArrow);
		bottomButtons.add(rightYearArrow);
		
		calButtonPanel.add(topButtons, BorderLayout.NORTH);
		calButtonPanel.add(bottomButtons, BorderLayout.SOUTH);
		
		wpanel.add(calendarPanel, BorderLayout.CENTER);
		
		// South Panel
		JPanel spanel = new JPanel();
		spanel.add(calButtonPanel, BorderLayout.SOUTH);

		frame.add(npanel, BorderLayout.NORTH);
		frame.add(wpanel, BorderLayout.WEST);
		frame.add(epanel, BorderLayout.CENTER);
		frame.add(spanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);		
		
	}
	
	/**
	 * Panel that represents a month view of a calendar
	 */
	 class CalendarPanel extends JPanel {

		private GregorianCalendar cal;
		private final int NUM_COLS = 7;
		private final int NUM_ROWS = 7;
		private JTable table;
		private JLabel titleLabel; // shows the month and year
		private DefaultTableModel model;
		private final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		private final String[] DAYS = { "S", "M", "T", "W", "T", "F", "S" };
		private int currentDayCol;

		CalendarPanel() {
			cal = new GregorianCalendar();
			table = new JTable(NUM_ROWS, NUM_COLS) {
				@Override
				public boolean getRowSelectionAllowed() { return false; }

				@Override
				public boolean getColumnSelectionAllowed() {
					return false;
				}

				@Override
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					TableCellRenderer rend = new CalendarPanel.BorderlessTableCellRenderer();
					Component cell = super.prepareRenderer(rend, row, column);
					Object o = table.getModel().getValueAt(row, column);
					// this.setBorder(DefaultListCellRenderer.noFocusBorder);
					if (o != null && isNumeric(o.toString()) && (Integer) o == cal.get(Calendar.DAY_OF_MONTH)) {
						cell.setBackground(Color.BLUE);

					} else {
						cell.setBackground(Color.WHITE);
					}
					return cell;
				}
			};

			model = new DefaultTableModel(NUM_ROWS, NUM_COLS) {

				@Override
				public boolean isCellEditable(int row, int col) {
					return false;
				}

			};

			table.setModel(model);
			this.setLayout(new BorderLayout());
			titleLabel = new JLabel();
			this.add(titleLabel, BorderLayout.NORTH);
			this.add(table, BorderLayout.CENTER);
			initialize();
		}

		/**
		 * Attaches a MouseAdapter to the table
		 * @param m The MouseAdapter that is to be attached
		 */
		public void attachListener(MouseAdapter m) {
			table.addMouseListener(m);
		}

		/**
		 * Sets the calendar day of the month to the given day
		 * Precondition: The day has to be a valid calendar day
		 * Postcondition: The calendar is set to the given day
		 * @param day The day that the calendar is to be changed to
		 */
		public void setCalendarDay(int day) {
			this.cal.set(Calendar.DAY_OF_MONTH, day);
		}

		private void initialize() {
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setSelectionBackground(Color.GRAY);
			// set label background as white
			titleLabel.setOpaque(true);
			titleLabel.setBackground(Color.WHITE);
			// edit table
			table.setRowSelectionAllowed(false);
			table.setColumnSelectionAllowed(false);
			table.setCellSelectionEnabled(true);
			for (int x = 0; x < NUM_COLS; x++)
				table.getColumnModel().getColumn(x).setMaxWidth(25);
			drawCalendar();
		}

		private void drawCalendar() {
			// reset table
			model.setRowCount(0);
			model.setRowCount(NUM_ROWS);
			titleLabel.setText(MONTHS[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR));
			// calculate day of week of the first day
			int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int counter = 1;
			GregorianCalendar temp = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
			int firstDayOfWeek = temp.get(Calendar.DAY_OF_WEEK) - 1;
			boolean firstDay = true;
			// initialize days of week
			for (int x = 0; x < DAYS.length; x++)
				model.setValueAt(DAYS[x], 0, x);
			// initialize days of month
			for (int row = 1; row < NUM_ROWS; row++) {
				for (int col = 0; col < NUM_COLS; col++) {
					if (firstDay) {
						col = firstDayOfWeek;
						firstDay = false;
					}
					if (cal.get(Calendar.DAY_OF_MONTH) == counter)
						this.currentDayCol = col;
					if (counter > numDays)
						break;
					model.setValueAt(counter, row, col);
					counter++;
				}
			}
		}

		/**
		 * Gets the current calendar
		 * @return current GregorianCalendar
		 */
		public GregorianCalendar getCalendar() {
			return this.cal;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawCalendar();
			table.changeSelection(cal.get(Calendar.WEEK_OF_MONTH) + 1, currentDayCol, false, false);
		}

		/**
		 * Advances the calendar forward one month
		 */
		public void goNextMonth() {
			cal.add(Calendar.MONTH, 1);
		}

		/**
		 * Moves the calendar back one month
		 */
		public void goPreviousMonth() {
			cal.add(Calendar.MONTH, -1);
		}
		
		/**
		 * Advances the calendar forward one year
		 */
		public void goNextYear() {
			cal.add(Calendar.YEAR, 1);
		}

		/**
		 * Moves the calendar back one year
		 */
		public void goPreviousYear() {
			cal.add(Calendar.YEAR, -1);
		}

		private boolean isNumeric(String str) {
			return str.matches("\\d+");
		}

		/**
		 * Custom table cell renderer that draws cells with no border.
		 */
		public class BorderlessTableCellRenderer extends DefaultTableCellRenderer {

			@Override
		    public Component getTableCellRendererComponent(final JTable table, final Object value,final boolean isSelected, final boolean hasFocus, final int row, final int col) {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, false, row, col);
		        return c;
		    }
		}    
	}
	
	
}
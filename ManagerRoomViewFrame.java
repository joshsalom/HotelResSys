import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Frame that represents the room view for a manager.
 */
public class ManagerRoomViewFrame
{
	private HotelModel model;
	private JTextArea roomView;
	private JTable table;
	private JFrame frame;
	private DefaultTableModel tableModel;
	private int selectedRoomNumber;
	private final int FRAME_HEIGHT = 340;
	
	@SuppressWarnings("serial")
	public ManagerRoomViewFrame(HotelModel hotelModel)
	{
		selectedRoomNumber = 0;
		this.model = hotelModel;
		
		frame = new JFrame();
		frame.setSize(800, FRAME_HEIGHT);
		frame.setLayout(new BorderLayout());
		
		table = new JTable(4, 5) {
			@Override
			public boolean getRowSelectionAllowed() { return false; }

			@Override
			public boolean getColumnSelectionAllowed() {
				return false;
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component cell = super.prepareRenderer(new DefaultTableCellRenderer(), row, column);
				Object o = table.getModel().getValueAt(row, column);
				if (o != null && (Integer)o == selectedRoomNumber) {
					cell.setBackground(Color.BLUE);

				} else {
					cell.setBackground(Color.WHITE);
				}
				return cell;
			}
		};

		tableModel = new DefaultTableModel(4, 5) {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};

		table.setModel(tableModel);
		initializeTable();
		table.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent e){
				JTable target = (JTable) e.getSource();
				selectedRoomNumber = (int) tableModel.getValueAt(target.getSelectedRow(), target.getSelectedColumn());
				for(Room room: model.getRoomMap().keySet())
					if(room.getRoomNumber() == selectedRoomNumber)
						updateRoomView(room);
					
			}
		});
		
		roomView = new JTextArea(10, 10);
		JScrollPane scroller = new JScrollPane(roomView);
		scroller.setPreferredSize(new Dimension(375, 320));
		
		roomView.setEditable(false);
		
		frame.add(table, BorderLayout.WEST);
		frame.add(scroller, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	/**
	 * Initializes the table to the correct format
	 */
	private void initializeTable(){
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		//make table uneditable
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		//change row width 
		table.setRowHeight(320 / table.getRowCount());
		//add room numbers to table
		int num = 1;
		for(int row = 0; row < tableModel.getRowCount(); row++)
			for(int col = 0; col < tableModel.getColumnCount(); col++)
				tableModel.setValueAt(new Integer(num++), row, col);
	}

	/**
	 * Updates the room view with the selected room
	 * @param r Room that is selected
	 */
	private void updateRoomView(Room r) {
		//room information
		String s = "Room number: " + r.getRoomNumber() + "\n"
				+ "   Room type: " + r.getRoomType() + "\n"
				+ "   Room price: " + r.getRoomPrice() + "\n";
		ArrayList<Reservation> reservations = model.getRoomMap().get(r);
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
		s += "\nCurrent Reservations:\n";
		
		for(Reservation res: reservations)
			s += "   " + res.getUser().getName() + ": " + res.getStartDate() + "-" + res.getEndDate() + "\n";
		roomView.setText(s);
	}
}

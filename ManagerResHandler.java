import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerResHandler{
	
	private HotelModel model;
	
	public ManagerResHandler(HotelModel hotelModel)
	{
		this.model = hotelModel;
		JFrame frame = new JFrame();
		frame.setSize(400, 140);
		frame.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Hello, Manager!");

		JLabel label = new JLabel("As a Manager, what would you like to do?");
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());

		JPanel welcomeLabelPanel = new JPanel();
		welcomeLabelPanel.add(welcomeLabel);

		welcomeLabelPanel.add(welcomeLabel);
		frame.add(welcomeLabelPanel, BorderLayout.NORTH);

		labelPanel.add(label);
		frame.add(labelPanel, BorderLayout.CENTER);

		JButton loadButton = new JButton("Load Existing Reservations");
		loadButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						try{
							ObjectInputStream in = new ObjectInputStream(new FileInputStream("hotelInfo.data"));
							model = (HotelModel) in.readObject();
							in.close(); 
							model.resetListeners(); //ArrayList<ChangeListener> is not serializable
						}
						catch(IOException | ClassNotFoundException e){
							
						}
					}
			
				});

		JButton viewButton = new JButton("View Information");
		viewButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						ManagerViewFrame newViewCancelFrame = new ManagerViewFrame(model);
						ManagerRoomViewFrame roomViewFrame = new ManagerRoomViewFrame(model);
						frame.dispose();
					}
			
				});
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.storeHotelInformation();
				} catch (IOException e1) {
				}
				System.exit(0);
				
			}
			
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(loadButton);
		buttonPanel.add(viewButton);
		buttonPanel.add(quitButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
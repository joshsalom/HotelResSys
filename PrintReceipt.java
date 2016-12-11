import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
/**
 * printReceipt implements generic receipt frame, strategy pattern will be used
 * for Simple and Comprehensive receipts.
 * @author Alex Liu, Harita Shroff, Joshua Salom
 */
public class PrintReceipt {
	/**
	 * Constructor for the class.
	 * @param model Hotel model with global data about the hotel and user
	 */
	PrintReceipt(HotelModel model)
	{		
		final JFrame frame = new JFrame();
		frame.setTitle("Hotel Receipt");
		frame.setSize(600,400);

		JPanel nPanel = new JPanel();
		JLabel label = new JLabel("Account:");
		nPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		nPanel.add(label);

		//print buttons
		JPanel centerPanel = new JPanel();
		JButton simpleButton = new JButton("Print Simple Receipt");
		JButton comprehensiveButton = new JButton("Print Comprehensive Receipt");

		ButtonGroup group = new ButtonGroup();
		group.add(simpleButton);
		group.add(comprehensiveButton);


		centerPanel.add(simpleButton);
		centerPanel.add(comprehensiveButton);

		Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY);
		centerPanel.setBorder(lineBorder1);

		//display receipt
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new BorderLayout());
		sPanel.add(centerPanel, BorderLayout.NORTH);
		final JTextArea textArea = new JTextArea();
		JScrollPane scroller = new JScrollPane(textArea);
		sPanel.add(scroller, BorderLayout.CENTER);

		JButton closeButton = new JButton("Done");
		sPanel.add(closeButton, BorderLayout.SOUTH);
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				GuestResHandler newGVF = new GuestResHandler(model);
				frame.dispose();
			}
		});

		//button actionsListeners
		simpleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//client program
				ReceiptFormat r = new SimpleReceipt(model);
				textArea.setText(setFormat(r) );
			}
		});

		comprehensiveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{  //client program
				ReceiptFormat r = new ComprehensiveReceipt(model);
				textArea.setText(setFormat(r));
			}
		});

		frame.add(nPanel, BorderLayout.NORTH);
		frame.add(sPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * 
	 * @param r object of type interface.
	 * @return returns the interface method
	 */
	private String setFormat(ReceiptFormat r)
	{
		return r.format();
	}
}
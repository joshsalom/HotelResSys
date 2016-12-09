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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class printReceipt {

	printReceipt(HotelModel model)
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
		sPanel.add(textArea, BorderLayout.CENTER);

		JButton closeButton = new JButton("Back");
		sPanel.add(closeButton, BorderLayout.SOUTH);
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				GuestViewFrame newGVF = new GuestViewFrame(model);
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

	private String setFormat(ReceiptFormat r)
	{
		return r.format();
	}
}
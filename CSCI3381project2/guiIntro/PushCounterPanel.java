package guiIntro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

import project1.ShowWeek;
import project1.Shows;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PushCounterPanel extends JPanel{
	private int count;
	private JButton push;
	private JLabel lblPushes;
	private JTextField movieTitle;
	private JLabel lblNewLabel;
	private Shows allData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton;
	private ImageIcon image;
	private int x, y;
	
	public PushCounterPanel() {
		allData = new Shows("allData","./project1/netflixAllWeeksGlobalProcessed.txt");
		
		//image = new ImageIcon (this.getClass().getResource("/Dog.gif"));
		image = new ImageIcon (this.getClass().getResource("/Fatherhood.jpg"));
		
		x = 475;
		y = 150;
		
		count = 0;
		push = new JButton ("Push Me!");
		push.setBounds(68, 54, 105, 23);
		push.addActionListener(new ButtonListener());
		lblPushes = new JLabel ("Pushes: 0");
		lblPushes.setBounds(214, 80, 105, 23);
		lblPushes.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		setLayout(null);

		add (push);
		add(lblPushes);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(800, 700));
		
		JButton btnClearCount = new JButton("Clear Count");
		btnClearCount.setBounds(68, 101, 105, 23);
		btnClearCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count=0;
				lblPushes.setText ("Pushes: " + count);
			}

		});
		add(btnClearCount);

		movieTitle = new JTextField("2021-07-04");
		movieTitle.setBounds(68, 210, 149, 20);
		add(movieTitle);
		movieTitle.setColumns(10);

		lblNewLabel = new JLabel("Enter Week:");
		lblNewLabel.setBounds(68, 198, 105, 14);
		add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 143, 215, 197);
		add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText(allData.toString());

		JComboBox Movies = new JComboBox();
		ArrayList<ShowWeek> moviesInWeek = allData.getOneWeek("2021-07-04");

		String [] data = new String[moviesInWeek.size()];
		int index = 0;
		for (ShowWeek sw : moviesInWeek){
			data[index] = sw.getShowTitle();
			index++;
		}

		Movies.setModel(new DefaultComboBoxModel(data));
		Movies.setBounds(68, 282, 149, 22);
		add(Movies);

		JLabel lblNewLabel_1 = new JLabel("Shows:");
		lblNewLabel_1.setBounds(68, 270, 46, 14);
		add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Get Shows");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ShowWeek> moviesInWeek = allData.getOneWeek(movieTitle.getText());
				if(moviesInWeek != null && moviesInWeek.size() > 0) {
					String [] data = new String[moviesInWeek.size()];
					int index = 0;
					for (ShowWeek sw : moviesInWeek){
						data[index] = sw.getShowTitle();
						index++;
					}
					Movies.setModel(new DefaultComboBoxModel(data));
				}
			}
		});
		btnNewButton.setBounds(68, 230, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Do Edit!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected())
				{
					
				}
			}
		});
		btnNewButton_1.setBounds(128, 315, 89, 23);
		add(btnNewButton_1);
		
		rdbtnNewRadioButton = new JRadioButton("Title");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(13, 317, 109, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnWeek = new JRadioButton("Week");
		buttonGroup.add(rdbtnWeek);
		rdbtnWeek.setBounds(13, 392, 109, 23);
		add(rdbtnWeek);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Purged");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(13, 355, 109, 23);
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("English?");
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(13, 431, 109, 23);
		add(rdbtnNewRadioButton_3);
	}

	public void doClose() {
		allData.doWrite("./textwrite.csv");
	}

	public void paintComponent (Graphics page)
	{
		super.paintComponent (page);
		image.paintIcon (this, page, x, y);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event){
			count++;
			lblPushes.setText ("Pushes: " + count);
		}
	}
}

package project2;

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
import javax.swing.JCheckBox;

public class ControlPanel extends JPanel{
	private int count;
	private JTextField movieTitle;
	private JLabel lblNewLabel;
	private Shows allData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton;
	private ImageIcon image;
	private int x, y;

	public ControlPanel() {
		allData = new Shows("allData","./project1/netflixAllWeeksGlobalProcessed.txt");

		//image = new ImageIcon (this.getClass().getResource("/Dog.gif"));
		//image = new ImageIcon (this.getClass().getResource("/Fatherhood.jpg"));

		count = 1;
		x = 475;
		y = 150;

		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(800, 700));

		//set the combo box
		movieTitle = new JTextField("2021-07-04");
		movieTitle.setBounds(68, 210, 149, 20);
		add(movieTitle);
		movieTitle.setColumns(10);

		//ask to enter week for combo box
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

		//gets shows based on week
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

		//Radio Buttons
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

		//used for displaying movie posters
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./Dog.gif"));
		lblNewLabel_2.setBounds(471, 33, 319, 494);
		add(lblNewLabel_2);
		
		//Goes through images
		JButton btnNewButton_3 = new JButton("Next");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(count > 9)
				{
					count = 1;
				}
				if(count == 1)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Fatherhood.jpg"));
				}	
				if(count == 2)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Fatherhood.jpg"));
				}
				if(count == 3)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Dog.gif"));
				}
				count++;
			}
		});
		btnNewButton_3.setBounds(667, 666, 89, 23);
		add(btnNewButton_3);
		
		JLabel lblNewLabel_3 = new JLabel("Recommended Movies:");
		lblNewLabel_3.setBounds(496, 8, 225, 14);
		add(lblNewLabel_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Night Mode");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chckbxNewCheckBox.setBounds(13, 484, 93, 21);
		add(chckbxNewCheckBox);
		
		


		

	}

	public void doClose() {
		allData.doWrite("./textwrite.csv");
	}
}

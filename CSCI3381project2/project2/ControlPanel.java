package project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel{
	private int count;
	private JTextField movieTitle;
	private JLabel lblNewLabel;
	private Shows allData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton, rdbtnUnpurge, rdbtnNewRadioButton_1;
	private ImageIcon image;


	public ControlPanel() {
		allData = new Shows("allData","./project1/netflixAllWeeksGlobalProcessed.txt");

		//image = new ImageIcon (this.getClass().getResource("/Dog.gif"));
		//image = new ImageIcon (this.getClass().getResource("/Fatherhood.jpg"));

		count = 1;

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

		//Scroll pane for text area
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 143, 215, 197);
		add(scrollPane);

		//text area with shows 
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText(allData.toString());

		//gets shows for one week for combo box
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

		//Radio Buttons
		rdbtnNewRadioButton = new JRadioButton("Purge Show");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(13, 317, 109, 23);
		add(rdbtnNewRadioButton);

		rdbtnUnpurge = new JRadioButton("Unpurge Show");
		buttonGroup.add(rdbtnUnpurge);
		rdbtnUnpurge.setBounds(13, 392, 109, 23);
		add(rdbtnUnpurge);

		rdbtnNewRadioButton_1 = new JRadioButton("Add Show");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(13, 355, 109, 23);
		add(rdbtnNewRadioButton_1);

		//Do edit, allows button to purge, unpurge and add shows
		JButton btnNewButton_1 = new JButton("Do Edit!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnNewRadioButton.isSelected() == true)
				{
					String Purge = JOptionPane.showInputDialog("Enter what show to Purge");
					allData.purgeShow(Purge);
				}
				else if (rdbtnUnpurge.isSelected() == true)
				{
					String Unpurge = JOptionPane.showInputDialog("Enter what show to Unpurge");
					allData.unPurgeShow(Unpurge);
				}
				else if (rdbtnNewRadioButton_1.isSelected() == true)
				{
					String week = JOptionPane.showInputDialog("Enter Week");
					String catogory = JOptionPane.showInputDialog("Enter Catogory");
					String rank = JOptionPane.showInputDialog("Enter Rank");
					String Title = JOptionPane.showInputDialog("Enter Title");
					String Season = JOptionPane.showInputDialog("Enter Season");
					String hours = JOptionPane.showInputDialog("Enter Hours");
					String WeeksTop10 = JOptionPane.showInputDialog("Enter Weeks in Top 10");

					ShowWeek Add = new ShowWeek(week, catogory, rank, Title, Season, hours,WeeksTop10);
					allData.addShowWeek(Add);
				}

			}

		});
		btnNewButton_1.setBounds(128, 315, 89, 23);
		add(btnNewButton_1);

		//used for displaying movie posters
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./netflix-intro-netflix.gif"));
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
					lblNewLabel_2.setIcon(new ImageIcon("./FearStreet.png"));
				}
				if(count == 3)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Wish_Dragon.png"));
				}
				if(count == 4)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./TheIceRoad.jpeg"));
				}
				if(count == 5)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Good_on_Paper_poster.jpg"));
				}
				if(count == 6)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Kung_Fu_Panda_3_poster.jpg"));
				}
				if(count == 7)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Warcraft_Teaser_Poster.jpg"));
				}
				if(count == 8)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./Fatale_film_poster.png"));
				}
				if(count == 9)
				{
					lblNewLabel_2.setIcon(new ImageIcon("./MitchellsMachinesPoster.jpg"));
				}

				count++;
			}
		});
		btnNewButton_3.setBounds(667, 666, 89, 23);
		add(btnNewButton_3);

		JLabel lblNewLabel_3 = new JLabel("Recommended Movies:");
		lblNewLabel_3.setBounds(531, 33, 225, 14);
		add(lblNewLabel_3);

		//Night mode toggle
		JCheckBox chckbxNewCheckBox = new JCheckBox("Night Mode");
		chckbxNewCheckBox.setSelected(false);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected() == true)
				{
					setBackground(Color.black);
					lblNewLabel.setForeground(Color.WHITE);
					lblNewLabel_1.setForeground(Color.WHITE);
					lblNewLabel_3.setForeground(Color.WHITE);
				}
				else
				{
					setBackground(Color.LIGHT_GRAY);
					lblNewLabel.setForeground(Color.BLACK);
					lblNewLabel_1.setForeground(Color.BLACK);
					lblNewLabel_3.setForeground(Color.BLACK);

				}
			}
		});
		chckbxNewCheckBox.setBounds(13, 484, 93, 21);
		add(chckbxNewCheckBox);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("./netflix.gif"));
		lblNewLabel_4.setBounds(-76, 8, 431, 140);
		add(lblNewLabel_4);






	}

	public void doClose() {
		allData.doWrite("./textwrite.csv");
	}
}

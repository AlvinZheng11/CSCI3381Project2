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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 215, 187);
		add(panel);

		JButton btnNewButton_2 = new JButton("Prev");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count--;
				System.out.println(count);
				if (count < 1)
				{
					count = 10;
				}
			}
		});
		btnNewButton_2.setBounds(523, 666, 89, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Next");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				System.out.println(count);
				if(count > 9)
				{
					count = 0;
				}
			}
		});
		btnNewButton_3.setBounds(667, 666, 89, 23);
		add(btnNewButton_3);


		if(count == 1)
		{
			image = new ImageIcon (this.getClass().getResource("/Fatherhood.jpg"));
			repaint();
		}	
		if(count == 2)
		{
			image = new ImageIcon (this.getClass().getResource("/Fatherhood.jpg"));
			repaint();
		}
		if(count == 3)
		{
			image = new ImageIcon (this.getClass().getResource("/Dog.gif"));
			repaint();
		}


	}

	public void doClose() {
		allData.doWrite("./textwrite.csv");
	}

	public void paintComponent (Graphics page)
	{
		super.paintComponent (page);
		image.paintIcon (this, page, x, y);
	}
}

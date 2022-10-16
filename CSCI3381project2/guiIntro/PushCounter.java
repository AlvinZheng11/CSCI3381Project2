package guiIntro;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class PushCounter {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Push Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PushCounterPanel panel = new PushCounterPanel();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				panel.doClose();
			}

		});
	}

}
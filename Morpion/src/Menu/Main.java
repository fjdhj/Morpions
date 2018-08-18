package Menu;


import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Morpion");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(null);
		
		JLabel title = new JLabel("Jeu du morpion");
		title.setFont(new Font("Arial", Font.BOLD, 20));

		
		
		frame.setVisible(true);
		
	}

}

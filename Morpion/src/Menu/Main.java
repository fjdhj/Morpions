package Menu;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	
	private static JLabel title = new JLabel("Jeu du morpion");
	
	private static JButton IA = new JButton("Joueur VS IA");
	private static JButton player = new JButton("Joueur VS Joueur");
	
	private static JPanel PANtitle = new JPanel();
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Morpion");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		
		title.setFont(new Font("Arial", Font.BOLD, 20));
		PANtitle.add(title);
		
		frame.add(PANtitle, BorderLayout.NORTH);
		
		/*
		 UN TRES GROS TEST 2
		 */
		
		
		// UN PLUS GROS TEST ENCORE
		frame.setVisible(true);
		
	}

}

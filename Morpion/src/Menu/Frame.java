package Menu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import renderer.DisplayException;
import renderer.MasterRenderer;

public class Frame extends JFrame{
	
	private static JLabel title = new JLabel("Jeu du morpion");
	
	private static JButton IA = new JButton("Joueur VS IA");
	private static JButton player = new JButton("Joueur VS Joueur");
	
	private static JPanel PANtitle = new JPanel();
	private static JPanel PANbutton = new JPanel();
	
	private MasterRenderer renderer;
	
	public Frame() {
		
		this.setTitle("Morpion");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		
		title.setFont(new Font("Arial", Font.BOLD, 20));
		PANtitle.add(title);
		
		IA.addActionListener(new Listener());
		
		
		PANbutton.add(IA);
		PANbutton.add(player);
		
		this.add(PANtitle, BorderLayout.NORTH);
		this.add(PANbutton, BorderLayout.CENTER);
		
		
		this.setVisible(true);
		
		renderer = new MasterRenderer(this);
		
	}
	
	public class Listener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			
			
		}

	}
}

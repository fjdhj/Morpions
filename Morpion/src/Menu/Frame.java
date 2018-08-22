package Menu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gamelogic.GameLogic;
import gamelogic.InputsManager;
import gamelogic.PvP;
import ia.IAcore;
import ia.InputsManagerIA;
import ia.PvIA;
import objects.Croix;
import objects.Jeton;
import objects.Rond;
import renderer.MasterRenderer;

public class Frame extends JFrame{
	
	private static JLabel title = new JLabel("Jeu du morpion");
	
	private static JButton IA = new JButton("Joueur VS IA");
	private static JButton player = new JButton("Joueur VS Joueur");
	
	private static JPanel PANtitle = new JPanel();
	private static JPanel PANbutton = new JPanel();
	private static JPanel PANglobal = new JPanel();
	
	private MasterRenderer renderer;
	private boolean PanelActive = false;
	private InputsManager inputManager;
	private GameLogic gamemode;
	
	public Frame() {
		
		this.setTitle("Morpion");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		PANglobal.setLayout(new BorderLayout());
		
		
		title.setFont(new Font("Arial", Font.BOLD, 20));
		PANtitle.add(title);
		
		IA.addActionListener(new IAListener());
		player.addActionListener(new playerListener());
		
		
		PANbutton.add(IA);
		PANbutton.add(player);
		
		PANglobal.add(PANtitle, BorderLayout.NORTH);
		PANglobal.add(PANbutton, BorderLayout.CENTER);
		
		this.setContentPane(PANglobal);
		
		this.setVisible(true);

		renderer = new MasterRenderer(this);	

}
	//pour passer du menu principal au Panel de rendu
	private void switchPane() {
		
		if(!PanelActive) {
			PanelActive = true;
		renderer.activateRenderPane();
		}else{
			PanelActive = false;
			this.setContentPane(PANglobal);
		}

}

	private void StartPvIA() {
		gamemode = new PvIA();
		inputManager = new InputsManager(renderer.getGraphicPane(), gamemode);
		
		InputsManagerIA IAinputs = new InputsManagerIA(gamemode);
		IAcore IA = new IAcore(IAinputs, gamemode);
		IA.start();
		
	}
	
	private void StartPvP() {
		 gamemode = new PvP();
		 inputManager = new InputsManager(renderer.getGraphicPane(), gamemode);
		}
	class IAListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			switchPane();
			StartPvIA();
		}
	}
	class playerListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				switchPane();
				StartPvP();
			}
}
}

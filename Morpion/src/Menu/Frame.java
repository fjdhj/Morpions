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
import ia.LearningIAcore;
import ia.PvIA;
import objects.Croix;
import objects.Jeton;
import objects.Rond;
import renderer.MasterRenderer;

public class Frame extends JFrame{
	
	public static final int PLAYER_CROIX_ID = -1;
	public static final int PLAYER_ROND_ID = 1;
	
	private static JLabel title = new JLabel("Jeu du morpion");
	
	private static JButton IA = new JButton("Joueur VS IA");
	private static JButton player = new JButton("Joueur VS Joueur");
	private static JButton IAvsIA = new JButton("IA VS IA");
	
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
		IAvsIA.addActionListener(new IAvIAListener());
		
		
		PANbutton.add(IA);
		PANbutton.add(player);
		PANbutton.add(IAvsIA);
		
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
	private void StartIAvIA() {
		gamemode = new PvIA();
		
		InputsManagerIA IAinputs = new InputsManagerIA(gamemode, PLAYER_ROND_ID);
		LearningIAcore IA = new LearningIAcore(IAinputs, gamemode,PLAYER_ROND_ID,1);
		IA.start();
		
		InputsManagerIA IA2inputs = new InputsManagerIA(gamemode, PLAYER_CROIX_ID);
		LearningIAcore IA2 = new LearningIAcore(IA2inputs, gamemode,PLAYER_CROIX_ID,0);
		IA2.start();
		
	}

	private void StartPvIA() {
	 	gamemode = new PvIA();
		inputManager = new InputsManager(renderer.getGraphicPane(), gamemode,PLAYER_ROND_ID);
		
		InputsManagerIA IA2inputs = new InputsManagerIA(gamemode, PLAYER_CROIX_ID);
		LearningIAcore IA2 = new LearningIAcore(IA2inputs, gamemode,PLAYER_CROIX_ID);
		IA2.start();
		
	}
	
	private void StartPvP() {
		 gamemode = new PvP();
		 inputManager = new InputsManager(renderer.getGraphicPane(), gamemode, PLAYER_ROND_ID);
		}
	class IAvIAListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			switchPane();
			StartIAvIA();
		}
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

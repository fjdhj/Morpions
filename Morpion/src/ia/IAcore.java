package ia;

import java.awt.Color;
import java.util.ArrayList;

import gamelogic.GameLogic;
import objects.Jeton;

public class IAcore extends Thread{
	
	private boolean Running;
	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private int PLAYER_ID = InputsManagerIA.PLAYER_ID;
	private int IdTurn;
	private int gameState[][];
	
	public IAcore(InputsManagerIA IAinputs, GameLogic gamemode) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		IdTurn = gamemode.getIdTurn();
	}

	/*
	 * Joueur : rond ROUGE (joueur n°2)
	 * IA : croix BLEU (joueur n°1)
	 * 
	 */


	@Override
	public void run() {
		Running = true;
		while(Running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(IdTurn != PLAYER_ID) {
				
				int gameState[][] = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
				
				for(Jeton jeton:JetonList) {
					int x = jeton.getX();
					int y = jeton.getY();
					Color color = jeton.getCouleur();
					
					if(color.getRed() == 255) { //Si pion joueur
						gameState[x-1][y-1] = 2;
						
					}else { //Si pion IA
						gameState[x-1][y-1] = 1;
						
					}
					
					
					
				}
				
				for(int y = 0; y < 3; y++) {
					for(int i = 0; i < 3; i++) {
						
					}
				}
				
				IAinputs.pressACase(1, 1);
				IAinputs.pressACase(2, 1);
				IAinputs.pressACase(3, 1);
				
				
			}
		}		
	}

}

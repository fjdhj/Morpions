package ia;

import java.awt.Color;
import java.util.ArrayList;

import gamelogic.GameLogic;
import objects.*;

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
				int max = -1000;
				int sim, maxX, maxY;
				int profondeur = 3;
				
				
				for(Jeton jeton:JetonList) {
					int x = jeton.getX();
					int y = jeton.getY();
					Color color = jeton.getCouleur();
					
					if(jeton instanceof Rond) { //Si pion joueur (rond)
						gameState[x-1][y-1] = 2;
						
					}else { //Si pion IA (croix)
						gameState[x-1][y-1] = 1;
						
					}
					
					
					
				}
				
				for(int y = 0; y < 3; y++) {
					for(int x = 0; x < 3; x++) {
						
						if(gameState[x][y] == 0) { //Si la case est vide
							
							gameState[x][y] = 1; //On simul le pion
							
							sim = Simu.Min(gameState, profondeur-1); // On simul ce que pourait joueur le joueur
							
							
						}
						
					}
				}
				
				IAinputs.pressACase(1, 1);
				IAinputs.pressACase(2, 1);
				IAinputs.pressACase(3, 1);
				
				
			}
		}		
	}

}

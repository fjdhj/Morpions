package ia;

import java.awt.Color;
import java.util.ArrayList;

import gamelogic.GameLogic;
import objects.*;

public class IAcore extends Thread{
	
	private boolean Running;
	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private int PLAYER_ID;
	private int IdTurn;
	private int gameState[][];
	private GameLogic gamemode;

	public IAcore(InputsManagerIA IAinputs, GameLogic gamemode, int playerCroixId) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		this.gamemode = gamemode;
		IdTurn = gamemode.getIdTurn();
		this.PLAYER_ID = playerCroixId;
	}
	
	private void getTurn() {
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
			//pour actualiser la variable idTurn, doit etre execut� dans la boucle avant tout traitement

			getTurn();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

      if(IdTurn == PLAYER_ID) {

				
				int gameState[][] = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
				int max = -10000;
				int sim; 
				int maxX = 1, maxY = 1;
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
							
							if(sim > max || (sim == max && Math.random() >=  0.5)) {
								max = sim;
								maxX = x + 1;
								maxY = y + 1;
							}
							
							gameState[x][y] = 0;
						}
						
					}
				}
				System.out.println(maxX + ", " + maxY);
				IAinputs.pressACase(maxX, maxY);
				
			}
		}		
	}

}

package ia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Menu.Frame;
import gamelogic.GameLogic;
import objects.*;

public class LearningIAcore extends Thread{

	private boolean Running;
	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private int PLAYER_ID;
	private int IdTurn;
	private GameLogic gamemode;
	private int[][] GameState = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,}};
		  			//GameState[x][y]
	private List History = new LinkedList();
	
	public LearningIAcore(InputsManagerIA IAinputs, GameLogic gamemode, int playerCroixId) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		this.gamemode = gamemode;
 		IdTurn = gamemode.getIdTurn();
		this.PLAYER_ID = playerCroixId;
	}
	
	private void getTurn() {
		IdTurn = gamemode.getIdTurn();
	}
	
	@Override
	public void run() {
		Running = true;
		while(Running) {
			getTurn();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(IdTurn == PLAYER_ID) {
				
				for(Jeton jeton: JetonList) {
					if(jeton instanceof Rond) {
					GameState[jeton.getX()][jeton.getY()] = Frame.PLAYER_ROND_ID;
					}else {
					GameState[jeton.getX()][jeton.getY()] = Frame.PLAYER_CROIX_ID;
					}
					
				}
				char play = nextPlay(GameState);
				IAinputs.pressACase(charToX(play),charToY(play));

			}
		}		
	}
	
	
	private static char nextPlay(int[][] gameState) {
		/*On fabrique l'ID du plateau
		La linkedList contiendra des objets History. Le positionnement des ces objets 
		permettera de creer un "replay" de la partie et ensuite d'ecrire son bilan dans un xml.
		Lire la liste et le bilan xml permet de déduire le coups suivant*/
		//TODO
		return'a';
	}
	
	private int charToX(char c) {
		switch(c) {
		case 'a' : return 1;
		case 'b' : return 2;
		case 'c' : return 3;
		case 'd' : return 1;
		case 'e' : return 2;
		case 'f' : return 3;
		case 'g' : return 1;
		case 'h' : return 2;
		case 'i' : return 3;
		}
		return 0;
	}
	private int charToY(char c) {
		switch(c) {
		case 'a' : return 1;
		case 'b' : return 1;
		case 'c' : return 1;
		case 'd' : return 2;
		case 'e' : return 2;
		case 'f' : return 2;
		case 'g' : return 3;
		case 'h' : return 3;
		case 'i' : return 3;
		}
		return 0;
	}
	
	
}

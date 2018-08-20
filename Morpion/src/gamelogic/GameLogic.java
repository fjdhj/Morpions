package gamelogic;

import java.util.ArrayList;

import objects.*;
import renderer.MasterRenderer;

//Classe en test

public abstract class GameLogic {
	
	public static final int CROIX_ID = -1;
	public static final int ROND_ID = 1;
	public static final int VOID_ID = 0;
	
	protected ArrayList<Jeton> JetonsList = new ArrayList<Jeton>();
	protected static int IDTurn;
	protected static int winnerID = VOID_ID;
	
	public GameLogic() {
		IDTurn = ROND_ID;
	}
	
	
	public abstract void casePressed(int X, int Y,int ID) throws GameLogicException;
		
	
	
	protected void screenUpdt() {
		MasterRenderer.render(JetonsList);
	}
	protected abstract Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException ;
	
	protected int calculateVictory(Jeton lastPlayedJeton) {
		//on met les infos des jetons dans des tableaux
		int[] upLine = 		{0,0,0};
		int[] middleLine =	{0,0,0};
		int[] downLine = 	{0,0,0};
		for(Jeton jeton:JetonsList) {
			int x = jeton.getX();
			int y = jeton.getY();
			
			if(y==1) { //donc la ligne du haut
				upLine[x-1] = conertTypeInInt(jeton);
			}
			if(y==2) { //donc la ligne du milieu
				middleLine[x-1] = conertTypeInInt(jeton);
			}
			if(y==3) { //donc la ligne du bas
				downLine[x-1] = conertTypeInInt(jeton);
			}
		}
		System.out.println("[LOGS]Checking lines");
		//on cherche dans les ligne
		int i = checkHorizontalnAlignement(upLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		i = checkHorizontalnAlignement(middleLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		i = checkHorizontalnAlignement(downLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		
		
		return VOID_ID;
	}

	private int checkHorizontalnAlignement(int[] Line) {
		if(Line[0]+Line[1]+Line[2] == 3) {
			return ROND_ID;
		}
		if(Line[0]+Line[1]+Line[2] == -3) {
			return CROIX_ID;
		}
		return 0;
	}
	
	private int conertTypeInInt(Jeton jeton) {
		if(jeton instanceof Rond) {
			return ROND_ID;
		}else {
			return CROIX_ID;
		}
		
	}	
	}
	


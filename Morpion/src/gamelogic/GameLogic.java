package gamelogic;

import java.util.ArrayList;

import objects.*;
import renderer.MasterRenderer;

//Classe en test

public abstract class GameLogic {
	
	public static final int CROIX_ID = -1;
	public static final int ROND_ID = 1;
	public static final int VOID_ID = 0;
	
	public static final String CROIX_STR = "CROIX";
	public static final String ROND_STR = "ROND";
	
	protected ArrayList<Jeton> JetonsList = new ArrayList<Jeton>();
	protected static int IDTurn;
	protected static int winnerID = VOID_ID;
	
	public GameLogic() {
		IDTurn = ROND_ID;
	}

	protected String playerIdToString(int winner) {
		if(winner == CROIX_ID) {
			return ROND_STR;
		}else {
			return ROND_STR;
		}
	}

	protected void isGameFinished() throws GameLogicException {
		if(winnerID!=VOID_ID) {
			throw new GameLogicException("Partie terminée", 0);
		}		
	}
  public ArrayList<Jeton> getJetonList(){
		return JetonsList;	
	
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
		//on cherche dans les ligne
		System.out.println("[LOGS]Checking lines");
		int i = checkHorizontaAlignement(upLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		i = checkHorizontaAlignement(middleLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		i = checkHorizontaAlignement(downLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		
		
		//on cherche dans les colonnes
		i = checkVerticalAlignement(upLine, middleLine, downLine);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		
		
		//on vérifie les 2 diagonales
		i = alignementCalculator(upLine[0], middleLine[1], downLine[2]);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		i = alignementCalculator(upLine[2], middleLine[1], downLine[0]);
		if(i!=VOID_ID) {
			System.out.println("[LOGS]Victory!");
			return i;
		}
		
		return i;
	}
	
	private int checkVerticalAlignement(int[] upLine,int[] middleLine,int[] downLine) {
		
		for(int i=0;i<upLine.length;i++) {
			int value = alignementCalculator(upLine[i] ,middleLine[i] ,downLine[i]);
		if(value!=VOID_ID) {
			return value;
		}
		}
		return VOID_ID;
	}
	
	private int alignementCalculator(int a, int b, int c) {
		if(a+b+c == 3) {
			return ROND_ID;
		}
		if(a+b+c == -3) {
			return CROIX_ID;
		}
		return 0;
	}

	private int checkHorizontaAlignement(int[] Line) {
		return alignementCalculator(Line[0], Line[1], Line[2]);
	}
	
	private int conertTypeInInt(Jeton jeton) {
		if(jeton instanceof Rond) {
			return ROND_ID;
		}else {
			return CROIX_ID;
		}
		
	}	
	}
	


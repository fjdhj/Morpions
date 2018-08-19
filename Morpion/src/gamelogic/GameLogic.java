package gamelogic;

import java.util.ArrayList;

import objects.*;
import renderer.MasterRenderer;

//Classe en test

public abstract class GameLogic {
	
	public static final int IA_ID = 2;
	public static final int HUMAN_ID = 1;
	
	protected ArrayList<Jeton> JetonsList = new ArrayList<Jeton>();
	protected int IDTurn;
	
	public GameLogic() {
		IDTurn = HUMAN_ID;
	}
	
	
	public abstract void casePressed(int X, int Y,int ID) throws GameLogicException;
		
	
	
	public void screenUpdt() {
		MasterRenderer.render(JetonsList);
	}
	protected abstract Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException ;
	
	
	}
	


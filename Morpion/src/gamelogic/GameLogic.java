package gamelogic;

import java.util.ArrayList;

import objects.*;
import renderer.MasterRenderer;

//Classe en test

public abstract class GameLogic {
	
	protected ArrayList<Jeton> JetonsList = new ArrayList<Jeton>();
	protected boolean Turn;
	
	public GameLogic() {
		Turn = true; //    <-----------il faut rendre cela aléatoire
	}
	
	public abstract void casePressed(int X, int Y) throws GameLogicException;
		
	public void screenUpdt() {
		MasterRenderer.render(JetonsList);
	}
	
	protected Jeton calculateTurn(int X, int Y) {
		if(Turn) {
			Turn = false;
			return new Rond(X,Y);
		}else {
			Turn = true;
			return new Croix(X,Y);
		}
	}
	
}

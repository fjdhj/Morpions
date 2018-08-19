package gamelogic;

import java.util.ArrayList;

import objects.Jeton;
import renderer.MasterRenderer;

//Classe en test

public abstract class GameLogic {
	
	protected ArrayList<Jeton> JetonsList = new ArrayList<Jeton>();
	
	public GameLogic() {
		
	}
	
	public abstract void casePressed(Jeton jeton) throws GameLogicException;
		
	public void screenUpdt() {
		MasterRenderer.render(JetonsList);
	}
	
}

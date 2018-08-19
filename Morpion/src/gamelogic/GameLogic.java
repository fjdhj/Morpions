package gamelogic;

import objects.Jeton;

//Classe en test

public abstract class GameLogic {
	public GameLogic() {
		
	}
	
	public abstract void casePressed(Jeton jeton) throws GameLogicException;
		
	
	
}

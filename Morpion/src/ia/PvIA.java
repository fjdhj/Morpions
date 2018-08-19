package ia;
//Classe en test => regles en PvIA

import gamelogic.GameLogic;
import gamelogic.GameLogicException;
import objects.Jeton;

public class PvIA extends GameLogic{

	@Override
	public void casePressed(int X, int Y) throws GameLogicException {
		
		for(Jeton jetonTest: JetonsList) {
			if(jetonTest.getX() == X &&  jetonTest.getY() == Y) {
				throw new GameLogicException("Case déjà occupée");
			}
		}
		JetonsList.add(calculateTurn(X, Y));
		
		
	}

}

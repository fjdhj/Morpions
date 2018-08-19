package ia;
//Classe en test => regles en PvIA

import gamelogic.GameLogic;
import gamelogic.GameLogicException;
import objects.Jeton;

public class PvIA extends GameLogic{

	@Override
	public void casePressed(Jeton jeton) throws GameLogicException {
		JetonsList.add(jeton);
		
	}

}

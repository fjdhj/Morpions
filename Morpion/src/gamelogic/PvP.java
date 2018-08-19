package gamelogic;
//Classe en test => regles en PvP

import objects.Jeton;

public class PvP extends GameLogic{

	@Override
	public void casePressed(int X, int Y) throws GameLogicException {
		
		for(Jeton jetonTest: JetonsList) {
			if(jetonTest.getX() == X &&  jetonTest.getY() == Y) {
				throw new GameLogicException("Case déjà occupée");
			}
		}
		JetonsList.add(jeton);
		
		
	}

}

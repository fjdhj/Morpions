package gamelogic;
//Classe en test => regles en PvP

import objects.Croix;
import objects.Jeton;
import objects.Rond;

public class PvP extends GameLogic{
	
	@Override
	public void casePressed(int X, int Y,int ID) throws GameLogicException {
		
		for(Jeton jetonTest: JetonsList) {
			if(jetonTest.getX() == X &&  jetonTest.getY() == Y) {
				throw new GameLogicException("Case déjà occupée");
			}
		}
		JetonsList.add(calculateTurn(X, Y, ID));
	}
	protected Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException {
		// ici l'ID de l'ia sert à inverser les formes dessinées.
		if(IDTurn==HUMAN_ID) {
			IDTurn = IA_ID;
			return new Rond(X,Y);
		}else {
			IDTurn = HUMAN_ID;
			return new Croix(X,Y);
		}
}
}
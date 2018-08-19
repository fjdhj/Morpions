package ia;
//Classe en test => regles en PvIA

import gamelogic.GameLogic;
import gamelogic.GameLogicException;
import objects.Croix;
import objects.Jeton;
import objects.Rond;

public class PvIA extends GameLogic{

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
		if(ID!=IDTurn) {throw new GameLogicException("Ce n'est pas votre tour.");}
		
		if(ID==HUMAN_ID) {
			IDTurn = IA_ID;
			return new Rond(X,Y);
		}else {
			IDTurn = HUMAN_ID;
			return new Croix(X,Y);
		}
}
}

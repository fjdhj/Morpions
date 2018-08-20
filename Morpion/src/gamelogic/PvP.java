package gamelogic;
//Classe en test => regles en PvP

import objects.Croix;
import objects.Jeton;
import objects.Rond;

public class PvP extends GameLogic{
	
	@Override
	public void casePressed(int X, int Y,int ID) throws GameLogicException {
		Jeton play = calculateTurn(X, Y, ID);
		
		for(Jeton jetonTest: JetonsList) {
			if(jetonTest.getX() == X &&  jetonTest.getY() == Y) {
				throw new GameLogicException("Case déjà occupée");
			}
		}
		JetonsList.add(play);		
		screenUpdt();

		int winner = calculateVictory(play);
		if(winner!=0) {
			System.out.println(winner + ": a gagné");
			winnerID = winner;
		}
	}
	protected Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException {
		if(winnerID!=VOID_ID) {
			throw new GameLogicException("Partie terminée");
		}
		
		if(IDTurn==ROND_ID) {
			IDTurn = CROIX_ID;
			return new Rond(X,Y);
		}else {
			IDTurn = ROND_ID;
			return new Croix(X,Y);
		}
}
}
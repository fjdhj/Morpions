package ia;
//Classe en test => regles en PvIA

import gamelogic.GameLogic;
import gamelogic.GameLogicException;
import objects.Croix;
import objects.Jeton;
import objects.Rond;
import renderer.MasterRenderer;

public class PvIA extends GameLogic{

	@Override
	public void casePressed(int X, int Y,int ID) throws GameLogicException {
		isGameFinished();
		for(Jeton jetonTest: JetonsList) {
			if(jetonTest.getX() == X &&  jetonTest.getY() == Y) {
				throw new GameLogicException("Case deja  occupee");
			}
		}
		Jeton play = calculateTurn(X, Y, ID);
		JetonsList.add(play);
		screenUpdt();
		
		int winner = calculateVictory(play);
		if(winner!=0) {
			System.out.println(winner + ": a gagne");
			winnerID = winner;
			MasterRenderer.renderText("Partie terminee",0);
			MasterRenderer.renderText("L'equipe "+playerIdToString(winner) + "a gagne!!", 2000);
			
		}
	}

	
	protected Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException {
			if(ID!=IDTurn) {throw new GameLogicException("Ce n'est pas votre tour.");}
		
		
		if(ID==ROND_ID) {
			IDTurn = CROIX_ID;
			return new Rond(X,Y);
		}else {
			IDTurn = ROND_ID;
			return new Croix(X,Y);
		}
}
}

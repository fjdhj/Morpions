package ia;
//Classe en test => regles en PvIA

import java.awt.Color;

import gamelogic.GameLogic;
import gamelogic.GameLogicException;
import objects.Croix;
import objects.Jeton;
import objects.Rond;
import renderer.MasterRenderer;

public class PvIA extends GameLogic{

	protected Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException {
			if(ID!=IDTurn) {throw new GameLogicException(ErrorID.NOT_YOUR_TURN_ID);}
		
		
			if(ID==ROND_ID) {
				IDTurn = CROIX_ID;
				return new Rond(X,Y,Color.red);
			}else {
				IDTurn = ROND_ID;
				return new Croix(X,Y,Color.blue);
			}
}
}

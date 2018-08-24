package gamelogic;
//Classe en test => regles en PvP

import java.awt.Color;

import ia.ErrorID;
import objects.Croix;
import objects.Jeton;
import objects.Rond;
import renderer.MasterRenderer;

public class PvP extends GameLogic{
	
	protected Jeton calculateTurn(int X, int Y, int ID) throws GameLogicException {
		
		
		if(IDTurn==ROND_ID) {
			IDTurn = CROIX_ID;
			return new Rond(X,Y,Color.red);
		}else {
			IDTurn = ROND_ID;
			return new Croix(X,Y,Color.blue);
		}
}
}
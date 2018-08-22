package gamelogic;

import ia.ErrorID;
import renderer.MasterRenderer;

public class GameLogicException extends Exception{

	private int Error;

	public GameLogicException() {
		System.out.println("Un joueur a tente une action interdite.");
		Error = ErrorID.GAME_LOGIC_EXCEPTION_ID;
	}
	
	public GameLogicException(int ErrorID) {
		MasterRenderer.renderText(IdToString(ErrorID), 1000);
		Error = ErrorID;
	}

	private String IdToString(int errorID) {
		switch (errorID) {
		case ErrorID.BUSY_CASE_ID : return "Case deja  occupee";
		case ErrorID.NOT_YOUR_TURN_ID : return "Ce n'est pas votre tour.";
		case ErrorID.GAME_OVER_ID : return "Partie terminee";
		}
		return "Action interdite";
	}

	public GameLogicException(int ErrorID, int ms) {
		MasterRenderer.renderText(IdToString(ErrorID), ms);
		Error = ErrorID;
	}

	public int getErrorID() {
		return Error;
	}
}

package gamelogic;

import ia.ErrorID;
import renderer.MasterRenderer;

public class GameLogicException extends Exception{

	private String Message;
	private int Error;
	private int MessageDuration;

	public GameLogicException() {
		Error = ErrorID.GAME_LOGIC_EXCEPTION_ID;
	}
	
	public GameLogicException(int ErrorID) {
		MessageDuration = 1000;
		Error = ErrorID;
		Message = IdToString(ErrorID);
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
		MessageDuration = ms;
		Error = ErrorID;
		Message = IdToString(ErrorID);
	}

	public String getMessage() {
		return Message;
	}
	
	public int getMessageDuration() {
		return MessageDuration;
	}
	
	public int getErrorID() {
		return Error;
	}
}

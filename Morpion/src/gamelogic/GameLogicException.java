package gamelogic;

public class GameLogicException extends Exception{
	//en cas de case d�ja prise par exemple
	public GameLogicException() {
		System.out.println("Un joueur a tent� une action interdite.");
	}
}

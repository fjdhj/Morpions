package gamelogic;

public class GameLogicException extends Exception{
	//en cas de case déja prise par exemple
	public GameLogicException() {
		System.out.println("Un joueur a tenté une action interdite.");
	}
	
	public GameLogicException(String str) {
		System.out.println(str);
	}
}

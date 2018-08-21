package gamelogic;

import renderer.MasterRenderer;

public class GameLogicException extends Exception{
	//en cas de case d�ja prise par exemple
	public GameLogicException() {
		System.out.println("Un joueur a tent� une action interdite.");
	}
	
	public GameLogicException(String str) {
		MasterRenderer.renderText(str, 1000);
	}

	public GameLogicException(String str, int ms) {
		MasterRenderer.renderText(str, ms);
	}
}

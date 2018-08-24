package ia;

import java.util.ArrayList;

import Menu.Frame;
import gamelogic.GameLogic;
import objects.Jeton;

public class LearningIAcore extends Thread{

	private boolean Running;
	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private int PLAYER_ID;
	private int IdTurn;
	private GameLogic gamemode;
	
	public LearningIAcore(InputsManagerIA IAinputs, GameLogic gamemode, int playerCroixId) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		this.gamemode = gamemode;
		IdTurn = gamemode.getIdTurn();
		this.PLAYER_ID = playerCroixId;
	}
	
	private void getTurn() {
		IdTurn = gamemode.getIdTurn();
	}
	
	@Override
	public void run() {
		Running = true;
		while(Running) {
			getTurn();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(IdTurn == PLAYER_ID) {
				IAinputs.pressACase(1, 1);
				IAinputs.pressACase(2, 1);
				IAinputs.pressACase(3, 1);
			}
		}		
	}
}

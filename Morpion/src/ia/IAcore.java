package ia;

import java.util.ArrayList;

import gamelogic.GameLogic;
import objects.Jeton;

public class IAcore extends Thread{
	
	private boolean Running;
	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private int PLAYER_ID = InputsManagerIA.PLAYER_ID;
	private int IdTurn;
	
	public IAcore(InputsManagerIA IAinputs, GameLogic gamemode) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		IdTurn = gamemode.getIdTurn();
	}



	@Override
	public void run() {
		Running = true;
		while(Running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(IdTurn != PLAYER_ID) {
				IAinputs.pressACase(1, 1);
				IAinputs.pressACase(2, 1);
				IAinputs.pressACase(3, 1);
			}
		}		
	}

}

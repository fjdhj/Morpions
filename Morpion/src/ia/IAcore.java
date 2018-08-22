package ia;

import java.util.ArrayList;

import gamelogic.GameLogic;
import objects.Jeton;

public class IAcore extends Thread{
	
	private boolean Running;
	private InputsManagerIA IAinputs;
	private GameLogic gamemode;
	private ArrayList<Jeton> JetonList;
	
	public IAcore(InputsManagerIA IAinputs, GameLogic gamemode) {
		this.IAinputs = IAinputs;
		this.gamemode = gamemode;
	}



	@Override
	public void run() {
		Running = true;
		while(Running) {
			
		}		
	}

}

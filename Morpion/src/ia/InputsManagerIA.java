package ia;

import gamelogic.GameLogic;
import gamelogic.GameLogicException;

public class InputsManagerIA {
	public int PLAYER_ID ;
	private GameLogic Gamemode;
	
	public InputsManagerIA(GameLogic Gamemode,int PLAYER_ID) {
		this.Gamemode = Gamemode;
 		this.PLAYER_ID = PLAYER_ID;
	}

		public int pressACase(int CaseX, int CaseY) {
				try {
					Gamemode.casePressed(CaseX,CaseY, PLAYER_ID);
				} catch (GameLogicException e) {
					return e.getErrorID();
				} catch (OutOfBoundException e) {
					return ErrorID.OUT_OF_BOUND_ID;
				}
				return ErrorID.NO_ERROR_ID;
			}
		}



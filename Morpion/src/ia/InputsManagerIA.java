package ia;

import gamelogic.GameLogic;
import gamelogic.GameLogicException;

public class InputsManagerIA {
	private static final int PLAYER_ID = -1;
	private GameLogic Gamemode;

		public int pressACase(int CaseX, int CaseY) {
				try {
					Gamemode.casePressed(CaseX,CaseY, PLAYER_ID);
				} catch (GameLogicException e) {
					return ErrorID.GAME_LOGIC_EXCEPTION_ID;
				} catch (OutOfBoundException e) {
					return ErrorID.OUT_OF_BOUND_ID;
				}
				return ErrorID.NO_ERROR_ID;
			}
		}



package ia;

public class History {
	private int ID;
	private char playedChar;
	
	public History(int iD, char playedChar) {
		ID = iD;
		this.playedChar = playedChar;
	}
	public int getID() {
		return ID;
	}
	public char getPlayedChar() {
		return playedChar;
	}
	
	
}

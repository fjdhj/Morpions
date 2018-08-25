package ia;

public class History {
	private String ID;
	private char playedChar;
	
	public History(String iD, char playedChar) {
		ID = iD;
		this.playedChar = playedChar;
	}
	public String getID() {
		return ID;
	}
	public char getPlayedChar() {
		return playedChar;
	}
	
	
}

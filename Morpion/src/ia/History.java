package ia;

public class History {
	private String ID;
	private char playedChar;
	private int[] playWeight = {0,0,0,0,0,0,0,0,0};
	
	public History(String iD, char playedChar, char[] population) {
		ID = iD;
		this.playedChar = playedChar;
		
		//conversion en poids de chaque possibilité
		for(int i = 0; i<playWeight.length; i++) {
			char lookafter = LearningIAcore.CHAR[i];
			int weightcount = 0;
		for(char c :population) {
			if(c == lookafter) {
				weightcount++;
			}
		}
		playWeight[i] = weightcount;
	}
	}
	public String getID() {
		return ID;
	}
	public char getPlayedChar() {
		return playedChar;
	}
	public int[] getPlayWeight() {
		return playWeight;
	}
	
}

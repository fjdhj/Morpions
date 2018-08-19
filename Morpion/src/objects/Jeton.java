package objects;

import java.awt.Color;

/*	Les valeurs x et y correspodent au numéro de la CASE dans la grille	*/
public class Jeton {
	private int x;
	private int y;
	private Color couleur;
	
	public Jeton(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

	
	
}

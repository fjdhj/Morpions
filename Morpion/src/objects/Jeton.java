package objects;

import java.awt.Color;

/*	Les valeurs x et y correspodent au numéro de la CASE dans la grille	
 * Elles vont de 1 a 3
 * */
public class Jeton {
	private int x;
	private int y;
	private Color couleur = Color.black;
	
	public Jeton(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Jeton(int x, int y,Color couleur) {
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getCouleur() {
		return couleur;
	}
	

	
	
}

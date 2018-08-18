package renderer;

import javax.swing.JFrame;

/*-----------------UTILISATION DE LA CLASSE-----------------------

Objet � construire une unique fois.
Le constructeur peut prendre ou pas de parametres. 
Si l'objet a �t� construit sans parametres il est obligatoire d'invoquer 
la m�thode init().

-----------------------------FIN--------------------------------*/


public class MasterRenderer {

	private JFrame Display;
	
	
	public MasterRenderer() {
		
	}
	
	public MasterRenderer(String name, int height, int width) {
		Display = new JFrame(name);
		Display.setSize(height, width);
	}
	
	
	public void init(String name, int height, int width) {
		Display = new JFrame(name);
		Display.setSize(height, width);
	}
	
	//M�thode de rendu
	public void render() {
		//TODO
	}
}

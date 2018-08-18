package renderer;

import java.util.ArrayList;
import javax.swing.JFrame;
import objects.*;

/*-----------------UTILISATION DE LA CLASSE-----------------------

Objet � construire une unique fois.

Le constructeur peut prendre ou pas de parametres. 
Si l'objet a �t� construit sans parametres il est obligatoire d'invoquer 
la m�thode init().

La methode render() prends en argument une liste d'objets Jeton. 
ATTENTION, les jetons doivent etre des ronds ou des croix!!!!
d�fini comme suit:   Jeton jeton = new Croix(0,0);

-----------------------------FIN--------------------------------*/


public class MasterRenderer {

	private JFrame Display;
	private boolean Init = false;
	
	public MasterRenderer() {
		
	}
	
	public MasterRenderer(String name, int height, int width) {
		init(name, height, width);
	}
	
	public void setVisible(boolean visible) throws DisplayException {
		if(!Init) {
			Display.setVisible(visible);
		}else {throw new DisplayException("L'affiche n'a pas �t� initalis� correctement.");}
	}
	
	//M�thode de d'initialisation
	public void init(String name, int height, int width) {
		Init =true;
		Display = new JFrame(name);
		Display.setSize(height, width);
		Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display.setLocationRelativeTo(null);

	}
	
	//M�thode de rendu
	public void render(ArrayList<Jeton> ObjectsToRenderList){

	}
}

package renderer;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.*;

/*-----------------UTILISATION DE LA CLASSE-----------------------

Objet à construire une unique fois.

Le constructeur peut prendre une JFrame ou des parametres. 
Si l'objet a été construit sans parametres il est obligatoire d'invoquer 
la méthode init().

La methode render() prends en argument une liste d'objets Jeton. 
ATTENTION, les jetons doivent etre des ronds ou des croix!!!!
défini comme suit:   Jeton jeton = new Croix(1,1);

-----------------------------FIN--------------------------------*/


public class MasterRenderer {

	private JFrame Display;
	private Panel ContentPane = new Panel();
	private boolean Init = false;
	
	public MasterRenderer(JFrame Display) {
		this.Display = Display;
		activateRenderPane();
	}
	
	public MasterRenderer(String name, int height, int width) {
		init(name, height, width);
	}
	
	public void setVisible(boolean visible) throws DisplayException {
		if(Init) {
			Display.setVisible(visible);
		}else {throw new DisplayException("L'affiche n'a pas été initalisé correctement.");}
	}
	
	public void activateRenderPane() {
		this.Display.setContentPane(ContentPane);
	}	
	
	public void disableRenderPane(JPanel ContentPane) {
		this.Display.setContentPane(ContentPane);
	}
	
	//Méthode de d'initialisation
	public void init(String name, int height, int width) {
		Init =true;
		Display = new JFrame(name);
		activateRenderPane();
		Display.setSize(height, width);
		Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display.setLocationRelativeTo(null);

	}
	
	//Méthode de rendu
	public void render(ArrayList<Jeton> ObjectsToRenderList){
		ContentPane.prepare(ObjectsToRenderList);
		ContentPane.repaint();
	}
}

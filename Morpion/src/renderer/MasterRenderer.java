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
	}
	
	public void setVisible(boolean visible){
			Display.setVisible(visible);
	}
	
	public void activateRenderPane() {
		this.Display.setContentPane(ContentPane);
		this.Display.validate();
	}	
	
	//Méthode de rendu
	public void render(ArrayList<Jeton> ObjectsToRenderList){
		ContentPane.prepare(ObjectsToRenderList);
		ContentPane.repaint();
	}
	public void render() {
		ContentPane.repaint();
	}
	public void updateRenderList(ArrayList<Jeton> ObjectsToRenderList) {
		ContentPane.prepare(ObjectsToRenderList);
	}
	
	
	public JPanel getGraphicPane() {
		return ContentPane;
		
	}
}

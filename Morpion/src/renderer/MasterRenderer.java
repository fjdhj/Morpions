package renderer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.*;

/*-----------------UTILISATION DE LA CLASSE-----------------------

Objet � construire une unique fois.

Le constructeur peut prendre une JFrame ou des parametres. 
Si l'objet a �t� construit sans parametres il est obligatoire d'invoquer 
la m�thode init().

La methode render() prends en argument une liste d'objets Jeton. 
ATTENTION, les jetons doivent etre des ronds ou des croix!!!!
d�finis comme suit:   Jeton jeton = new Croix(1,1);

-----------------------------FIN--------------------------------*/


public class MasterRenderer {

	private JFrame Display;
	private static Panel ContentPane = new Panel();
	private static String infiniteStringToRender;

	
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
	
	public static void clearAllTextOnScreen() {
		infiniteStringToRender = "";
        ContentPane.clearTextOnScreen();
	}
	
	public static void renderText(String text, long timeOnScreen) {
		

		if(timeOnScreen==0) {
			infiniteStringToRender = text;
			ContentPane.printTextOnScreen(infiniteStringToRender);
		}else {
		Timer timer = new Timer();
        timer.schedule (new TimerTask() {
            public void run()
            {
            ContentPane.clearTextOnScreen();
			ContentPane.printTextOnScreen(infiniteStringToRender);
            
            }
        },timeOnScreen);
        
		ContentPane.printTextOnScreen(text);
		}
	}		
	
	//M�thode de rendu
	public static void render(ArrayList<Jeton> ObjectsToRenderList){
		ContentPane.prepare(ObjectsToRenderList);
		ContentPane.repaint();
	}
	public void render() {
		ContentPane.repaint();
	}
	public void updateRenderList(ArrayList<Jeton> ObjectsToRenderList) {
		ContentPane.prepare(ObjectsToRenderList);
	}
	
	public static void updateNetworkStatus(String status) {
		ContentPane.debugMessage(status);
		ContentPane.repaint();

	}
	
	public JPanel getGraphicPane() {
		return ContentPane;
		
	}
}

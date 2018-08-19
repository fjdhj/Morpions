package gamelogic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class InputsManager {
	
	
	public InputsManager(JPanel Panel) {
		Panel.addMouseListener(new MouseAdapter(){
		      public void mousePressed(MouseEvent e){
		    	  //TODO
		    	  
		      }
		      });
	}
	
	
}

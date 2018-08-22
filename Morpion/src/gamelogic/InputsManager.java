package gamelogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ia.OutOfBoundException;
import objects.Croix;
import renderer.MasterRenderer;

public class InputsManager {
	private static final int PLAYER_ID = 1;
	
	public InputsManager(JPanel Panel, GameLogic Gamemode) {
		Panel.addMouseListener(new MouseAdapter(){
		      public void mousePressed(MouseEvent e){

		    	  int CaseX = 0;
		    	  int CaseY = 0;
		    	  
		    	  int MouseX = e.getX();
		    	  int MouseY = e.getY();

		    	  int PaneHeight = Panel.getHeight();
		    	  int PaneWidth = Panel.getWidth();
		    	  
		    	  //trouve la colonne
		    	  if(0<MouseX && MouseX<1*PaneWidth/3) {
		    		  CaseX = 1;
		    	  }
		    	  if(1*PaneWidth/3<MouseX && MouseX<2*PaneWidth/3) {
		    		  CaseX = 2;
		    	  }
		    	  if(2*PaneWidth/3<MouseX && MouseX<3*PaneWidth/3) {
		    		  CaseX = 3;
		    	  }
		    	  
		    	  //trouve la ligne
		    	  if(0<MouseY && MouseY<1*PaneHeight/3) {
		    		  CaseY = 1;
		    	  }
		    	  if(1*PaneHeight/3<MouseY && MouseY<2*PaneHeight/3) {
		    		  CaseY = 2;
		    	  }
		    	  if(2*PaneHeight/3<MouseY && MouseY<3*PaneHeight/3) {
		    		  CaseY = 3;
		    	  }
		    	  
		    	  try {
					Gamemode.casePressed(CaseX,CaseY, PLAYER_ID);
				} catch (GameLogicException e1) {
					MasterRenderer.renderText(e1.getMessage(), e1.getMessageDuration());
				} catch (OutOfBoundException e1) {/*Impossible*/}
		    	  
		      }
		      });
		Panel.requestFocus();
	}
	
	
}

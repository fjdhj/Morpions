package renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import objects.Croix;
import objects.Jeton;
import objects.Rond;

public class Panel extends JPanel {
	
	private int height;
	private int width;
	private ArrayList<Jeton> RenderList = new ArrayList<Jeton>();
	private String stringToRender;

	public void paintComponent(Graphics g){
		height = this.getHeight();
		width = this.getWidth();
		
		int caseHeight = height/3;
		int caseWidth = width/3;
		
		int[] START_BACK_LINES ={1*height/3 , 2*height/3 , 3*height/3,
				1*width/3 ,		
				2*width/3 ,	
				3*width/3};	
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);

		
		for(int i=0; i<START_BACK_LINES.length; i++){
			if(i>2) {
			g.drawLine(START_BACK_LINES[i], 0, START_BACK_LINES[i], height);
			}else {
			g.drawLine(0, START_BACK_LINES[i], width, START_BACK_LINES[i]);
			}
		}
		

		for(Jeton jeton: RenderList) {
			if(jeton instanceof Rond) {
				g.setColor(jeton.getCouleur());
				drawCenteredCircle(g,caseWidth* jeton.getX() - caseWidth/2 , caseHeight* jeton.getY() - caseHeight/2,40);
				g.setColor(Color.black);
			}
			if(jeton instanceof Croix) {
				g.setColor(jeton.getCouleur());
				drawCenteredCross(g,caseWidth* jeton.getX() - caseWidth/2 , caseHeight* jeton.getY() - caseHeight/2,caseHeight,caseWidth);
				g.setColor(Color.black);
			}
		}
		
		// on fait le rendu d'un texte si il y en a
		if(stringToRender != null) {
			g.setFont(new Font("Impact", Font.ITALIC, 20));
			g.drawString(stringToRender, width/2-stringToRender.length()*4, height/2);
		}
	}
	

	private void drawCenteredCross(Graphics g, int x, int y, int caseHeight, int caseWidth) {
		g.drawLine(x-20, y-20, x+20, y+20);
		g.drawLine(x+20, y-20, x-20, y+20);
	}
	
	private void drawCenteredCircle(Graphics g, int x, int y, int r) {
		x = x-(r/2);
		y = y-(r/2);
		g.drawOval(x,y,r,r);
		
	}
	
	public void printTextOnScreen(String text) {
        stringToRender = text;
		this.repaint();
		}
        
	
	
	public void clearTextOnScreen() {
		stringToRender = "";
		this.repaint();
	}
	
	public void prepare(ArrayList<Jeton> objectsToRenderList) {
		RenderList = objectsToRenderList;
	}
}

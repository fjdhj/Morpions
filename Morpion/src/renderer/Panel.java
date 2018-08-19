package renderer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private int height;
	private int width;

	@Override
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
		drawCenteredCross(g,caseWidth/2 , caseHeight/2,caseHeight,caseWidth);
		drawCenteredCircle(g, 3*caseWidth/2,3*caseHeight/2, 40);
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
}

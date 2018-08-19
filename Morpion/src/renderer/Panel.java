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
		
	}
	

	private void drawCenteredCross(Graphics g, int x, int y) {
		
	}
	
	private void drawCenteredCircle(Graphics g, int x, int y) {
		
	}
}

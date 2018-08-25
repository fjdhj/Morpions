package ia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import Menu.Frame;
import gamelogic.GameLogic;
import objects.*;

public class LearningIAcore extends Thread{

	private boolean Running;
	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private int PLAYER_ID;
	private int IdTurn;
	private GameLogic gamemode;
	private int[][] GameState = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
		  			//GameState[x][y]
	private List<History> History = new LinkedList<History>();
	
	public LearningIAcore(InputsManagerIA IAinputs, GameLogic gamemode, int playerCroixId) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		this.gamemode = gamemode;
 		IdTurn = gamemode.getIdTurn();
		this.PLAYER_ID = playerCroixId;
	}
	
	private void getTurn() {
		IdTurn = gamemode.getIdTurn();
	}
	
	private void getJetonList() {
		JetonList = gamemode.getJetonList();
	}
	@Override
	public void run() {
		Running = true;
		while(Running) {
			getTurn();
			getJetonList();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(IdTurn == PLAYER_ID) {
				
				for(Jeton jeton: JetonList) {
					if(jeton instanceof Rond) {
					GameState[jeton.getX()-1][jeton.getY()-1] = 2; //adversaire
					}else {
					GameState[jeton.getX()-1][jeton.getY()-1] = 1; //joueur
					}
					
				}
				char play = nextPlay(GameState);

				IAinputs.pressACase(charToX(play),charToY(play));

			}
		}		
	}
	
	
	private char nextPlay(int[][] gameState) {
		/*On fabrique l'ID du plateau
		La linkedList contiendra des objets History. Le positionnement des ces objets 
		permettera de creer un "replay" de la partie et ensuite d'ecrire son bilan dans un xml.
		Lire la liste et le bilan xml permet de déduire le coups suivant*/

		int id = 0;
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				id = id + x+1*y+1*id+ gameState[x][y];
				System.out.println(x+1*y+1*id+ gameState[x][y] + "         " + id);

			}
		}
		
		int status = readXML(History,id);
		
		System.out.println("Id du plateau: @"+id);
		
		if(status != ErrorID.IA_OK) {
		
		double random = Math.random()*10;
		
		if(random<1*10/9) {
			History.add(new History(id,'a'));
			return'a';
		}
		if(1*10/9<random && random<2*10/9) {
			History.add(new History(id,'b'));
			return'b';
		}
		if(2*10/9<random && random<3*10/9) {
			History.add(new History(id,'c'));
			return'c';
		}
		if(3*10/9<random && random<4*10/9) {
			History.add(new History(id,'d'));
			return'd';
		}
		if(4*10/9<random && random<5*10/9) {
			History.add(new History(id,'e'));
			return'e';
		}
		if(5*10/9<random && random<6*10/9) {
			History.add(new History(id,'f'));
			return'f';
		}
		if(6*10/9<random && random<7*10/9) {
			History.add(new History(id,'g'));
			return'g';
		}
		if(7*10/9<random && random<8*10/9) {
			History.add(new History(id,'h'));
			return'h';
		}
		if(8*10/9<random && random<9*10/9) {
			History.add(new History(id,'i'));
			return'i';
		}
	}
		
		return'a';
	}
	
	private int readXML(List<ia.History> history, int id) {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      
	      
	      String expression = "";
	      
	      for(History h : history) {
	    	  expression = expression +"/@"+ h.getID();
	      }

	      System.out.println(expression);
	      
	      if(expression == "") {
	    	  return ErrorID.IA_EMPTY_PATH;
	      }
	      
	      try {
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         File fileXML = new File("IANeuronalTree.xml");
	         Document xml = builder.parse(fileXML);
	         Element root = xml.getDocumentElement();
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	                   
	        
	        String str = (String)path.evaluate(expression, root);
	        System.out.println(str);
	        System.out.println("-------------------------------------");
	       
	        
	      } catch (ParserConfigurationException e) {
	      } catch (SAXException e) {
	      } catch (IOException e) {
	      } catch (XPathExpressionException e) {
	    	  return ErrorID.IA_EMPTY_PATH;
	      }
	      
		
		return 0;
	}

	private int charToX(char c) {
		switch(c) {
		case 'a' : return 1;
		case 'b' : return 2;
		case 'c' : return 3;
		case 'd' : return 1;
		case 'e' : return 2;
		case 'f' : return 3;
		case 'g' : return 1;
		case 'h' : return 2;
		case 'i' : return 3;
		}
		return 0;
	}
	private int charToY(char c) {
		switch(c) {
		case 'a' : return 1;
		case 'b' : return 1;
		case 'c' : return 1;
		case 'd' : return 2;
		case 'e' : return 2;
		case 'f' : return 2;
		case 'g' : return 3;
		case 'h' : return 3;
		case 'i' : return 3;
		}
		return 0;
	}
	
	
}

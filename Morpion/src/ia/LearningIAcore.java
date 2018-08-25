package ia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
		int LastStatus = -1;
		while(Running) {
			getTurn();
			getJetonList();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(IdTurn == PLAYER_ID) {
				if(LastStatus == ErrorID.GAME_OVER_ID) {
					Running = false;
					System.out.println("[LEARNING_IA] Fin de la partie.");
					break;
				}
				
				for(Jeton jeton: JetonList) {
					if(jeton instanceof Rond) {
					GameState[jeton.getX()-1][jeton.getY()-1] = 2; //adversaire
					}else {
					GameState[jeton.getX()-1][jeton.getY()-1] = 1; //joueur
					}
					
				}
				char play = nextPlay(GameState);

				LastStatus = IAinputs.pressACase(charToX(play),charToY(play));
				

			}
		}		
	}
	
	
	private char nextPlay(int[][] gameState) {
		/*On fabrique l'ID du plateau
		*/

		String id = "";
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				switch(gameState[x][y]) {
				case 2 : id = id + 'r';
				case 1 : id = id + 'c';
				case 0 : id = id + 'v';
				}
				
			}
		}
		
		System.out.println("[LEARNING_IA] Id du plateau: /"+id);
		String status = readXML(History,id);
		//status = tirage au sort avec les poids

		
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
	
	private String readXML(List<ia.History> history, String id) {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);
	      
	      String returnString = "";
	      
	      System.out.println("[LEARNING_IA] Lecture du xml");
	      try {
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         File fileXML = new File("IANeuronalTree.xml");
	         Document xml = builder.parse(fileXML);
	         Element root = xml.getDocumentElement();

	         NodeList states = root.getChildNodes();
	         
	         for(int i = 0; i<states.getLength(); i++) {
        	 Node temp = states.item(i);
        	 if(temp instanceof Element && temp.getNodeName().equals(id)) {
        	 System.out.println("[LEARNING_IA] Situatuion connue");
        	 System.out.println("-------------------------------------");
        	 
        	 	NodeList coups = temp.getChildNodes();
        	 	for(int a = 0; a<coups.getLength(); a++) {
        	 		Node tempscoup = coups.item(a);
        	 		if(tempscoup instanceof Element) {
        			 
        	 			for(int n = 0; Integer.parseInt(tempscoup.getAttributes().item(0).getNodeValue())>n;n++) {
        				 returnString = returnString + tempscoup.getNodeName()  ;
        	 			}
        	 		}
        	 	}
        	 	System.out.println("[LEARNING_IA] "+returnString);
	        return returnString;
        	 }
	         }
			
	      } catch (ParserConfigurationException e) {
	    	  e.printStackTrace();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      } catch (SAXException e) {
	    	  e.printStackTrace();
		}
	      
  	 	System.out.println("Pas de coups connu trouvé");
		return ErrorID.IA_UNKNOWN_STATE;
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

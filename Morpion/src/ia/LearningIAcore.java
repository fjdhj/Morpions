package ia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

	private InputsManagerIA IAinputs;
	private ArrayList<Jeton> JetonList;
	private boolean Running;
	private static final String EQUIPROBABLE = "abcdefghi";
	public static final char[] CHAR = {'a','b','c','d','e','f','g','h','i'};
	private int PLAYER_ID;
	private int IdTurn;
	private int[][] GameState = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
		  			//GameState[x][y]
	private GameLogic gamemode;
	private ArrayList<History> History =  new ArrayList<History>() ;
	
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
			if(IAinputs.getVictoryState() != GameLogic.VOID_ID) {
				Running = false;
				System.out.println("[LEARNING_IA] Fin de la partie: Traitement du fichier bilan...");
				break;
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
				int status = IAinputs.pressACase(charToX(play),charToY(play));
				if(status == ErrorID.BUSY_CASE_ID) {
					History.remove(History.size()-1);
				}
				

			}
		}
		
		IaXmlWriter bilan = new IaXmlWriter();
		bilan.writeNeuronalXml(History, IAinputs.getVictoryState());
	}
	
	
	private char nextPlay(int[][] gameState) {
		/*On fabrique l'ID du plateau
		*/

		String id = "";
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				switch(gameState[x][y]) {
				case 2 : id = id + 'r';
				break;
				case 1 : id = id + 'c';
				break;
				case 0 : id = id + 'v';
				break;
				}
				
			}
		}
		
		System.out.println("[LEARNING_IA] Id du plateau: /"+id);
		String status = readXML(History,id);
		
		if(status == ErrorID.IA_UNKNOWN_STATE) {
			status = EQUIPROBABLE;
		}
		//traiter les appuis sur une meme case
		char[] population = status.toCharArray();
		char play = randomCase(population);
		History.add(new History(id,play,population));
		return play;
	}
	
	private char randomCase(char[] population) {
		int random = (int) (Math.random()*(population.length-1));
		System.out.println("[LEARNING_IA] variable al�atoire: "+random);
		System.out.println("[LEARNING_IA] case choisie: "+population[random]);
		return population[random];
	}

	
	
	private String readXML(ArrayList<ia.History> history2, String id) {
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
	      
  	 	System.out.println("[LEARNING_IA] Pas de coups connu trouv�");
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

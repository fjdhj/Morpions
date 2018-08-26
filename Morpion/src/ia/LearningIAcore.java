package ia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	public static final char[] CHAR = {'a','b','c','d','e','f','g','h','i'};
	private int PLAYER_ID;
	private int IdTurn;
	private int[][] GameState = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
		  			//GameState[x][y]
	private GameLogic gamemode;
	private ArrayList<History> History =  new ArrayList<History>() ;
	private int IA_ID=0;
	
	public LearningIAcore(InputsManagerIA IAinputs, GameLogic gamemode, int playerCroixId, int ia_id) {
		this.IAinputs = IAinputs;
		JetonList = gamemode.getJetonList();
		this.gamemode = gamemode;
 		IdTurn = gamemode.getIdTurn();
		this.PLAYER_ID = playerCroixId;
		this.IA_ID = ia_id;
	}
	
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
				Thread.sleep(50);
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
		bilan.writeNeuronalXml(History, IAinputs.getVictoryState(), IA_ID);
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
		String status = readXML(id);
		//Il serait possible de générer un id symétrique, de le faire chercher dans le ficher
		//et ensuite de générer une population qui mixe les 2.
		// Cette solution serait surtout efficace si aucun coups n'est trouvé: il suffit de chercher un symétrique
		//et de le convertir.
		String newId = id;
		int symetry = 0;
		if(status == ErrorID.IA_UNKNOWN_STATE) {
			 newId = symetriseHorizontalID(id);
			status = readXML(newId);
			symetry = 1;
		}
		
		if(status == ErrorID.IA_UNKNOWN_STATE) {
			 newId = symetriseVerticalID(id);
			status = readXML(newId);
			symetry = 2;
		}
		
		
		//Exclure les cases pleines si cas inconnu
		if(status == ErrorID.IA_UNKNOWN_STATE) {
			System.out.println("[LEARNING_IA] Pas de symetrie, tentative aléatoire");

			char[] nodeChar = id.toCharArray();
			int nodeCharParser = 0;
			status = "";
			for(char c:nodeChar) {
				   if(c == 'v') {
					  status = status + String.valueOf(CHAR[nodeCharParser]);
				   }
				   nodeCharParser++;
			}
			char[] population = status.toCharArray();
			char play = randomCase(population);
			History.add(new History(id,play,population));
			return play;
		}
		
	//coups connu par symetrie ou non. donc 3 cas possibles: pas de symetrie/symetrieH/symetrieV	
		char[] population = status.toCharArray();
		char play = randomCase(population);
		History.add(new History(id,play,population));
		//si la symetrie a trouvé: il faut aussi inverser le résultat
				switch(symetry) {
				case 1: play = symetriseHorizontalChar(play);
				case 2: play = symetriseVerticalChar(play);
				}
		return play;
	}
	
	
	private char symetriseHorizontalChar(char c) {
		
		switch(c) {
		case 'a' : return 'c';
		case 'c' : return 'a';
		case 'd' : return 'f';
		case 'f' : return 'd';
		case 'g' : return 'i';
		case 'i' : return 'g';
		}
		
		return c;
	}
	
	private char symetriseVerticalChar(char c) {
		
		switch(c) {
		case 'a' : return 'g';
		case 'b' : return 'h';
		case 'c' : return 'i';
		case 'g' : return 'a';
		case 'h' : return 'b';
		case 'i' : return 'c';
		}
		
		return c;
	}

	private String symetriseVerticalID(String input) {
		String id = "";
		char[] idChar = input.toCharArray();
		id= String.valueOf(idChar[6]) + String.valueOf(idChar[7]) + String.valueOf(idChar[8]);
		id= id +String.valueOf(idChar[3]) + String.valueOf(idChar[4]) + String.valueOf(idChar[5]);
		id= id +String.valueOf(idChar[0]) + String.valueOf(idChar[1]) + String.valueOf(idChar[2]);
		
		System.out.println("[LEARNING_IA] Id d'un plateau symetrique: /"+id);
		return id;
	}

	private String symetriseHorizontalID(String input) {
		String id = "";
		char[] idChar = input.toCharArray();
		id= String.valueOf(idChar[2]) + String.valueOf(idChar[1]) + String.valueOf(idChar[0]);
		id= id +String.valueOf(idChar[5]) + String.valueOf(idChar[4]) + String.valueOf(idChar[3]);
		id= id +String.valueOf(idChar[8]) + String.valueOf(idChar[7]) + String.valueOf(idChar[6]);
		
		System.out.println("[LEARNING_IA] Id d'un plateau symetrique: /"+id);
		return id;
	}
	
	private char randomCase(char[] population) {
		int random = (int) (Math.random()*(population.length-1));
		System.out.println("[LEARNING_IA] variable aléatoire: "+random);
		System.out.println("[LEARNING_IA] case choisie: "+population[random]);
		return population[random];
	}

	
	
	private String readXML(String id) {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);
	      
	      String returnString = "";
	      System.out.println("[LEARNING_IA] Lecture du xml");
	      try {
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         File fileXML = new File("IANeuronalTree"+IA_ID+".xml");
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
	      
  	 	System.out.println("[LEARNING_IA] Pas de coups connu trouvé");
  	 	System.out.println("-------------------------------------");
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

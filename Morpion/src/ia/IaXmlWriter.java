package ia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import gamelogic.GameLogic;

public class IaXmlWriter {

	private static char[] CasesChar = {'a','b','c','d','e','f','g','h','i'};
   public IaXmlWriter() {
	   
   }
   
   public void writeNeuronalXml(ArrayList<History> history, int victory, int iA_ID) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try {
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document xml = builder.newDocument();
         
         Element root = xml.createElement("IAtree");
         
         readXML(history, xml, root,iA_ID);

         int profondeur = 0;
         for(History time: history) {
         fillElement(root, time.getPlayedChar(),time.getID(), victory, time.getPlayWeight(), profondeur, xml);
         profondeur +=2;
		 }
         
         Transformer t = TransformerFactory.newInstance().newTransformer();
         t.setOutputProperty(OutputKeys.INDENT, "yes");
         String resultFile = "IANeuronalTree"+iA_ID+".xml"; 
         StreamResult XML = new StreamResult(resultFile);
         
         t.transform(new DOMSource(root), XML);       
         
      } catch (DOMException e) {
         e.printStackTrace();
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (TransformerConfigurationException e) {
         e.printStackTrace();
      } catch (TransformerFactoryConfigurationError e) {
         e.printStackTrace();
      } catch (TransformerException e) {
         e.printStackTrace();
      }finally {
    	  System.out.println("[LEARNING_IA] Fin de traitement\n ---------arret---------");
      }
   }
   
   private void readXML(ArrayList<History> history, Document xml, Element root, int iA_ID) {
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);
	      
	      
	      System.out.println("[LEARNING_IA] Lecture du xml");
	      try {
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         File fileXML = new File("IANeuronalTree"+iA_ID+".xml");
	         Document xml1 = builder.parse(fileXML);
	         Element root1 = xml1.getDocumentElement();

	         NodeList states = root1.getChildNodes();
	         
	         for(int i = 0; i<states.getLength(); i++) {
	        	 Node temp = states.item(i);
	        	 if(temp instanceof Element) {
	        	 boolean inList = false;
	        	 for(History hist:history) {
	        		if(temp.getNodeName().equals(hist.getID())) {
	        			inList= true;
	        		}
	        	 }
	        	 if(!inList) {
	        		 
     			root.appendChild(xml.importNode(temp, true));
	        	 }
	        	 }
     	 	}  	 
	         
			
	      } catch (ParserConfigurationException e) {
	    	  e.printStackTrace();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      } catch (SAXException e) {
	    	  e.printStackTrace();
		}
	      
}

   private void fillElement(Element element, char play,String nodeName,int victory,int[] playweight,int profondeur , Document xml) {
	   	   int poids = 1;
	   
		   Element etat = xml.createElement(nodeName);
		   char[] nodeChar = etat.getNodeName().toCharArray();
		   System.out.println(String.valueOf(nodeChar) + " :fils de : "+ element.getNodeName());
		   element.appendChild(etat);
		   
		   int i = 0;
		   for(char c:LearningIAcore.CHAR) {
			   //parcourir le nom du noeud et si � l'emplacement de c il n'y a pas v, poids = 0
			   
			   if(nodeChar[i] != 'v') {
				   poids = 0;
			   }else {
				   poids = playweight[i];
			   
			   
				   if(c == play && victory == GameLogic.CROIX_ID) {
					   poids = poids + 3 + profondeur;
				   }
				   if(c == play && victory == GameLogic.EX_AEQUO_ID) {
					   poids = poids + 1;
				   }
				   if(c == play && victory == GameLogic.ROND_ID) {
					   poids = poids - 1 - profondeur;
				   }
			   }
			   if(poids < 0) {
				   poids = 0;
		   }
		   Element coups = xml.createElement(String.valueOf(c));
		   coups.setAttribute("poids", String.valueOf(poids));
		   etat.appendChild(coups); 
		   i++;
		   }
		   
   }

   
   
   
}
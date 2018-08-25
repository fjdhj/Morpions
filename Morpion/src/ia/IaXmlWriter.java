package ia;

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

import gamelogic.GameLogic;

public class IaXmlWriter {

	private static char[] CasesChar = {'a','b','c','d','e','f','g','h','i'};
	
   public IaXmlWriter() {
	   
   }
   
   public void writeNeuronalXml(ArrayList<History> history, int victory) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document xml = builder.newDocument();
         
         Element root = xml.createElement("IAtree");
           
         for(History time: history) {
         fillElement(root, time.getPlayedChar(),time.getID(), victory, time.getPlayWeight(), xml);
         }
         
         
         Transformer t = TransformerFactory.newInstance().newTransformer();
         t.setOutputProperty(OutputKeys.INDENT, "yes");
         String resultFile = "IANeuronalTree.xml"; 
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
   
   private static void fillElement(Element element, char play,String nodeName,int victory,int[] playweight , Document xml) {
  		System.out.println("Traitement...");
	   	   int poids = 1;
	   
		   Element etat = xml.createElement(nodeName);
		   System.out.println(etat.getNodeName() + " :fils de : "+ element.getNodeName());
		   element.appendChild(etat);
		   
		   int i = 0;
		   for(char c:LearningIAcore.CHAR) {
			   
			   poids = playweight[i];
			   if(c == play && victory == GameLogic.CROIX_ID) {
				   poids =+3;
			   }
			   if(c == play && victory == GameLogic.EX_AEQUO_ID) {
				   poids =+1;
			   }
			   if(c == play && victory == GameLogic.ROND_ID) {
				   poids =-1;
			   }
		   Element coups = xml.createElement(String.valueOf(c));
		   coups.setAttribute("poids", String.valueOf(poids));
		   etat.appendChild(coups); 
		   i++;
		   }
		   
   }

   
   
   
}
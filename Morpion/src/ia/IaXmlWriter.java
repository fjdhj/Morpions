package ia;

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

public class IaXmlWriter {

	private static char[] CasesChar = {'a','b','c','d','e','f','g','h','i'};
	
   public IaXmlWriter() {
	   
   }
   
   public void writeNeuronalXml() {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document xml = builder.newDocument();
         
         Element root = xml.createElement("IAtree");
                    
         fillElement(root, CasesChar, xml);
         Transformer t = TransformerFactory.newInstance().newTransformer();
         t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "maDtdBibon.dtd");
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
      }       
   }
   
   private static void fillElement(Element element, char[] CasesChar,Document xml) {
	   	   
	   for(char charactere: CasesChar) {
		   Element noeud = xml.createElement(String.valueOf(charactere));
		   noeud.setAttribute("poids", "1");
		   System.out.println(noeud.getNodeName() + " :fils de : "+ element.getNodeName());
		   element.appendChild(noeud);
		   
		   String str = "";
		   for(char charac : CasesChar) {
			   if(charac != charactere) {
				   str+=charac;
			   }
		   }
		   System.out.println("[LOG]  contenu de str : "+str);
				if(str=="") {
					 Element noeudFin = xml.createElement(String.valueOf(charactere));
					 noeudFin.setTextContent("Fond de noeud");
					 noeud.appendChild(noeudFin); 
					break;
				}
		   char[] newChar = str.toCharArray();
		   fillElement(noeud, newChar, xml);
	   }
   }

   
   
   
}
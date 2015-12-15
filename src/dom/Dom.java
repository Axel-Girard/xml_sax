package dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {
	private HashMap<Integer, String> conference;
	private ArrayList<String> titres;

	Dom(){
		conference = new HashMap<Integer, String>();
		titres = new ArrayList<String>();
	}
	
	public static void main(String...strings){
		Dom dom = new Dom();
		dom.parser();
	}

	public void test(){
		/*int taille = conference.size();
		for(int i = 0; i < taille; i++){
			System.out.println(i + " " + conference.get(i));
		}*/
		System.out.println(conference.values());
	}
	
	public void titre(Element titre){
		
	}
	
	public void parser(){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document= builder.parse(new File("TALN-RECITAL-BIB.xml"));

			Element racine = document.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();
			int cpt = 0;
			for (int i = 0; i<nbRacineNoeuds; i++) {
				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					cpt++;
					Element connexion = (Element) racineNoeuds.item(i);
					Element titre = (Element) connexion.getElementsByTagName("titre").item(0);
					System.out.println(cpt + "  " + titre.getTextContent());
					conference.put(cpt, titre.getTextContent());
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		Element connexion = (Element) racineNoeuds.item(i);

		Element xmlTimeOut = (Element) connexion.getElementsByTagName("timeOut").item(0);
		Element xmlNbConnexionMax = (Element) connexion.getElementsByTagName("nbConnexionMax").item(0);
		Element xmlPort = (Element) connexion.getElementsByTagName("port").item(0);
		int nbConnexionMax = Integer.parseInt(xmlNbConnexionMax.getTextContent());
    }*/
}

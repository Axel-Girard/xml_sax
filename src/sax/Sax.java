package sax;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Sax {
	public static void main(String... args) {
		Sax sax = new Sax();
		sax.parse();
	}

	public void parse(){
		try {
			Trace tracer = new Trace();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(tracer);
			reader.parse("TALN-RECITAL-BIB.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Trace extends DefaultHandler {
// Initialiser dans le constructeur
		//private int indent = 0;
		private boolean isConf = false;
		private boolean isTitre = false;
		private boolean isDate = false;
		private boolean isAcronyme = false;
		private boolean isNames = false;
		private boolean isNamesPresidents = false;
		private boolean isLieu = false;
		private boolean isStats = false;
		private int numConference = 0;
		private ArrayList<Conference> conferences;

		private String str = "";
		private String acronyme = "";
		private String nomsPresidents = "";
		private String lieu = "";
		private String statistique = "";
		private int nbArticle = 0;

		Trace(){
			conferences = new ArrayList<Conference>();
		}

		void printIndent() {
			//for (int i=0; i<indent; i++) System.out.print("­-");
		}

		public void indexConferences(){
			for(Conference conference: conferences){
				System.out.println(conference.getTitre());
				for(Integer annee: conference.getAnnees()){
					System.out.print(annee + " ");
				}
				System.out.println("");
				System.out.println(conference.getSortedAnnees());
			}
		}

		public void startDocument() {
			System.out.println("start document");
		}

		public void endDocument () {
			System.out.println("end document\n");
			indexConferences();
		}

		public void	startElement(String uri, String localName, String qName, Attributes atts) {
			printIndent();
			if(qName.equals("conference")){
				isConf = true;
			}
			if(isConf && qName.equals("titre")){
				System.out.println("tittre");
				isTitre = true;
				isConf = false;
			} else {
				isTitre = false;
			}
			if(qName.equals("dateDebut")){
				isDate = true;
			} else {
				isDate = false;
			}
			if(qName.equals("acronyme")){
				isAcronyme = true;
			} else {
				isAcronyme = false;
			}
			if(qName.equals("presidents")){
				isNames = true;
			}
			if(isNames && qName.equals("nom")){
				isNamesPresidents = true;
			}
			if(qName.equals("ville")){
				isLieu = true;
			}
			if(qName.equals("acceptations")){
				isStats = true;
				int length = atts.getLength();
				// process each attribute
				for (int i=0; i<length; i++) {
					String name = atts.getQName(i);
					if(name.equals("soumissions")){
						statistique += atts.getValue(i) + " ";
					}
				}
			}
			if(qName.equals("article")){
				nbArticle++;
			}
			//indent++;
		}

		public void	endElement (String uri, String localName, String qName) {
			//indent--;
			printIndent();
			if(qName.equals("presidents")){
				isNames = false;
				isNamesPresidents = false;
				conferences.get(numConference).addNoms(nomsPresidents);
				nomsPresidents = "";
			}
			if(isLieu && qName.equals("pays")){
				isLieu = false;
				conferences.get(numConference).addLieu(lieu);
				lieu = "";
			}
			if(qName.equals("statistiques")){
				if(isStats){
					isStats = false;
					conferences.get(numConference).addStatistique(statistique);
					statistique = "";
				} else {
					conferences.get(numConference).addStatistique("");
				}
			}
			if(qName.equals("conference")){
				conferences.get(numConference).addNbArticles(nbArticle);
				nbArticle = 0;
			}
		}

		public void ignorableWhitespace	(char[] ch, int start, int length) { 
			printIndent();
		}

		public void processingInstruction (String target, String data) {
			printIndent();
		}

		public void	characters (char[] ch, int start, int length){
			printIndent();
			
			if(isTitre){
				for(int i = start; i < length + start; i++){
					str += ch[i];
				}

				if(!Character.isWhitespace(str.charAt(0))){
					boolean nouveau = true;
					for(int i = 0; i < conferences.size() && nouveau; i++){
						if(nouveau && conferences.get(i).getTitre().equals(str)){
							nouveau = false;
						}
						numConference = i;
					}
					if(nouveau){
						conferences.add(new Conference(str));
					}
					conferences.get(numConference).addAcronymes(acronyme);
					acronyme = "";
				}
			}
			
			if(isDate){
				for(int i = start; i < length + start; i++){
					str += ch[i];
				}
				String[] parts = str.split("-");

				if(str.length() > 0 && !Character.isWhitespace(str.charAt(0))){
					conferences.get(numConference).addAnnees(Integer.parseInt(parts[0]));
				}
			}
			
			if(isAcronyme){
				for(int i = start; i < length + start; i++){
					if(str.length() > 0 && !Character.isWhitespace(ch[i])){
						acronyme += ch[i];
					}
				}
			}
			
			if(isNamesPresidents){
				for(int i = start; i < length + start; i++){
					if(!Character.isWhitespace(ch[i])){
						nomsPresidents += ch[i];
					}
				}
			}
			
			if(isLieu){
				for(int i = start; i < length + start; i++){
					if(!Character.isWhitespace(ch[i])){
						lieu += ch[i];
					}
				}
			}
			
			if(isStats){
				for(int i = start; i < length + start; i++){
					if(!Character.isWhitespace(ch[i])){
						statistique += ch[i];
						System.out.println(statistique);
					}
				}
			}

			str = "";
		}
	}
}

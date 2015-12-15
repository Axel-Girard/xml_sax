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
		private int indent = 0;
		private boolean conf = false;
		private boolean titre = false;
		private boolean date = false;
		private int numConference = 0;
		private ArrayList<Conference> conferences;
		
		Trace(){
			conferences = new ArrayList<Conference>();
		}
		
		void printIndent() {
			//for (int i=0; i<indent; i++) System.out.print("­-");
		}
		
		public void conf(){
			for(Conference conference: conferences){
				System.out.println(conference.getTitre());
				for(Integer annee: conference.getAnnees()){
					System.out.print(annee + " ");
				}
				System.out.println("");
			}
		}
		
		public void startDocument() {
			System.out.println("start document");
		}
		
		public void endDocument () {
			System.out.println("end document");
			conf();
		}
		
		public void	startElement(String uri, String localName, String qName, Attributes atts) {
			printIndent();
			if(qName.equals("conference")){
				//System.out.println(qName);
				conf = true;
			}
			if(conf && qName.equals("titre")){
				//System.out.println(qName);
				titre = true;
				conf = false;
			} else {
				titre = false;
			}
			if(qName.equals("dateDebut")){
				date = true;
			} else {
				date = false;
			}
			indent++;
		}
		
		public void	endElement (String uri, String localName, String qName) {
			indent--;
			printIndent();
		}

		public void ignorableWhitespace	(char[] ch, int start, int length) { 
			printIndent();
		}
		public void processingInstruction (String target, String data) {
			printIndent();
		}
		
		public void	characters (char[] ch, int start, int length){
			printIndent();
			String str = "";
			if(titre){
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
				}
			}
			
			if(date){
				System.out.print("date : ");
				for(int i = start; i < length + start; i++){
					str += ch[i];
				}
				String[] parts = str.split("-");

				if(!Character.isWhitespace(str.charAt(0))){
					System.out.println(parts[0] + " " + numConference);
					conferences.get(numConference).addAnnees(Integer.parseInt(parts[0]));
				}
			}

			str = "";
		}
	}
}

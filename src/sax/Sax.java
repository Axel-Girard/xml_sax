package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Sax {
	public static void main(String... args) {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse("acteurs.xml", new DefaultHandler() {
				public void startDocument() throws SAXException {
					System.out.println("startDocument"); 
				}
			    public void endDocument() throws SAXException {
			    	System.out.println("endDocument");
			    }
			    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			    	System.out.print("<" + qName);
			    	for(int i = 0; i < attributes.getLength(); i++){
			    		System.out.print(" " + attributes.getLocalName(i)+"=\""+attributes.getValue(i)+"\"");
			    	}
			    	System.out.print(">");
			    }
			    public void endElement(String uri, String localName, String qName) throws SAXException {
			    	System.out.print("</" + qName);
			    	System.out.print(">");
			    }
			    public void characters(char[] ch, int start, int length) throws SAXException {
			    	for(int i = start; i < start + length ; i++){
			    		//if(!Character.isWhitespace(ch[i])){
			    			System.out.print(ch[i]);
			    		//}
			    	}
			    }
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

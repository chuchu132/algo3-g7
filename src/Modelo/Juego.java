package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.XPP3Reader;
import org.xmlpull.v1.XmlPullParserException;

public class Juego {
	
	private static Jugador jugador;
	private static Pista pista;
	
	
	public static void guardar(String ruta) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element raiz = document.addElement("Lista");
		
		String rutaArchivo = ruta;
		
		if(rutaArchivo != null){
			raiz.add(jugador.serialize());
			raiz.add(pista.serialize());
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(rutaArchivo), format);
			writer.write(document);
			writer.close();
		}
	}
	
	public static void cargar(String ruta) throws IOException {
		String rutaArchivo = ruta;
		
		if(rutaArchivo != null){
			File aFile = new File(rutaArchivo);
			XPP3Reader xmlReader = new XPP3Reader();
			Document doc = null;
			try {
				doc = xmlReader.read(aFile);
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			
			Element element = doc.getRootElement();
			Iterator it = element.elementIterator();
			
			Element elemJugador = (Element)it.next();
			Element elemPista = (Element)it.next();
			
			jugador = jugador.deserialize(elemJugador);
			pista = pista.deserialize(elemPista);
		}
	}
	
}

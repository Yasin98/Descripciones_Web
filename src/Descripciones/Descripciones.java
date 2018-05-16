package Descripciones;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Descripciones {

	private static Logger logger = Logger.getLogger(Descripciones.class.getName());

	public static void main(String[] args) {
		try {
			ExtraerDescripcion("https://www.google.es/");
		} catch (IOException e) {
			logger.log(Level.SEVERE,"I/O Exception");
		}
	}

    public static void ExtraerDescripcion(String url) throws IOException {	
    	
    	try {
		Document doc = Jsoup.connect(url).get();
		if(doc.select("meta[name=description]").isEmpty()) {
			logger.log(Level.INFO, "No existe descripción");
		}
		else {
			String Descripcion = doc.select("meta[name=description]").first().attr("content");
		    System.out.println("Descripción del sitio: " + Descripcion);
		}

    	}
    	catch(UnknownHostException ex) {
			logger.log(Level.INFO, "Problema al establecer conexión con el host");
    	}
    	catch(HttpStatusException exc) {
			logger.log(Level.INFO, "Error al buscar la URL");
    	}
    	catch(IllegalArgumentException exce) {
			logger.log(Level.INFO, "URL No válida");
    	}
    	catch(SocketTimeoutException excep) {
			logger.log(Level.INFO, "Error en la Red");
    	}
    }

}

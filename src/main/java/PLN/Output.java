package PLN;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * @author N. Ibrahim Hernández Jorge
 * @date 2017
 * @subject Sistemas Inteligentes
 * @organization ULL
 * 
 * Clase que gestiona las escrituras en ficheros.
 */
public class Output {
	public Output()
	{}
	//Sola para el vocabulario
	void escribirFichero(String fichero, Hashtable<String, Integer> hashPalabras, int numPalabras) throws IOException{
		FileWriter file = new FileWriter(fichero);
        PrintWriter writer = new PrintWriter(file);
        Enumeration<String> e = hashPalabras.keys(); //Contiene las claves del hash
        List<String> list = Collections.list(e);         
        Collections.sort(list);
        //---------------------------------
        //numPalabras esta con repeticiones
        writer.println("Numero de palabras: " + hashPalabras.size() + "\n");
        
        //Mientras hay claves en el hash, imprimir en el fichero.
       
        //---------------------------
        for(Object clave: list) {
        	        
        	writer.println("Palabra: " + (String)clave + " Count: " + hashPalabras.get((String)clave));
        }
        writer.println("Palabra: " + "<UNKNOWN>" + " Count: " + "0");
      //---------------------------
        writer.close();
	}
	
	void escribirFicheroAprendizaje(String fichero, Hashtable<String, Integer> hashPalabras, Hashtable<String, Double> hashProbabilidades,
			int numPreguntas,int numPalabras) throws IOException{
		FileWriter file = new FileWriter(fichero);
        PrintWriter writer = new PrintWriter(file);
        Enumeration<String> e = hashPalabras.keys(); //Contiene las claves del hash
       
        List<String> list = Collections.list(e);         
        Collections.sort(list);
      
        writer.println("Numero de documentos(preguntas) del corpus : " + numPreguntas);
        writer.println("Numero de palabras del corpus: " + numPalabras);
        
        //Mientras haya palabras, imprimir en el fichero.
                
        for(Object palabra: list) {
        	writer.println("Palabra: " + (String)palabra + " Frec: " + hashPalabras.get((String)palabra) +
        			" LogProb: " + hashProbabilidades.get((String)palabra));
        }
     
        writer.close();
	}
	
	void escribirFicheroClasificacion(String fichero ,ArrayList<Integer> clasificacion) throws IOException {
		FileWriter file = new FileWriter(fichero);
        PrintWriter writer = new PrintWriter(file);
        
        for(int i = 0; i < clasificacion.size(); i++) {
        		switch(clasificacion.get(i)) {
        			case 1: writer.println("luces");break;
        			case 2: writer.println("horno");break;
        			case 3: writer.println("regar");break;
        			case 4: writer.println("alarma");break;
        			case 5: writer.println("ventana");break;
        			case 6: writer.println("puertas");break;
        			case 7: writer.println("garaje");break;
        			case 8: writer.println("calefaccion");break;
        			case 9: writer.println("aire_acondicionado");break;
        		}
        }
   
        writer.close();
        
        //Porcentaje de aciertos corpus todo
        if(clasificacion.size() == 19000) {       
	        ArrayList<Integer> totalCorpus = new ArrayList<Integer>();
	        totalCorpus.add(990);
	        totalCorpus.add(908);
	        totalCorpus.add(841);
	        totalCorpus.add(950);
	        totalCorpus.add(954);
	        
	        totalCorpus.add(993);
	        totalCorpus.add(757);
	        totalCorpus.add(984);
	        totalCorpus.add(905);
	        totalCorpus.add(956);
	        
	        totalCorpus.add(991);
	        totalCorpus.add(993);
	        totalCorpus.add(997);
	        totalCorpus.add(896);
	        totalCorpus.add(962);
	        
	        totalCorpus.add(989);
	        totalCorpus.add(988);
	        totalCorpus.add(958);
	        totalCorpus.add(991);
	        totalCorpus.add(997);
	        
	        int aciertos = 0;
	        int numPregunta = 0;
	        for(int i = 0; i < totalCorpus.size(); i++) {
	        	for (int j = 0; j < totalCorpus.get(i); j++) {
	        		if ((i + 1) == clasificacion.get(numPregunta)) {
	        			aciertos++;
	        		}
	        		numPregunta++;
	        	}
	        }
	        
	        double porcentaje = (aciertos / 19000.0) * 100.0;
	        System.out.println("Procentaje corpus todo: " + porcentaje);
        }
	}
}

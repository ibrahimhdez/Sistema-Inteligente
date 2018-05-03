package PLN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * 
 * @author N. Ibrahim Hernández Jorge
 * @date 2017
 * @subject Sistemas Inteligentes
 * @organization ULL
 * 
 * Clase que gestiona las lecturas de ficheros.
 */
public class Input {
	private int numPalabras; //Cogiendo repetidas
	private int numPreguntas;

	public Input()
	{}
	
	Hashtable<String, Integer> leerFichero(String fichero) throws IOException {
		this.setNumPalabras(0);
		this.setNumPreguntas(0);
		
		Hashtable<String, Integer> hashPalabras = new Hashtable<>();
		FileReader reader = new FileReader(fichero);
		BufferedReader buffer = new BufferedReader(reader);
		StringTokenizer tokenizador;
		String cadena; //String que contendrá las diferentes líneas 
		String palabra;
		String auxPalabra;
		//Pattern patron = "(/d+ ./ d+)";
		boolean modificada = false;
		int iteracion = 0;
		
		while((cadena = buffer.readLine()) != null) { //Mientras el fichero tiene más líneas
			this.setNumPreguntas(this.getNumPreguntas() + 1);
						
			tokenizador = new StringTokenizer(cadena); //Inicializar el tokenizador con un string que contiente una línea del fichero para separarla por palabras.
						
			while(tokenizador.hasMoreTokens()){
				palabra = tokenizador.nextToken();
				
				
				//Quitar signos de puntación de las palabras
				if(!isDouble(palabra))
					palabra = palabra.replace('.', ' ');
			
				palabra = palabra.toLowerCase();
				palabra = palabra.replace(',', ' ');
				palabra = palabra.replace('?', ' ');
				palabra = palabra.replace('"', ' ');
				palabra = palabra.replace('\'', ' ');
				palabra = palabra.replace('/', ' ');
				palabra = palabra.replace('(', ' ');
				palabra = palabra.replace(')', ' ');
				palabra = palabra.replaceAll("[!#$%&*+@:.;<>=\\[\\]^`{}~]"," ");
				palabra = palabra.replaceAll("[\\|]+"," ");
				palabra = palabra.replaceAll("\\\\"," ");
				palabra = palabra.replaceAll("[_]+","_");
				palabra = palabra.replaceAll("\\d"," ");
				palabra = palabra.replaceAll("\\s","");	
				palabra = palabra.replaceAll("[^(\\w|_)]","");
				
				if((palabra.length() != 0)) {
					//En caso de que una palabra comience con doble comillas se queda un espacio al principio, este if lo quita.
					if(palabra.charAt(0) == ' ')
						palabra = palabra.substring(1, palabra.length());
					
					//En caso que palabra acabe con espacio, eliminar este espacio.
					if(palabra.length() > 1)
						if(palabra.charAt(palabra.length() - 1) == ' ')
							palabra = palabra.substring(0, palabra.length() - 2);
					
					//Bucle que separa las palabras que están separadas por guiones. (Esto-son-cuatro-palabras)	
					for(int i = 0; i < palabra.length(); i++){
						if(palabra.charAt(i) == '-'){
							modificada = true;
							auxPalabra = palabra.substring(iteracion, i);
							
							//Si no se ha insertado en el hash, se inserta. Si ya ha sido insertada, se incrementa el contador.
							if(hashPalabras.get(auxPalabra) != null) 
								hashPalabras.put(auxPalabra, hashPalabras.get(auxPalabra) + 1);
							else
								hashPalabras.put(auxPalabra, 1);
							
							numPalabras++;
							iteracion = i + 1;
						}	
					}
					
					//Si tiene guiones, el string 'palabra' deberá contener la última palabra.
					if(modificada){
						palabra = palabra.substring(iteracion, palabra.length());
						modificada = false;
					}
					iteracion = 0;
					
					//Si no se ha insertado en el hash, se inserta. Si ya ha sido insertada, se incrementa el contador.
					if(hashPalabras.get(palabra) != null) 
						hashPalabras.put(palabra, hashPalabras.get(palabra) + 1);
					else
						hashPalabras.put(palabra, 1);
					numPalabras++;
				}
			}
		}
				
		buffer.close();
		
		return hashPalabras; 
	}	
	
	Hashtable<String, Integer> leerFicheroVocabulario(String fichero) throws IOException {
		setNumPalabras(0);
	
		Hashtable<String, Integer> hashPalabras = new Hashtable<>();
		FileReader reader = new FileReader(fichero);
		BufferedReader buffer = new BufferedReader(reader);
		
		String cadena;
		String[] palabras;
		String auxPalabra;
		
		cadena = buffer.readLine(); //Cabecera	
		palabras = cadena.split("\\s");		
		this.setNumPalabras(Integer.parseInt(palabras[3]));// N Palabras del vocabulario

		while((cadena = buffer.readLine()) != null) {
			palabras = cadena.split("\\s");
			if(palabras.length == 4) {
				auxPalabra = palabras[1];
				hashPalabras.put(auxPalabra, -1);
				
				//System.out.println(palabras[1]);
			}
		}
		buffer.close();
		
		return hashPalabras; 
	}	
	
	
	 ArrayList<Integer> leerFicherosClasificacion (String sentencia, String ficheroAprendizaje, int numClases) throws IOException {	
		ArrayList<Hashtable<String, Double>> aprendizajeArray = new ArrayList<Hashtable<String, Double>>();
			
		int contador = 1;
		while(contador  <=  numClases) { //Ficheros de aprendizaje		
			FileReader reader = new FileReader(ficheroAprendizaje);
			BufferedReader buffer = new BufferedReader(reader);
			
			Hashtable<String, Double> hashProbabilidades = new Hashtable<>();
			String cadena = "";
			String[] palabras;
			
			cadena = buffer.readLine();
			cadena = buffer.readLine();
			
			while((cadena = buffer.readLine()) != null) { //Extrayendo palarbas de aprendizaje
				palabras = cadena.split("\\s");
								
				if(palabras.length == 6) {
					hashProbabilidades.put(palabras[1], Double.parseDouble(palabras[5]));										
				}
			}
			aprendizajeArray.add(hashProbabilidades);
					
			buffer.close();
			contador++;		
			ficheroAprendizaje = ficheroAprendizaje.replaceFirst("\\d+", Integer.toString(contador));				
		}	
		
		//---------------------Clasificando 
		 ArrayList<Integer> totalCorpus = new ArrayList<Integer>();
		 ArrayList<Double> probCorpus = new ArrayList<Double>();
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
		
		for(int i = 0; i < totalCorpus.size(); i++) {
			probCorpus.add(Math.log(totalCorpus.get(i) / 19000.0));
		}
		//System.out.println(probCorpus);
		
		/*FileReader reader = new FileReader(ficheroCorpus);
		BufferedReader buffer = new BufferedReader(reader);*/
		ArrayList<Integer> clasificacionFinal = new ArrayList<Integer>();
		Hashtable<String, Integer> preguntaHash = tratarPregunta(sentencia);
			
		ArrayList<String> palabras = Collections.list(preguntaHash.keys());		
		ArrayList<Double> totales = new ArrayList<Double>();
			
		
		for(int i = 0; i < aprendizajeArray.size(); i++) { //Prob de cada clase
			Double suma = (double) 0;
			Double probPalabra = (double) 0;
				
			for(int j = 0; j < palabras.size(); j++) {
				probPalabra = 0.0;
				if (aprendizajeArray.get(i).containsKey(palabras.get(j))) 
					probPalabra = aprendizajeArray.get(i).get(palabras.get(j));					
					
				else // Palabras desconocidas
					probPalabra = aprendizajeArray.get(i).get("<UNKNOWN>");
					
				suma = suma + (probPalabra * preguntaHash.get(palabras.get(j)));
			}
				
			totales.add(suma + probCorpus.get(i));
		}
			
		Double mayor = totales.get(0);
		int clase = 1;
		for(int i = 1; i < totales.size(); i++) {
			if (mayor < totales.get(i)) {
				mayor = totales.get(i);
				clase = i + 1;
			}
		}
			
		clasificacionFinal.add(clase);			
		
		return clasificacionFinal;
	}
	
	public Hashtable<String, Integer> tratarPregunta(String cadena) {
		Hashtable<String, Integer> hashPalabras = new Hashtable<>();
	
		StringTokenizer tokenizador;
		
		String palabra;
		String auxPalabra;
	
		boolean modificada = false;
		int iteracion = 0;
					
					
		tokenizador = new StringTokenizer(cadena); //Inicializar el tokenizador con un string que contiente una línea del fichero para separarla por palabras.
					
		while(tokenizador.hasMoreTokens()){
			palabra = tokenizador.nextToken();
			
			
			//Quitar signos de puntación de las palabras
			if(!isDouble(palabra))
				palabra = palabra.replace('.', ' ');
		
			palabra = palabra.toLowerCase();
			palabra = palabra.replace(',', ' ');
			palabra = palabra.replace('?', ' ');
			palabra = palabra.replace('"', ' ');
			palabra = palabra.replace('\'', ' ');
			palabra = palabra.replace('/', ' ');
			palabra = palabra.replace('(', ' ');
			palabra = palabra.replace(')', ' ');
			palabra = palabra.replaceAll("[!#$%&*+@:.;<>=\\[\\]^`{}~]"," ");
			palabra = palabra.replaceAll("[\\|]+"," ");
			palabra = palabra.replaceAll("\\\\"," ");
			palabra = palabra.replaceAll("[_]+","_");
			palabra = palabra.replaceAll("\\d"," ");
			palabra = palabra.replaceAll("\\s","");	
			palabra = palabra.replaceAll("[^(\\w|_)]","");
			
			if((palabra.length() != 0)) {
				//En caso de que una palabra comience con doble comillas se queda un espacio al principio, este if lo quita.
				if(palabra.charAt(0) == ' ')
					palabra = palabra.substring(1, palabra.length());
				
				//En caso que palabra acabe con espacio, eliminar este espacio.
				if(palabra.length() > 1)
					if(palabra.charAt(palabra.length() - 1) == ' ')
						palabra = palabra.substring(0, palabra.length() - 2);
				
				//Bucle que separa las palabras que están separadas por guiones. (Esto-son-cuatro-palabras)	
				for(int i = 0; i < palabra.length(); i++){
					if(palabra.charAt(i) == '-'){
						modificada = true;
						auxPalabra = palabra.substring(iteracion, i);
						
						//Si no se ha insertado en el hash, se inserta. Si ya ha sido insertada, se incrementa el contador.
						if(hashPalabras.get(auxPalabra) != null) 
							hashPalabras.put(auxPalabra, hashPalabras.get(auxPalabra) + 1);
						else
							hashPalabras.put(auxPalabra, 1);
						
						numPalabras++;
						iteracion = i + 1;
					}	
				}
				
				//Si tiene guiones, el string 'palabra' deberá contener la última palabra.
				if(modificada){
					palabra = palabra.substring(iteracion, palabra.length());
					modificada = false;
				}
				iteracion = 0;
				
				//Si no se ha insertado en el hash, se inserta. Si ya ha sido insertada, se incrementa el contador.
				if(hashPalabras.get(palabra) != null) 
					hashPalabras.put(palabra, hashPalabras.get(palabra) + 1);
				else
					hashPalabras.put(palabra, 1);
				numPalabras++;
			}
		}
				
		return hashPalabras; 
	}
	
	boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	public int getNumPalabras() {
		return numPalabras;
	}

	public void setNumPalabras(int numPalabras) {
		this.numPalabras = numPalabras;
	}
	
	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
	}
}

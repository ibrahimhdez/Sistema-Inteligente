package PLN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 
 * @author N. Ibrahim Hernández Jorge
 * @date 2017
 * @subject Sistemas Inteligentes
 * @organization ULL
 * 
 * Clase que gestiona las clases de escritura y lectura de ficheros.
 */
public class Controlador {
	private Hashtable<String, Integer> misPalabras;
	private Hashtable<String, Integer> miVocabulario;
	private ArrayList<Integer> clasificacion;
	private String nombreFicheroEntrada;
	private String nombreFicheroSalida;
	private String nombreFicheroVocabulario;
	private Input input;
	private Output output;
	
	public Controlador() {
		this.setInput(new Input());
		this.setOutput(new Output());
	}

	public Controlador(String ficheroVocabulario, String ficheroCorpus, String ficheroSalida) {
		this.setMisPalabras(new Hashtable<String, Integer>());
		this.setInput(new Input());
		this.setOutput(new Output());
		this.setNombreFicheroEntrada(ficheroCorpus);
		this.setNombreFicheroSalida(ficheroSalida);
		this.setNombreFicheroVocabulario(ficheroVocabulario);
	}
	
	public Controlador(String ficheroEntrada, String ficheroSalida) {
		this.setMisPalabras(new Hashtable<String, Integer>());
		this.setInput(new Input());
		this.setOutput(new Output());
		this.setNombreFicheroEntrada(ficheroEntrada);
		this.setNombreFicheroSalida(ficheroSalida);
	}
	
	void iniciarComponentes() throws IOException{
		this.setMisPalabras(this.getInput().leerFichero(this.getNombreFicheroEntrada()));
		this.getOutput().escribirFichero(this.getNombreFicheroSalida(), this.getMisPalabras(), this.getInput().getNumPalabras());
	}
	
	void estimarProbabiladades() throws IOException {
		this.setMisPalabras(this.getInput().leerFichero(this.getNombreFicheroEntrada()));
		int numPreguntasCorpus = this.getInput().getNumPreguntas();
		int numPalabrasCorpus = this.getInput().getNumPalabras();
		
		this.setMiVocabulario(this.getInput().leerFicheroVocabulario(this.getNombreFicheroVocabulario()));
		int numPalabrasVocabulario = this.getMiVocabulario().size();
		
		
		Enumeration<String> e = this.getMiVocabulario().keys();
			
		Object palabra;
		Object frecuencia;
		Hashtable<String, Double> palabrasProbabilidad = new Hashtable<>();
		
		while( e.hasMoreElements() ) { //Mientras haya palabras del vocabulario
		  palabra = e.nextElement();
		  frecuencia = this.getMisPalabras().get(palabra); //Frecuencia de la palabra en el corpus
		  
		  if(frecuencia == null) {			 
			  frecuencia = 0;
		  }
			 
		
		  this.getMiVocabulario().put((String)palabra, (Integer)frecuencia);
		  
		  //Calcular probabilidad
		  
		  //Con log en base e?
		  Double prob = Math.log(((Integer)frecuencia + 1.0) / (numPalabrasCorpus + numPalabrasVocabulario + 1.0));
		  palabrasProbabilidad.put((String)palabra, prob);		  
		}		
		
		//System.out.println("num Palab: " + numPalabrasCorpus);
		//System.out.println("Num Preg:" + numPreguntasCorpus);
		
		this.getOutput().escribirFicheroAprendizaje(this.getNombreFicheroSalida(), this.getMiVocabulario(), 
				palabrasProbabilidad, numPreguntasCorpus, numPalabrasCorpus);
		
	}
	
	public void clasificar(String ficheroCorpus, String ficheroAprendizaje, String ficheroSalida, int numClases) throws IOException {
		this.setClasificacion(this.getInput().leerFicherosClasificacion(ficheroCorpus, ficheroAprendizaje, numClases));
		//this.getOutput().escribirFicheroClasificacion(ficheroSalida, clasificacion);
	}

	public Hashtable<String, Integer> getMisPalabras() {
		return misPalabras;
	}

	public void setMisPalabras(Hashtable<String, Integer> misPalabras) {
		this.misPalabras = misPalabras;
	}
	
	public Hashtable<String, Integer> getMiVocabulario() {
		return miVocabulario;
	}

	public void setMiVocabulario(Hashtable<String, Integer> miVocabulario) {
		this.miVocabulario = miVocabulario;
	}
	
	public ArrayList<Integer> getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(ArrayList<Integer> arrayList) {
		this.clasificacion = arrayList;
	}

	public String getNombreFicheroEntrada() {
		return nombreFicheroEntrada;
	}

	public void setNombreFicheroEntrada(String nombreFicheroEntrada) {
		this.nombreFicheroEntrada = nombreFicheroEntrada;
	}

	public String getNombreFicheroSalida() {
		return nombreFicheroSalida;
	}

	public void setNombreFicheroSalida(String nombreFicheroSalida) {
		this.nombreFicheroSalida = nombreFicheroSalida;
	}
	
	public String getNombreFicheroVocabulario() {
		return nombreFicheroVocabulario;
	}

	public void setNombreFicheroVocabulario(String nombreFicheroVocabulario) {
		this.nombreFicheroVocabulario = nombreFicheroVocabulario;
	}
	
	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}
}

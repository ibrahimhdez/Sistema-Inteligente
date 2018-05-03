package PLN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * 
 * @author IbrahimHernandez
 *	Recibe vocabulario.txt y corpus.txt
 */

public class GenerarVocabulario {
	private ArrayList<String> palabras;
	private Hashtable<String, Integer> hash;

	public GenerarVocabulario(){
		this.setPalabras(new ArrayList<String>());
		this.setHash(new Hashtable<String, Integer>());
	}

	void leerFichero(String fileName) throws IOException {
		FileReader fichero = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader(fichero);
		StringTokenizer tokenizer;
		String cadena = "";
		
		while((cadena = buffer.readLine()) != null) {
			tokenizer = new StringTokenizer(cadena);
			
			while(tokenizer.hasMoreElements()) 
				this.getPalabras().add(tokenizer.nextToken().toLowerCase());
		}
		buffer.close();
	}
	
	void escribirFichero(String fileName) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		BufferedWriter buffer = new BufferedWriter(writer);
		Enumeration<String> enumeration = this.getHash().keys();
		
		buffer.write("Número de palabras: " + this.getHash().size() + "\n\n");
		buffer.write("Palabra: " + "<UNKNOWN>" + " Count: " + 0 + "\n");
		
		while(enumeration.hasMoreElements()) {
			String elemento = enumeration.nextElement();
			buffer.write("Palabra: " + elemento + " Count: " + this.getHash().get(elemento) + "\n");
		}
		buffer.close();
	}
	
	void generarHash() {
		for(int i = 0; i < this.getPalabras().size(); i++) {
			if(!this.getHash().containsKey(this.getPalabras().get(i)))
				this.getHash().put(this.getPalabras().get(i), 1);
			
			else
				this.getHash().put(this.getPalabras().get(i), this.getHash().get(this.getPalabras().get(i)) + 1);
		}
	}

	static public void main(String args[]) throws IOException{
		if(args.length == 2){
			GenerarVocabulario generar = new GenerarVocabulario();
			generar.leerFichero(args[0]);
			generar.generarHash();
			generar.escribirFichero(args[1]);
			System.out.println("Done");
		}

		else 
			System.out.println("Usage: java GenerarVocabulario <ficheroSentencias> <ficheroOutput>");
	}

	public ArrayList<String> getPalabras() {
		return palabras;
	}

	public void setPalabras(ArrayList<String> palabras) {
		this.palabras = palabras;
	}

	public Hashtable<String, Integer> getHash() {
		return hash;
	}

	public void setHash(Hashtable<String, Integer> hash) {
		this.hash = hash;
	}
}
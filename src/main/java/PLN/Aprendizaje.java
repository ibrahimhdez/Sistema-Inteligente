package PLN;

import java.io.IOException;

/**
 * 
 * @author N. Ibrahim Hernández Jorge
 * @date 2017
 * @subject Sistemas Inteligentes
 * @organization ULL
 * 
 * Clase que gestiona el aprendizaje.
 */

public class Aprendizaje {
	static public void main(String args[]) throws IOException {
		if(args.length != 4) {
			System.out.println("Usage: java Main <Vocabulary_file> <corpus_file> <output_file>");
			System.out.println("Note: The program assume that corpus file and output file are the firsts of 20 others");
		}
		else {
			String ficheroVocabulario = args[0].toLowerCase();
			String ficheroCorpus = args[1].toLowerCase();
			String ficheroSalida = args[2].toLowerCase();
				
			int contador = 1;
			Integer numFicheros = new Integer(args[3]);
			while(contador  <= numFicheros) {
				Controlador miControlador = new Controlador(ficheroVocabulario, ficheroCorpus, ficheroSalida);
				miControlador.estimarProbabiladades();
				
				contador++;				
				ficheroCorpus = ficheroCorpus.replaceFirst("\\d+", Integer.toString(contador));
				ficheroSalida = ficheroSalida.replaceFirst("\\d+", Integer.toString(contador));			
			}	
			System.out.println("Done");
		}
	}
}

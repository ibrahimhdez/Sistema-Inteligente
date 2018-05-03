package PLN;

import java.io.IOException;

/**
 * 
 * @author N. Ibrahim Hernández Jorge
 * @date 2017
 * @subject Sistemas Inteligentes
 * @organization ULL
 * 
 * Clase que gestiona la clasificación.
 * 
 * Recibe fichero con frase a probar, aprendizaje1.txt y fichero de salida.
 */

public class Clasificacion {
	private Controlador controlador;
	
	public Clasificacion() {
		this.setControlador(new Controlador());
	}
	
	public Integer clasificar(String rutaAprendizaje, String sentencia, int numClases) throws IOException {
		String ficheroAprendizaje = rutaAprendizaje;	
		String ficheroSalida = "src/main/java/PLN/solucion.txt";
		
		this.getControlador().clasificar(sentencia, ficheroAprendizaje, ficheroSalida, numClases);

		return this.getControlador().getClasificacion().get(0);
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}

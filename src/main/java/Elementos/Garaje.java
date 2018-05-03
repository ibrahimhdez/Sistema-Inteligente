package Elementos;

public class Garaje extends Objeto{
	public Garaje() {
		super(false, "garaje", 2);
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setAbierto(true);
		else if(accion.equals("apagar"))
			this.setAbierto(false);
	}
	
	public String toString() {
		String string = "Puerta garaje ";
		
		if(this.getAbierto())
			string += "abierta";
		else
			string += "cerrada";
		
		return string;
	}

	public Boolean getAbierto() {
		return this.getEncendido();
	}

	public void setAbierto(Boolean abierto) {
		this.setEncendido(abierto);
	}
}

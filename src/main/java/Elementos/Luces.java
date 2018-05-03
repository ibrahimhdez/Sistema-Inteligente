package Elementos;

public class Luces extends Objeto{
	private Lugar lugar;
	
	public Luces() {
		super(false, "luces", 2);
		this.setLugar(new Lugar(""));
		this.setNumeroAcciones(2);
	}
	
	public Luces(Boolean encendida, Lugar lugar) {
		super(encendida, "luces", 2);
		this.setLugar(lugar);
	}
	
	public Luces(String lugar) {
		super(false, "luces", 2);
		this.setLugar(new Lugar(lugar));
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setEncendido(true);
		else if(accion.equals("apagar"))
			this.setEncendido(false);
	}
	
	public String toString() {
		String string = "Luz " + this.getLugar();
		
		if(this.getEncendido())
			string += " encendida";
		else
			string += " apagada";
		
		return string;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
}

package Elementos;

public class Ventana extends Objeto{
	private Lugar Lugar;
	private Integer numeroAcciones;
	
	public Ventana() {
		super(false, "ventana", 2);
		this.setLugar(new Lugar(""));
	}
	
	public Ventana(Boolean abierta, Lugar lugar) {
		super(abierta, "ventana", 2);
		this.setLugar(lugar);
	}
	
	public Ventana(String lugar) {
		super(false, "ventana", 2);
		this.setLugar(new Lugar(lugar));
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setAbierta(true);
		else if(accion.equals("apagar"))
			this.setAbierta(false);
	}

	public String toString() {
		String string = "Ventana " + this.getLugar();
		
		if(this.getAbierta())
			string += " abierta";
		else
			string += " cerrada";
		
		return string;
	}
	
	public Boolean getAbierta() {
		return this.getEncendido();
	}

	public void setAbierta(Boolean abierta) {
		this.setEncendido(abierta);
	}

	public Lugar getLugar() {
		return Lugar;
	}

	public void setLugar(Lugar lugar) {
		Lugar = lugar;
	}

	public Integer getNumeroAcciones() {
		return numeroAcciones;
	}

	public void setNumeroAcciones(Integer numeroAcciones) {
		this.numeroAcciones = numeroAcciones;
	}
}

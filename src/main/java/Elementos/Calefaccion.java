package Elementos;

public class Calefaccion extends Objeto{
	private Integer grados;
	
	public Calefaccion() {
		super(false, "calefaccion", 4);
		this.setGrados(0);
	}
	
	public Calefaccion(Boolean encendida, Integer grados) {
		super(encendida, "calefaccion", 4);
		comprobarGrados(grados);
	}
	
	private void comprobarGrados(Integer grados) {
		if(this.getEncendido()) {
			if(comprobarRango(grados))
				this.grados = grados;
		}
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setEncendida(true);
		else if(accion.equals("apagar"))
			this.setEncendida(false);
		else if(accion.equals("disminuir"))
			this.setGrados(this.getGrados() - 5);
		else if(accion.equals("aumentar"))
			this.setGrados(this.getGrados() + 5);
	}
	
	private Boolean comprobarRango(Integer grados) {
		return ((grados >= 15) && (grados <= 35));
	}
	
	public String toString() {
		if(this.getEncendido())
			return "Calefacción encendida a " + this.getGrados() + " grados";
		else
			return "Calefacción apagada";
	}
	
	public Integer aumentarPotencia() {
		if(this.getGrados() == null)
			this.grados = 15;
		if(this.getGrados() < 20) {
			this.setGrados(20);
			return 128;
		}

		else {
			this.setGrados(35);
			return 255;
		}
	}
	
	public Integer disminuirPotencia() {
		if(this.getGrados() < 20) {
			this.setGrados(15);
			return 10;
		}
		
		else {
			this.setGrados(20);
			return 128;
		}
	}

	public void setEncendida(Boolean encendida) {
		super.setEncendido(encendida);
		
		if(!encendida)
			this.grados = 15;
		if(encendida && this.getGrados() == null)
			this.setGrados(20);
	}

	public Integer getGrados() {
		return grados;
	}

	public void setGrados(Integer grados) {
		comprobarGrados(grados);
	}
}

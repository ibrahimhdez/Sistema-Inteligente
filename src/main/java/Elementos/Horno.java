package Elementos;

public class Horno extends Objeto{
	private Integer grados;
	private Lugar lugar;
	
	public Horno() {
		super(false, "horno", 4);
		this.setGrados(0);
		this.setLugar(new Lugar("cocina"));
	}
	
	public Horno(Boolean encendido, Integer grados) {
		super(encendido, "horno", 4);
		comprobarGrados(grados);
	}
	
	private void comprobarGrados(Integer grados) {
		if(this.getEncendido())
			if(grados == 0)
				this.setEncendido(false);
			else if(comprobarRango(grados))
				this.grados = grados;	
	}

	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setEncendido(true);
		else if(accion.equals("apagar"))
			this.setEncendido(false);
		else if(accion.equals("disminuir"))
			this.setGrados(this.getGrados() - 10);
		else if(accion.equals("aumentar"))
			this.setGrados(this.getGrados() + 10);
	}
	
	private Boolean comprobarRango(Integer grados) {
		return ((grados >= 100) && (grados <= 250));
	}
	
	public String toString() {
		if(this.getEncendido())
			return "Horno encendido a " + this.getGrados() + " grados";
		else
			return "Horno apagado";
	}

	public void setEncendido(Boolean encendido) {
		super.setEncendido(encendido);
		
		if(!encendido)
			this.grados = 0;
		else if(this.getGrados() == 0)
			this.setGrados(100);
	}

	public Integer getGrados() {
		return grados;
	}

	public void setGrados(Integer grados) {
		comprobarGrados(grados);
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
}

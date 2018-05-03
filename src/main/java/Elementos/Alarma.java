package Elementos;

public class Alarma extends Objeto{
	private Boolean sonando;
	
	public Alarma() {
		super(false, "alarma", 2);
		this.setSonando(false);
	}
	
	public Alarma(Boolean encendida, Boolean sonando) {
		super(encendida, "alarma", 2);
		comprobarEncendida(sonando);
	}
	
	private void comprobarEncendida(Boolean sonando) {
		if(this.getEncendido() && sonando)
			this.sonando = sonando;
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setEncendida(true);
		else if(accion.equals("apagar"))
			this.setEncendida(false);
	}
	
	public String toString() {
		if(this.getEncendido()) {
			String string = "Alarma encendida";
			
			if(this.getSonando())
				string += " y sonando";
			return string;
		}
		else
			return "Alarma apagada";
	}

	public void setEncendida(Boolean encendida) {
		super.setEncendido(encendida);
		
		if(!encendida)
			this.sonando = false;
	}

	public Boolean getSonando() {
		return sonando;
	}

	public void setSonando(Boolean sonando) {
		comprobarEncendida(sonando);
	}
}

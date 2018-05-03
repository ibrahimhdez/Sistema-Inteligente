package Elementos;

public class AireAcondicionado extends Objeto{
	private Integer grados;
	
	public AireAcondicionado() {
		super(false, "aire_acondicionado", 4);
		this.setGrados(0);
	}
	
	public AireAcondicionado(Boolean encendido, Integer grados) {
		super(encendido, "aire_acondicionado", 4);
		comprobarGrados(grados);
	}
	
	private void comprobarGrados(Integer grados) {
		if(this.getEncendido())
			if(comprobarRango(grados))
				this.grados = grados;
	}
	
	private Boolean comprobarRango(Integer grados) {
		return ((grados >= 0) && (grados <= 20));
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setEncendido(true);
		else if(accion.equals("apagar"))
			this.setEncendido(false);
		else if(accion.equals("disminuir"))
			this.setGrados(this.getGrados() - 5);
		else if(accion.equals("aumentar"))
			this.setGrados(this.getGrados() + 5);
	}
	
	public Integer aumentarPotencia() {
		if(this.getGrados() > 10) {
			this.setGrados(10);
			return 128;
		}

		else {
			this.setGrados(0);
			return 255;
		}
	}
	
	public Integer disminuirPotencia() {
		if(this.getGrados() < 10) {
			this.setGrados(10);
			return 10;
		}
		
		else {
			this.setGrados(20);
			return 128;
		}
	}
	
	public String toString() {
		if(this.getEncendido())
			return "Aire acondicionado encendido a " + this.getGrados() + " grados";
		else
			return "Aire acondicionado apagado";
	}

	public void setEncendido(Boolean encendido) {
		super.setEncendido(encendido);
		
		if(!encendido)
			this.grados = 0;
		else if(this.getGrados() == 0)
			this.setGrados(15);
	}

	public Integer getGrados() {
		return grados;
	}

	public void setGrados(Integer grados) {
		comprobarGrados(grados);
	}
}

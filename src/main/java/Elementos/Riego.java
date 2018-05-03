package Elementos;

public class Riego extends Objeto{
	private String intensidad;

	public Riego() {
		super(false, "regar", 4);
		this.setIntensidad("media");
	}
	
	public Riego(Boolean encendido, String intensidad) {
		super(encendido, "regar", 4);
		comprobarIntensidad(intensidad);
	}
	
	private void comprobarIntensidad(String intensidad) {
		if(this.getEncendido()) 
			if(intensidad == null)
				this.intensidad = "media";
			if((intensidad.equals("floja")) || (intensidad.equals("flojo")) || (intensidad.equals("media")) || (intensidad.equals("medio")) || (intensidad.equals("fuerte")))
				this.intensidad = intensidad;
			else
				this.setEncendido(false);	
	}
	
	public void accionar(String accion) {
		if(accion.equals("encender"))
			this.setEncendido(true);
		else if(accion.equals("apagar"))
			this.setEncendido(false);
		else if(accion.equals("disminuir"))
			disminuirIntensidad();
		else if(accion.equals("aumentar"))
			aumentarIntensidad();
	}
	
	public String disminuirIntensidad() {
		String intensidad = "";
		if(((this.getIntensidad().equals("media")) || (this.getIntensidad().equals("medio")))) {
			intensidad = "flojo";
			this.setIntensidad(intensidad);
		}
		
		
		else if(this.getIntensidad().equals("fuerte")) {
			intensidad = "media";
			this.setIntensidad(intensidad);
		}
		
		return intensidad;
	}
	
	public String aumentarIntensidad() {
		String intensidad = "";
		if(((this.getIntensidad().equals("flojo")) || (this.getIntensidad().equals("floja")))) {
			intensidad = "media";
			this.setIntensidad(intensidad);
		}
		
		
		else if(((this.getIntensidad().equals("media")) || (this.getIntensidad().equals("medio")))) {
			intensidad = "fuerte";
			this.setIntensidad(intensidad);	
		}
		
		return intensidad;
	}
	
	public String toString() {
		String string = "Riego ";
		
		if(this.getEncendido())
			string += "encendido a intensidad " + this.getIntensidad();
		else
			string += "apagado";
		
		return string;
	}

	public void setEncendido(Boolean encendido) {
		super.setEncendido(encendido);
		
		if(!encendido)
			this.intensidad = "";
	}

	public String getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(String intensidad) {
		comprobarIntensidad(intensidad);
	}
}

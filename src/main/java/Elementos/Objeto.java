package Elementos;

public class Objeto {
	private Boolean encendido;
	private String id;
	private Integer numeroAcciones;
	
	public Objeto(Boolean encendido, String id, Integer numeroAcciones) {
		this.setEncendido(encendido);
		this.setId(id);
		this.setNumeroAcciones(numeroAcciones);
	}
	
	public Boolean getEncendido() {
		return encendido;
	}
	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumeroAcciones() {
		return numeroAcciones;
	}

	public void setNumeroAcciones(Integer numeroAcciones) {
		this.numeroAcciones = numeroAcciones;
	}
}

package Elementos;

public class Lugar {
	private String zona;
	
	public Lugar() {
		this.setZona("");
	}
	
	public Lugar(String zona) {
		comprobarZona(zona);
	}
	
	private void comprobarZona(String zona) {
		if((zona.equals("cocina")) || (zona.equals("salon")) || (zona.equals("cuarto")) || (zona.equals("garaje")) || (zona.equals("baño")) || (zona.equals("")))
			this.zona = zona;
	}
	
	public String toString() {
		return this.getZona();
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		comprobarZona(zona);
	}
}

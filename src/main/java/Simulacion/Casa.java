package Simulacion;

import java.util.ArrayList;

import Elementos.AireAcondicionado;
import Elementos.Alarma;
import Elementos.Calefaccion;
import Elementos.Garaje;
import Elementos.Horno;
import Elementos.Luces;
import Elementos.Objeto;
import Elementos.Riego;
import Elementos.Ventana;

public class Casa {
	private ArrayList<Objeto> objetos;
	private Luces luzCocina;
	private Luces luzSalon;
	private Luces luzCuarto;
	private Luces luzGaraje;
	private Luces luzBaño;
	private Riego riego;
	private Horno horno;
	private Calefaccion calefaccion;
	private AireAcondicionado aireAcondicionado;
	private Garaje garaje;
	private Alarma alarma;
	private Ventana ventanaCocina;
	private Ventana ventanaSalon;
	private Ventana ventanaCuarto;
	
	public Casa() {
		this.setObjetos(new ArrayList<Objeto>());
		this.setLuzCocina(new Luces("cocina"));
		this.setLuzSalon(new Luces("salon"));
		this.setLuzCuarto(new Luces("cuarto"));
		this.setLuzGaraje(new Luces("garaje"));
		this.setLuzBaño(new Luces("baño"));
		this.setRiego(new Riego());
		this.setHorno(new Horno());
		this.setCalefaccion(new Calefaccion());
		this.setAireAcondicionado(new AireAcondicionado());
		this.setGaraje(new Garaje());
		this.setAlarma(new Alarma());
		this.setVentanaCocina(new Ventana("cocina"));
		this.setVentanaSalon(new Ventana("salon"));
		this.setVentanaCuarto(new Ventana("cuarto"));
		initArray();
	}
	
	private void initArray() {
		this.getObjetos().add(this.getLuzCocina());
		this.getObjetos().add(this.getLuzSalon());
		this.getObjetos().add(this.getLuzCuarto());
		this.getObjetos().add(this.getLuzGaraje());
		this.getObjetos().add(this.getLuzBaño());
		this.getObjetos().add(this.getRiego());
		this.getObjetos().add(this.getHorno());
		this.getObjetos().add(this.getCalefaccion());
		this.getObjetos().add(this.getCalefaccion());
		this.getObjetos().add(this.getAireAcondicionado());
		this.getObjetos().add(this.getGaraje());
		this.getObjetos().add(this.getAlarma());
		this.getObjetos().add(this.getVentanaCocina()	);
		this.getObjetos().add(this.getVentanaSalon());
		this.getObjetos().add(this.getVentanaCuarto());
	}
	
	public Integer getNumAcciones(String objeto) {
		for(int i = 0; i < this.getObjetos().size(); i++)
			if(this.getObjetos().get(i).getId().equals(objeto))
				return this.getObjetos().get(i).getNumeroAcciones();
		
		return 0;
	}
	
	public String estadoActual() {
		String string = "";
		
		string += this.getLuzCocina() + "\n";
		string += this.getLuzSalon() + "\n";
		string += this.getLuzCuarto() + "\n";
		string += this.getLuzGaraje() + "\n";
		string += this.getLuzBaño() + "\n";
		string += this.getVentanaCocina() + "\n";
		string += this.getVentanaSalon() + "\n";
		string += this.getVentanaCuarto() + "\n";
		string += this.getRiego() + "\n";
		string += this.getHorno() + "\n";
		string += this.getCalefaccion() + "\n";
		string += this.getAireAcondicionado() + "\n";
		string += this.getGaraje() + "\n";
		string += this.getAlarma() + "\n";
		
		return string;
	}

	public ArrayList<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(ArrayList<Objeto> objetos) {
		this.objetos = objetos;
	}

	public Luces getLuzCocina() {
		return luzCocina;
	}

	public void setLuzCocina(Luces luzCocina) {
		this.luzCocina = luzCocina;
	}

	public Luces getLuzSalon() {
		return luzSalon;
	}

	public void setLuzSalon(Luces luzSalon) {
		this.luzSalon = luzSalon;
	}

	public Luces getLuzCuarto() {
		return luzCuarto;
	}

	public void setLuzCuarto(Luces luzCuarto) {
		this.luzCuarto = luzCuarto;
	}

	public Luces getLuzGaraje() {
		return luzGaraje;
	}

	public void setLuzGaraje(Luces luzGaraje) {
		this.luzGaraje = luzGaraje;
	}

	public Luces getLuzBaño() {
		return luzBaño;
	}

	public void setLuzBaño(Luces luzBaño) {
		this.luzBaño = luzBaño;
	}

	public Riego getRiego() {
		return riego;
	}

	public void setRiego(Riego riego) {
		this.riego = riego;
	}

	public Horno getHorno() {
		return horno;
	}

	public void setHorno(Horno horno) {
		this.horno = horno;
	}

	public Calefaccion getCalefaccion() {
		return calefaccion;
	}

	public void setCalefaccion(Calefaccion calefaccion) {
		this.calefaccion = calefaccion;
	}

	public AireAcondicionado getAireAcondicionado() {
		return aireAcondicionado;
	}

	public void setAireAcondicionado(AireAcondicionado aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	public Garaje getGaraje() {
		return garaje;
	}

	public void setGaraje(Garaje garaje) {
		this.garaje = garaje;
	}

	public Alarma getAlarma() {
		return alarma;
	}

	public void setAlarma(Alarma alarma) {
		this.alarma = alarma;
	}

	public Ventana getVentanaCocina() {
		return ventanaCocina;
	}

	public void setVentanaCocina(Ventana ventanaCocina) {
		this.ventanaCocina = ventanaCocina;
	}

	public Ventana getVentanaSalon() {
		return ventanaSalon;
	}

	public void setVentanaSalon(Ventana ventanaSalon) {
		this.ventanaSalon = ventanaSalon;
	}

	public Ventana getVentanaCuarto() {
		return ventanaCuarto;
	}

	public void setVentanaCuarto(Ventana ventanaCuarto) {
		this.ventanaCuarto = ventanaCuarto;
	}
}

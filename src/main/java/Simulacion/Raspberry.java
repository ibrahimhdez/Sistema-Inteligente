package Simulacion;

import java.util.ArrayList;

public class Raspberry {
	private ArrayList<GPIO> gpio;
	private ArrayList<String> comandosActivar;
	private ArrayList<String> comandosDireccionar;
	
	public Raspberry() {
		this.setGpio(new ArrayList<GPIO>());
		this.setComandosActivar(new ArrayList<String>());
		this.setComandosDireccionar(new ArrayList<String>());
		init();
	}
	
	private void init() {
		iniciarPines();
		activarPines();
	}
	
	public String encender(Integer pin) {
		if((pin == 22) || (pin == 23) || (pin == 25))
			return "pigs p " + pin + " 50";
		else
			return "pigs p " + pin + " 255";
	}
	
	public String encender(Integer pin, Integer intensidad) {
		return "pigs p " + pin + " " + intensidad;
	}
	
	public String apagar(Integer pin) {
		return "pigs p " + pin + " 0";
	}
	
	private void iniciarPines() {
		ArrayList<Integer> numeroPin = new ArrayList<Integer>();
		numeroPin.add(4);
		numeroPin.add(5);
		numeroPin.add(6);
		numeroPin.add(12);
		numeroPin.add(13);
		for(int i = 16; i < 28; i++)
			numeroPin.add(i);
		
		for(int i = 0; i < numeroPin.size(); i++)
			this.getGpio().add(new GPIO(numeroPin.get(i), false));
	}
	
	private void activarPines() {
		for(int i = 0; i < this.getGpio().size(); i++) {
			this.getComandosActivar().add("echo " + this.getGpio().get(i).getPin() + " > /sys/class/gpio/export");
			this.getComandosDireccionar().add("echo out > /sys/class/gpio/gpio" + this.getGpio().get(i).getPin() + "/direction");
		}
	}

	public ArrayList<GPIO> getGpio() {
		return gpio;
	}

	public void setGpio(ArrayList<GPIO> gpio) {
		this.gpio = gpio;
	}

	public ArrayList<String> getComandosActivar() {
		return comandosActivar;
	}

	public void setComandosActivar(ArrayList<String> comandosActivar) {
		this.comandosActivar = comandosActivar;
	}

	public ArrayList<String> getComandosDireccionar() {
		return comandosDireccionar;
	}

	public void setComandosDireccionar(ArrayList<String> comandosDireccionar) {
		this.comandosDireccionar = comandosDireccionar;
	}
}

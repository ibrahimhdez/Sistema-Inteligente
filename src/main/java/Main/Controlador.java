package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import com.jcraft.jsch.JSchException;

import CapturaDeVoz.CapturaDeVoz;
import PLN.Clasificacion;
import SSH.SSHConnector;
import Simulacion.Casa;
import Simulacion.Raspberry;
import Speech2Text.ConvertSpeech;

public class Controlador {
	private ConvertSpeech convertidorVoz;
	private Clasificacion clasificador;
	private CapturaDeVoz capturaVoz;
	private Casa casa;
	private Raspberry raspberry;
	private SSHConnector ssh;
	private Integer accion;
	private Integer categoria;
	private Integer subcategoria;
	private Timer timerRiego;
	private Boolean encendido;
	
	public Controlador() throws IOException {
		this.setConvertidorVoz(new ConvertSpeech());   
		this.setClasificador(new Clasificacion());
		this.setCapturaVoz(new CapturaDeVoz());
		this.setCasa(new Casa());
		this.setRaspberry(new Raspberry());
		this.setSsh(new SSHConnector());
		this.setEncendido(false);
		this.setTimerRiego(new Timer (1500, new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
				if(!getEncendido()) {
					try {
						encender(18);
					} catch (IllegalAccessException | JSchException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setEncendido(true);
				}
				
				else {
					try {
						apagar(18);
					} catch (IllegalAccessException | JSchException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setEncendido(false);
				}	
			} 
		     
		})); 
	}
	
	public void init() {
		this.getCapturaVoz().setControlador(this);
		this.getCapturaVoz();
		CapturaDeVoz.getPanel_().setZonasDelHogar(this.getCasa().getObjetos()); 
	}
	
	public void accionar() throws IllegalAccessException, JSchException, IOException {
		gestionarAccion();
	}
	
	private void gestionarAccion() throws IllegalAccessException, JSchException, IOException {
		if(this.getAccion().equals("encender"))
			if(this.getCategoria().equals("regar"))
				encenderParpadeo(mapeo(this.getCategoria(), this.getSubcategoria()));
			else
				encender(mapeo(this.getCategoria(), this.getSubcategoria()));
		
		else if(this.getAccion().equals("apagar"))
			if(this.getCategoria().equals("regar"))
				apagarParpadeo(mapeo(this.getCategoria(), this.getSubcategoria()));
			else
				apagar(mapeo(this.getCategoria(), this.getSubcategoria()));
		
		else if(this.getCasa().getNumAcciones(this.getCategoria()) == 4) {
			if(this.getAccion().equals("aumentar")) {
				if(this.getCategoria().equals("regar"))
					aumentarPotenciaRiego(this.getCategoria());
				else
					aumentarPotencia(this.getCategoria(), mapeo(this.getCategoria(), this.getSubcategoria()));
				
			}
			else if(this.getAccion().equals("disminuir")) {
				if(this.getCategoria().equals("regar")) {
					disminuirPotenciaRiego(this.getCategoria());
				}
				else 
					disminuirPotencia(this.getCategoria(), mapeo(this.getCategoria(), this.getSubcategoria()));
			}
		} 
	}
	
	public void procesarGrabacion() throws Exception {
		String sentencia = this.convertir("Grabacion.wav");
		sentencia = sentencia.replaceAll("ó", "o");
		System.out.println(sentencia);	
		this.clasificar(sentencia);
		System.out.println(this.getAccion() + " " + this.getCategoria() + " " + this.getSubcategoria());
		this.accionar();
	}
	
	private Integer mapeo(String objeto, String lugar) {
		Integer pin = new Integer(0);
		
		if(objeto.equals(this.getCasa().getLuzCocina().getId())) {
			if(lugar.equals("cocina"))
				pin = 17;
			else if(lugar.equals("baño"))
				pin = 20;
			else if(lugar.equals("salon"))
				pin = 21;
			else if(lugar.equals("garaje"))
				pin = 13;
		}
		
		else if(objeto.equals("garaje"))
				pin = 5;
		else if(objeto.equals("regar"))
			pin = 18;
		else if(objeto.equals("aire_acondicionado"))
			pin = 25;
		else if(objeto.equals("calefaccion"))
			pin = 23;
		else if(objeto.equals("horno"))
			pin = 22;
		
		return pin;
	}
	
	public void clasificar(String sentencia) throws IOException {
		this.setAccion(this.getClasificador().clasificar("src/main/java/PLN/accion/aprendizaje/aprendizaje1.txt", sentencia, 4));
		this.setCategoria(this.getClasificador().clasificar("src/main/java/PLN/aprendizaje/aprendizaje1.txt", sentencia, 9));
		this.setSubcategoria(this.getClasificador().clasificar("src/main/java/PLN/subcategorias/aprendizaje/aprendizaje1.txt", sentencia, 5));
	}
	
	public void conectarSSH(String username, String password, String host, int port) throws IllegalAccessException, JSchException {
		this.getSsh().connect(username, password, host, port);
	}
	
	public void desconectarSSH() {
		this.getSsh().disconnect();
	}
	
	public void iniciarRaspberry() throws IllegalAccessException, JSchException, IOException, InterruptedException {
		for(int i = 0; i < this.getRaspberry().getComandosActivar().size(); i++) {
			if(this.getRaspberry().getGpio().get(i).getPin() == 26) {
				System.out.println(this.getRaspberry().getComandosActivar().get(i));
				this.getSsh().executeCommand(this.getRaspberry().getComandosActivar().get(i));
			}
		}
		
		for(int i = 0; i < this.getRaspberry().getComandosDireccionar().size(); i++) {
			if(this.getRaspberry().getGpio().get(i).getPin() == 26)
				this.getSsh().executeCommand(this.getRaspberry().getComandosDireccionar().get(i));
		}
	}
	
	public void encender(Integer pin) throws IllegalAccessException, JSchException, IOException {
		this.getSsh().executeCommand(this.getRaspberry().encender(pin));
	}
	
	private void encenderParpadeo(Integer pin) {
		if(pin == 18)
			this.getTimerRiego().start();
	}
	
	private void apagarParpadeo(Integer pin) throws IllegalAccessException, JSchException, IOException {
		if(pin == 18) {
			this.getTimerRiego().stop();
			apagar(18);
		}
	}
	
	public void apagar(Integer pin) throws IllegalAccessException, JSchException, IOException {
		this.getSsh().executeCommand(this.getRaspberry().apagar(pin));
	}
	
	private void aumentarPotenciaRiego(String objeto) {
		String intensidad;
		
		if(objeto.equals("regar")) {
			intensidad = this.getCasa().getRiego().aumentarIntensidad();
			gestionarIntensidad("regar", intensidad);
		}
	}
	
	private void disminuirPotenciaRiego(String objeto) {
		String intensidad;
		
		intensidad = this.getCasa().getRiego().disminuirIntensidad();
		gestionarIntensidad("regar", intensidad);
	}
	
	private void aumentarPotencia(String objeto, Integer pin) throws IllegalAccessException, JSchException, IOException {
		if(this.getCasa().getCalefaccion().getId().equals(objeto)) {
			System.out.println(this.getRaspberry().encender(pin, this.getCasa().getCalefaccion().aumentarPotencia()));
			this.getSsh().executeCommand(this.getRaspberry().encender(pin, this.getCasa().getCalefaccion().aumentarPotencia()));
		}
		else if(this.getCasa().getAireAcondicionado().getId().equals(objeto)) {
			System.out.println(this.getCasa().getAireAcondicionado().aumentarPotencia());
			this.getSsh().executeCommand(this.getRaspberry().encender(pin, this.getCasa().getAireAcondicionado().aumentarPotencia()));
		}
	}
	
	private void disminuirPotencia(String objeto, Integer pin) throws IllegalAccessException, JSchException, IOException {
		if(this.getCasa().getCalefaccion().getId().equals(objeto)) {
			System.out.println(this.getRaspberry().encender(pin, this.getCasa().getCalefaccion().disminuirPotencia()));
			this.getSsh().executeCommand(this.getRaspberry().encender(pin, this.getCasa().getCalefaccion().disminuirPotencia()));
		}
		else if(this.getCasa().getAireAcondicionado().getId().equals(objeto)) {
			System.out.println(this.getCasa().getAireAcondicionado().disminuirPotencia());
			this.getSsh().executeCommand(this.getRaspberry().encender(pin, this.getCasa().getAireAcondicionado().disminuirPotencia()));
		}
	}
	
	private void gestionarIntensidad(String objeto, String intensidad) {
		if(objeto.equals("regar")) {
			if((intensidad.equals("floja")) || (intensidad.equals("flojo")))
				this.getTimerRiego().setDelay(2500);
			else if((intensidad.equals("media")) || (intensidad.equals("medio")))
				this.getTimerRiego().setDelay(1500);
			else if(intensidad.equals("fuerte"))
				this.getTimerRiego().setDelay(500);
		}
	}
	
	public String convertir(String fileName) throws Exception {
		return this.getConvertidorVoz().convert(fileName);
	}
	
	public ConvertSpeech getConvertidorVoz() {
		return convertidorVoz;
	}
	public void setConvertidorVoz(ConvertSpeech convertidorVoz) {
		this.convertidorVoz = convertidorVoz;
	}

	public Clasificacion getClasificador() {
		return clasificador;
	}

	public void setClasificador(Clasificacion clasificador) {
		this.clasificador = clasificador;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public Raspberry getRaspberry() {
		return raspberry;
	}

	public void setRaspberry(Raspberry raspberry) {
		this.raspberry = raspberry;
	}

	public SSHConnector getSsh() {
		return ssh;
	}

	public void setSsh(SSHConnector ssh) {
		this.ssh = ssh;
	}

	public String getAccion() {
		switch(this.accion) {
			case 1: return "encender";
			case 2: return "apagar";
			case 3: return "aumentar";
			case 4: return "disminuir";
			default: return "";
		}
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	public String getCategoria() {
		switch(this.categoria) {
			case 1: return "luces";
			case 2: return "horno";
			case 3: return "regar";
			case 4: return "alarma";
			case 5: return "ventana";
			case 6: return "puertas";
			case 7: return "garaje";
			case 8: return "calefaccion";
			case 9: return "aire_acondicionado";
			default: return "";
		}
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getSubcategoria() {
		switch(this.subcategoria) {
			case 1: return "cocina";
			case 2: return "salon";
			case 3: return "cuarto";
			case 4: return "garaje";
			case 5: return "baño";
			default: return "";
		}
	}
	
	public void setSubcategoria(Integer subcategoria) {
		this.subcategoria = subcategoria;
	}

	public CapturaDeVoz getCapturaVoz() {
		return capturaVoz;
	}

	public void setCapturaVoz(CapturaDeVoz capturaVoz) {
		this.capturaVoz = capturaVoz;
	}

	public Timer getTimerRiego() {
		return timerRiego;
	}

	public void setTimerRiego(Timer timerRiego) {
		this.timerRiego = timerRiego;
	}

	public Boolean getEncendido() {
		return encendido;
	}

	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}
}
